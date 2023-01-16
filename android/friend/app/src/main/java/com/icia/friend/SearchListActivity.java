package com.icia.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icia.friend.remote.StoreRemoteService;
import com.icia.friend.vo.StoreVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.icia.friend.remote.StoreRemoteService.BASE_URL;

public class SearchListActivity extends AppCompatActivity {
    Retrofit retrofit;
    StoreRemoteService s_service;
    String query;
    List<HashMap<String,Object>> array=new ArrayList<>();
    searchAdapter searchAdapter= new searchAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        
        Intent intent=getIntent();
        query=intent.getStringExtra("query");

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        s_service = retrofit.create(StoreRemoteService.class);
        onRestart();

        RecyclerView list = findViewById(R.id.search_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(searchAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Call<List<HashMap<String,Object>>> call=s_service.search(query);
        call.enqueue(new Callback<List<HashMap<String,Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, Object>>> call, Response<List<HashMap<String, Object>>> response) {
                System.out.println(response);
                array=response.body();
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HashMap<String, Object>>> call, Throwable t) {
                System.out.println("오류.........." + t.toString());
            }

        });
    }

    class searchAdapter extends RecyclerView.Adapter<searchAdapter.ViewHolder>{

        @NonNull
        @Override
        public searchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.search_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull searchAdapter.ViewHolder holder, int position) {
            HashMap<String,Object> map=array.get(position);
            System.out.println(map);
            holder.search_name.setText(map.get("s_name").toString());
            holder.search_location.setText(map.get("s_location").toString());
            holder.search_menu.setText(map.get("m_name").toString());

            final Bitmap[] bitmap = new Bitmap[1];
            String imageUrl="http://192.168.0.193:8088/api/display?fileName="+map.get("s_photo");

            if(!map.get("s_photo").equals(null)){
                holder.search_photo.setVisibility(View.VISIBLE);
                Thread Thread = new Thread() {

                    @Override
                    public void run(){
                        try{
                            URL url = new URL(imageUrl);
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                            //     HttpURLConnection의 인스턴스가 될 수 있으므로 캐스팅해서 사용한다
                            //     conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            //inputStream 값 가져오기
                            bitmap[0] = BitmapFactory.decodeStream(is);
                            // Bitmap으로 반환
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                };
                Thread.start();
                try{

                    //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
                    Thread.join();
                    holder.search_photo.setImageBitmap(bitmap[0]);
                }catch (InterruptedException e){

                    e.printStackTrace();
                }
            }
            else{
                holder.search_photo.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView search_name, search_location, search_menu;
            ImageView search_photo;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                search_name = itemView.findViewById(R.id.search_name);
                search_location = itemView.findViewById(R.id.search_location);
                search_menu = itemView.findViewById(R.id.search_menu);
                search_photo = itemView.findViewById(R.id.search_photo);
            }
        }
    }


}