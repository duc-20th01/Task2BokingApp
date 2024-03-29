package com.example.bookingapp.api;

import com.example.bookingapp.model.Member;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {

    @POST("post/add")
    Call<String> add(@Body Post post);

    @POST("member/login")
    Call<Member> login(@Body Member member);

    @POST("member")
    Call<String> rigister(@Body Member member);

    @GET("post/list")
    Call<ArrayList<Post>> getAll(@Query("id") String id);

    @PUT("post/update")
    Call<Void> updatePost(@Body Post post);

    @DELETE("post/delete")
    Call<Void> delete(@Query("id") int id);

    @PUT("member/editProfile")
    Call<String> updateMember(@Body Member member);

    @GET("member/login")
    Call<ArrayList<Member>> getAllDataMember();

    @PUT("member/changePassword")
    Call<String> changePassWord(@Body Member member);

}
