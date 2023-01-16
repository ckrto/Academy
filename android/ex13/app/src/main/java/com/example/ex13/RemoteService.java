package com.example.ex13;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL = "http://192.168.0.191:3000";

    @GET("/users/list.json")
    Call<List<UserVO>> list();

    @POST("/users/insert")
    Call<Void> insert(@Body UserVO vo);

    @GET("/users/read.json")
    Call<UserVO> read(@Query("id") String id);

    @POST("/users/update")
    Call<Void> update(@Body UserVO vo);

    @POST("/users/delete")
    Call<Void> delete(@Body UserVO vo);
}
