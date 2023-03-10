package com.example.ex14;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://192.168.0.106:3000";

    //상품목록
    @GET("/product/list.json")
    Call<List<ProductVO>> list(@Query("word") String word,
                               @Query("order") String order);

    //상품등록
    @Multipart
    @POST("/product/insert")
    Call<Void> insert(@Part("name") RequestBody name,
                      @Part("price") RequestBody price,
                      @Part MultipartBody.Part image);
    //상품정보
    @GET("/product/read.json")
    Call<ProductVO> read(@Query("code") int code);

    //상품삭제
    @GET("/product/delete")
    Call<Void> delete(@Query("code") int code);

    //상품수정
    @POST("/product/update")
    Call<Void> update(@Body ProductVO vo);
}
