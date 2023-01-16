package com.example.ex15;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetomeService {
    public final String BASE_URL="http://192.168.0.106:3000";

    @GET("/food/list.json")
    Call<List<FoodVO>> list(@Query("word") String word,
                            @Query("order") String order);

    @GET("/food/read.json")
    Call<FoodVO> read(@Query("id") int id);

    @POST("/food/insert")
    Call<Void> insert(@Body FoodVO vo);

    @GET("/food/delete")
    Call<Void> delete(@Query("id") int id);
}
