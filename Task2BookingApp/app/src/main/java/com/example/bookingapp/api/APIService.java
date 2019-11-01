package com.example.bookingapp.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {

    @POST("sample/add")

    @GET("sample/list")

    @PUT("sample/update")

    @DELETE("sample/delete")
    Call<String> delete(@Query("id") int id);

}
