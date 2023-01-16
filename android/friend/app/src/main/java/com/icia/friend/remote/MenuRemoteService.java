package com.icia.friend.remote;

import com.icia.friend.vo.MenuVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuRemoteService {
    public final static String BASE_URL = "http://192.168.0.191:8088/api/store/";

    @GET("list/{s_code}")
    Call<List<MenuVO>> list (@Path("s_code") String s_code);



//    @GET("/food/list.json") 
//    Call<List<FoodVO>> list(@Query("word") String word, @Query("order") String order);
//
//    @GET("/food/read.json")
//    Call<FoodVO> read(@Query("id") int id);
//
//    @POST("/food/insert")
//    Call<Void> insert(@Body FoodVO vo);
//
//    @GET("/food/delete")
//    Call<Void> delete(@Query("id") int id);

}
