package com.ntp.apiconnectdrive.api;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.POST;

public interface DriveAPIService {

    @POST("token")
    Call<Response> postToken();
}
