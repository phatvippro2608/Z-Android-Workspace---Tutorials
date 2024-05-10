package com.ntp.apiconnectdrive.api;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeAPI {
    private static OkHttpClient client = new OkHttpClient();

//    public static void main(String[] args) {
//        String videoUrl = "https://www.youtube.com/watch?v=VIDEO_ID";
//        try {
//            Map<String, String> info = getInfo(videoUrl, false);
//            if (info != null) {
//                System.out.println("Video Details:");
//                System.out.println("Title: " + info.get("title"));
//                System.out.println("Author: " + info.get("author"));
//                System.out.println("Formats:");
//                for (String format : info.get("formats").split("\n")) {
//                    System.out.println(format);
//                }
//                System.out.println("Live Data:");
//                System.out.println(info.get("liveData"));
//            } else {
//                System.out.println("Failed to fetch video information.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static Map<String, String> getInfo(String url, boolean throwOnError) throws IOException {
        String videoId = getVideoId(url);

        if (videoId == null) return null;

        String ytApi = "https://www.youtube.com/watch";
        Request request = new Request.Builder()
                .url(ytApi + "?v=" + videoId)
                .build();
        Map<String, String> result = new HashMap<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful() || response.body() == null) {
                        throw new IOException("Cannot get youtube video response");
                    }

                    String responseBody = response.body().string();
                    String ytInitialPlayerResponse = resolvePlayerResponse(responseBody);
                    JSONObject parsedResponse = new JSONObject(ytInitialPlayerResponse);
                    JSONObject streamingData = parsedResponse.optJSONObject("streamingData");

                    StringBuilder formats = new StringBuilder();
                    if (streamingData != null) {
                        for(int i = 0; i<streamingData.getJSONArray("formats").length(); i++){
                            JSONObject format = (JSONObject) streamingData.getJSONArray("formats").get(i);
                            if (format.has("url")) {
                                formats.append(format.getString("url")).append("\n");
                            }
                        }
                        for(int i = 0; i<streamingData.getJSONArray("adaptiveFormats").length(); i++){
                            JSONObject format = (JSONObject) streamingData.getJSONArray("adaptiveFormats").get(i);
                            if (format.has("url")) {
                                formats.append(format.getString("url")).append("\n");
                            }
                        }
                    }

                    boolean isEncryptedVideo = formats.toString().contains("signatureCipher");

                    if (isEncryptedVideo) {
                        String jsFileUrl = getJsFileUrl(responseBody);
                        if (jsFileUrl != null) {
                            String jsFileContent = getRemoteFile("https://www.youtube.com" + jsFileUrl);

                            String decodeFunction = getDecodeFunction(jsFileContent);

                            if (decodeFunction != null) {
                                formats = new StringBuilder();
                                for(int i = 0; i<streamingData.getJSONArray("formats").length(); i++){
                                    JSONObject format = (JSONObject) streamingData.getJSONArray("formats").get(i);
                                    if (format.has("url")) {
                                        String signatureCipher = format.getString("signatureCipher");
                                        String decodedUrl = decodeSignature(signatureCipher, decodeFunction);
                                        formats.append(decodedUrl).append("\n");
                                    }
                                }
                                for(int i = 0; i<streamingData.getJSONArray("adaptiveFormats").length(); i++){
                                    JSONObject format = (JSONObject) streamingData.getJSONArray("adaptiveFormats").get(i);
                                    if (format.has("url")) {
                                        String signatureCipher = format.getString("signatureCipher");
                                        String decodedUrl = decodeSignature(signatureCipher, decodeFunction);
                                        formats.append(decodedUrl).append("\n");
                                    }
                                }
                            }
                        }
                    }

