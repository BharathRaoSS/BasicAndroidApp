package com.example.basicandroidapp.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestInterface {
    @GET("api/sample_response.php")
    Call<JsonResponse> getJSON();
}
