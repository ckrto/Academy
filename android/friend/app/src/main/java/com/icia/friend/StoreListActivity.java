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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.icia.friend.remote.StoreRemoteService.BASE_URL;

import com.icia.friend.remote.StoreRemoteService;
import com.icia.friend.vo.StoreVO;


public class StoreListActivity extends AppCompatActivity {
    Retrofit s_retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    StoreRemoteService service=s_retrofit.create(StoreRemoteService.class);
    List<StoreVO> array=new ArrayList<>();
    storeAdapter storeAdapter= new storeAdapter();
    Intent intent;
    int s_c_code;

    TextView[] text=new TextView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        text[0]=(TextView) findViewById(R.id.C_han);
        text[1]=(TextView) findViewById(R.id.C_il);
        text[2]=(TextView) findViewById(R.id.C_joong);
        text[3]=(TextView) findViewById(R.id.C_yang);
        text[4]=(TextView) findViewById(R.id.C_asian);
        text[5]=(TextView) findViewById(R.id.C_boon);
        text[6]=(TextView) findViewById(R.id.C_fast);
        text[7]=(TextView) findViewById(R.id.C_dessert);
        text[8]=(TextView) findViewById(R.id.C_etc);

        intent=getIntent();
        s_c_code=intent.getIntExtra("s_c_code",0);
        onRestart();

        RecyclerView store_list=findViewById(R.id.store_list);
        store_list.setLayoutManager(new LinearLayoutManager(this));
        store_list.setAdapter(storeAdapter);


        View v_swipe = (View) findViewById(R.id.store_list);
        v_swipe.setOnTouchListener(new OnSwipeTouchListener(StoreListActivity.this) {
            public void onSwipeRight() {
                if(s_c_code==1){
                    return;
                }
                else{
                    s_c_code=s_c_code-1;
                    onRestart();
                }
            }
            public void onSwipeLeft() {
                if(s_c_code==9){
                    return;
                }
                else {
                    s_c_code = s_c_code + 1;
                    onRestart();
                }
            }
        });

        for(int i=0; i<9; i++){
            int j=i;
            text[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s_c_code=j+1;
                    onRestart();
                }
            });
        }

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Call<List<StoreVO>> call=service.clist(s_c_code);
        call.enqueue(new Callback<List<StoreVO>>() {
            @Override
            public void onResponse(Call<List<StoreVO>> call, Response<List<StoreVO>> response) {
                array=response.body();
                storeAdapter.notifyDataSetChanged();

                for(int i=0; i<9; i++){
                    text[i].setBackgroundResource(0);
                }
                text[s_c_code-1].setBackgroundResource(R.drawable.selected);
            }

            @Override
            public void onFailure(Call<List<StoreVO>> call, Throwable t) {
                System.out.println(t.toString()+"오류.............................");
            }
        });
    }

    class storeAdapter extends RecyclerView.Adapter<storeAdapter.ViewHolder>{

        @NonNull
        @Override
        public storeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.store_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull storeAdapter.ViewHolder holder, int position) {
            StoreVO vo = array.get(position);
            holder.s_location.setText(vo.getS_location());
            holder.s_name.setText(vo.getS_name());
            holder.s_tel.setText(vo.getS_tel());

//            final Bitmap[] bitmap = new Bitmap[1];
//            String imageUrl="http://192.168.0.193:8088/api/display?fileName="+vo.getS_photo();
//
//            if(vo.getS_photo() !=null){
//                holder.s_photo.setVisibility(View.VISIBLE);
//                Thread Thread = new Thread() {
//
//                    @Override
//                    public void run(){
//                        try{
//                            URL url = new URL(imageUrl);
//                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//
//                            //     HttpURLConnection의 인스턴스가 될 수 있으므로 캐스팅해서 사용한다
//                            //     conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
//                            conn.connect();
//                            InputStream is = conn.getInputStream();
//                            //inputStream 값 가져오기
//                            bitmap[0] = BitmapFactory.decodeStream(is);
//                            // Bitmap으로 반환
//                        } catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                Thread.start();
//                try{
//
//                    //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
//                    Thread.join();
//                    holder.s_photo.setImageBitmap(bitmap[0]);
//                }catch (InterruptedException e){
//
//                    e.printStackTrace();
//                }
//            }
//            else{
//                holder.s_photo.setVisibility(View.GONE);
//            }

            holder.s_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(StoreListActivity.this,StoreReadActivity.class);
                    intent.putExtra("StoreVO",vo);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout s_item;
            TextView s_name,s_location,s_tel;
            ImageView s_photo;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                s_name=itemView.findViewById(R.id.s_name);
                s_location=itemView.findViewById(R.id.s_location);
                s_tel=itemView.findViewById(R.id.s_tel);
                s_photo=itemView.findViewById(R.id.s_photo);
                s_item=itemView.findViewById(R.id.s_item);

            }
        }
    }
}