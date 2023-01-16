package com.icia.friend.remote;

import com.icia.friend.vo.MenuVO;
import com.icia.friend.vo.StoreVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StoreRemoteService {
    public final static String BASE_URL = "http://192.168.0.191:8088/api/store/";

    @GET("clist/{s_c_code}")
    Call<List<StoreVO>> clist (@Path("s_c_code") int s_c_code);

    @GET("search")
    Call<List<HashMap<String,Object>>> search(@Query("query") String query);



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
