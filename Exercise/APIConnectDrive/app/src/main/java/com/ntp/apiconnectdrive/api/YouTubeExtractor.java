package com.ntp.apiconnectdrive.api;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeExtractor {

    private static final String TAG = "YouTubeExtractor";

    public interface OnExtractionComplete {
        void onExtractionComplete(List<YouTubeVideo> videoList);
    }

    public static void extract(String videoUrl, OnExtractionComplete onExtractionComplete) {
        new Thread(() -> {
            try {
                List<YouTubeVideo> videoList = extractVideos(videoUrl);
                onExtractionComplete.onExtractionComplete(videoList);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static List<YouTubeVideo> extractVideos(String videoUrl) throws IOException, JSONException {
        List<YouTubeVideo> videoList = new ArrayList<>();

        String videoId = getVideoId(videoUrl);
        if (videoId == null) return videoList;

        String ytApi = "https://www.youtube.com/watch?v=" + videoId;
        String watchHtml = getRemoteFile(ytApi);

        if (watchHtml == null || watchHtml.isEmpty()) return videoList;

        String ytInitialPlayerResponse = resolvePlayerResponse(watchHtml);
        if (ytInitialPlayerResponse.isEmpty()) return videoList;

        JSONObject parsedResponse = new JSONObject(ytInitialPlayerResponse);
        JSONObject streamingData = parsedResponse.optJSONObject("streamingData");

        if (streamingData == null) return videoList;

        JSONArray formats = streamingData.optJSONArray("formats");
        JSONArray adaptiveFormats = streamingData.optJSONArray("adaptiveFormats");

        List<JSONObject> allFormats = new ArrayList<>();

        if (formats != null) {
            for (int i = 0; i < formats.length(); i++) {
                allFormats.add(formats.getJSONObject(i));
            }
        }

        if (adaptiveFormats != null) {
            for (int i = 0; i < adaptiveFormats.length(); i++) {
                allFormats.add(adaptiveFormats.getJSONObject(i));
            }
        }

        for (JSONObject format : allFormats) {
            String url = format.optString("url");
            if (url.isEmpty()) continue;

            boolean isEncrypted = format.has("signatureCipher");

            if (isEncrypted) {
                String signatureCipher = format.optString("signatureCipher");
                String decryptedUrl = decryptSignature(signatureCipher, watchHtml);
                if (!decryptedUrl.isEmpty()) {
                    url = decryptedUrl;
                }
            }

            String quality = format.optString("quality", "Unknown Quality");
            String type = format.optString("mimeType", "Unknown Mime Type");

            YouTubeVideo video = new YouTubeVideo(videoId, url, quality, type);
            videoList.add(video);
        }

        return videoList;
    }

    private static String getVideoId(String videoUrl) {
        String[] patterns = {
                "(?:https?:\\/\\/)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?v=|embed\\/|v\\/))([a-zA-Z0-9_-]{11})"
        };

        for (String pattern : patterns) {
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(videoUrl);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        return null;
    }

    private static String getRemoteFile(String urlString) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(new URL(urlString).openStream(),
                StandardCharsets.UTF_8.toString());
        scanner.useDelimiter("\\A");
        String content = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        return content;
    }

    private static String resolvePlayerResponse(String watchHtml) {
        if (watchHtml == null || watchHtml.isEmpty()) {
            return "";
        }

//        Pattern pattern = Pattern.compile("ytInitialPlayerResponse = (.*?)(?=}});", Pattern.DOTALL);
        Pattern pattern = Pattern.compile("ytInitialPlayerResponse = (.*?)(?=\\}\\}\\);)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(watchHtml);

        if (matcher.find()) {
            return matcher.group(1) + "}}}";
        }

        return "";
    }

    private static String decryptSignature(String signatureCipher, String watchHtml) {
        if (signatureCipher == null || signatureCipher.isEmpty() || watchHtml == null || watchHtml.isEmpty()) {
            return "";
        }

        String jsFileUrl = resolveJsFileUrl(watchHtml);
        if (jsFileUrl.isEmpty()) return "";

        String jsFileContent = null;
        try {
            jsFileContent = getRemoteFile(jsFileUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String decodeFunction = extractDecodeFunction(jsFileContent);
        if (decodeFunction.isEmpty()) return "";

        String varName = extractVarName(decodeFunction);
        if (varName.isEmpty()) return "";

        String varDeclares = extractVarDeclares(jsFileContent, varName);
        if (varDeclares.isEmpty()) return "";

        String signature = extractSignature(signatureCipher);
        if (signature.isEmpty()) return "";

        String decodedSignature = executeDecodeFunction(decodeFunction, varDeclares, signature);

        return decodedSignature;
    }

    private static String resolveJsFileUrl(String watchHtml) {
        Pattern pattern = Pattern.compile("/s/player/[A-Za-z0-9]+/[A-Za-z0-9_.]+/[A-Za-z0-9_]+/base\\.js");
        Matcher matcher = pattern.matcher(watchHtml);

        if (matcher.find()) {
            return "https://www.youtube.com" + matcher.group(0);
        }

        return "";
    }

    private static String extractDecodeFunction(String jsFileContent) {
        Pattern pattern = Pattern.compile("function.*\\.split\\(\"\"\\).*\\.join\\(\"\"\\)}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(jsFileContent);

        if (matcher.find()) {
            return matcher.group(0);
        }

        return "";
    }

    private static String extractVarName(String decodeFunction) {
        Pattern pattern = Pattern.compile("\\.split\\(\"\"\\);([a-zA-Z0-9]+)\\.");
        Matcher matcher = pattern.matcher(decodeFunction);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private static String extractVarDeclares(String jsFileContent, String varName) {
        Pattern pattern = Pattern.compile("var " + varName + "={(.*?\\}\\};)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(jsFileContent);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private static String extractSignature(String signatureCipher) {
        try {
            JSONObject params = new JSONObject(signatureCipher);
            String signature = params.optString("s");
            return signature != null ? signature : "";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String executeDecodeFunction(String decodeFunction, String varDeclares, String signature) {
        String functionCode = "function decodeSignature(signature) {" +
                "    " + varDeclares +
                "    return (" + decodeFunction + ")(signature);" +
                "}";

        try {
            String encodedFunction = new String(android.util.Base64.encode(functionCode.getBytes(), android.util.Base64.DEFAULT));
            String evalFunction = "eval(atob('" + encodedFunction + "'))";
            return (String) evalFunction.getClass().getMethod("decodeSignature", String.class).invoke(null, signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static class YouTubeVideo {
        private final String videoId;
        private final String url;
        private final String quality;
        private final String mimeType;

        public YouTubeVideo(String videoId, String url, String quality, String mimeType) {
            this.videoId = videoId;
            this.url = url;
            this.quality = quality;
            this.mimeType = mimeType;
        }

        public String getVideoId() {
            return videoId;
        }

        public String getUrl() {
            return url;
        }

        public String getQuality() {
            return quality;
        }

        public String getMimeType() {
            return mimeType;
        }
    }
}