//                    Map<String, String> result = new HashMap<>();
                    result.put("title", parsedResponse.optString("title"));
                    result.put("author", parsedResponse.optString("author"));
                    result.put("formats", formats.toString());

                    if (parsedResponse.optBoolean("isLiveContent")) {
                        try {
                            String m3u8Link = resolveM3U8Link(responseBody);
                            if (m3u8Link != null) {
                                String m3u8FileContent = getRemoteFile(m3u8Link);

                                // Process m3u8 file content if needed

                                result.put("liveData", m3u8FileContent);
                            }
                        } catch (IOException e) {
                            if (throwOnError) {
                                throw e;
                            }
                        }
                    }

                } catch (IOException e) {
                    if (throwOnError) {
                        throw new RuntimeException(e);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        while (!result.isEmpty());
        return result;
    }

    private static String getVideoId(String url) {
        if (url.matches(".*youtu\\.?be.*")) {
            String[] patterns = {
                    "youtu\\.be/([^#\\&\\?]{11})",  // youtu.be/<id>
                    "\\?v=([^#\\&\\?]{11})",        // ?v=<id>
                    "\\&v=([^#\\&\\?]{11})",        // &v=<id>
                    "embed/([^#\\&\\?]{11})",       // embed/<id>
                    "/v/([^#\\&\\?]{11})"           // /v/<id>
            };

            for (String pattern : patterns) {
                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(url);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }

            String[] tokens = url.split("[/\\&\\?=#\\.\\s]");
            for (String token : tokens) {
                if (token.length() == 11) {
                    return token;
                }
            }
        }
        return null;
    }

    private static String resolvePlayerResponse(String watchHtml) {
        if (watchHtml == null || watchHtml.isEmpty()) {
            return "";
        }

        Pattern pattern = Pattern.compile("ytInitialPlayerResponse = (.*);");
        Matcher matcher = pattern.matcher(watchHtml);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private static String resolveM3U8Link(String watchHtml) {
        if (watchHtml == null || watchHtml.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("hlsManifestUrl\":\"(.*\\/file\\/index\\.m3u8)");
        Matcher matcher = pattern.matcher(watchHtml);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    private static String getJsFileUrl(String watchHtml) {
        if (watchHtml == null || watchHtml.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("/s/player/[A-Za-z0-9]+/[A-Za-z0-9_.]+/[A-Za-z0-9_]+/base\\.js");
        Matcher matcher = pattern.matcher(watchHtml);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }

    private static String getRemoteFile(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        String[] rs = {""};
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful() || response.body() == null) {
                        rs[0] = "ERROR";
                        throw new IOException("Failed to fetch remote file: " + url);
                    }
                    rs[0] = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();
        while (rs[0].isEmpty());
        return rs[0];
    }

    private static String getDecodeFunction(String jsFileContent) {
        if (jsFileContent == null || jsFileContent.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("function.*?\\.split\\(\\\"\\\"\\).*?\\.join\\(\\\"\\\"\\)}");
        Matcher matcher = pattern.matcher(jsFileContent);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }

    private static String decodeSignature(String signatureCipher, String decodeFunction) {
        try {
            String[] parts = signatureCipher.split("&");
            Map<String, String> params = new HashMap<>();
            for (String part : parts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], URLDecoder.decode(keyValue[1], "UTF-8"));
                }
            }

            String signature = params.getOrDefault("s", "");
            String signatureParam = params.getOrDefault("sp", "signature");
            String url = params.getOrDefault("url", "");

            String varDeclares = decodeFunction.substring(decodeFunction.indexOf('{') + 1, decodeFunction.lastIndexOf('}'));
            String[] declareLines = varDeclares.split(";");
            Map<String, String> vars = new HashMap<>();
            for (String declareLine : declareLines) {
                String[] varParts = declareLine.split("=");
                if (varParts.length == 2) {
                    vars.put(varParts[0].trim(), varParts[1].trim());
                }
            }

            StringBuilder decodedSignature = new StringBuilder();
            for (char c : signature.toCharArray()) {
                String key = String.valueOf(c);
                if (vars.containsKey(key)) {
                    decodedSignature.append(vars.get(key));
                } else {
                    decodedSignature.append(c);
                }
            }

            return url + "&" + signatureParam + "=" + java.net.URLEncoder.encode(decodedSignature.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}