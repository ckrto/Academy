package com.example.ex13;

import static com.example.ex13.RemoteService.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    RemoteService service;
    List<UserVO> array = new ArrayList<>();
    UserAdapter userAdapter = new UserAdapter();
    List<String> arrDelete = new ArrayList<>();
    boolean isDone = false;

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);

        switch (item.getItemId()) {
            case R.id.delete :
                if(arrDelete.size() == 0) {
                    box.setTitle("경고");
                    box.setMessage("삭제할 항목을 선택해주세요.");
                    box.setPositiveButton("확인", null);
                    box.show();
                }
                else {
                    box.setTitle("질의");
                    box.setMessage(arrDelete.size() + "개의 항목을 삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for(String id : arrDelete) {
                                UserVO vo = new UserVO();
                                vo.setId(id);
                                Call<Void> call = service.delete(vo);
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        isDone = true;
                                        onRestart();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        System.out.println("오류 : " + t.toString());
                                    }
                                });
                            }
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("사용자 관리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(RemoteService.class);

        onRestart();

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(userAdapter);

        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Call<List<UserVO>> call = service.list();
        call.enqueue(new Callback<List<UserVO>>() {
            @Override
            public void onResponse(Call<List<UserVO>> call, Response<List<UserVO>> response) {
                array = response.body();
                System.out.println(array.size());
                userAdapter.notifyDataSetChanged();
                arrDelete.clear();
            }

            @Override
            public void onFailure(Call<List<UserVO>> call, Throwable t) {
                System.out.println("오류 : " + t.toString());
            }
        });
    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

        @NonNull
        @Override
        public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_user, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
            UserVO vo = array.get(position);
            holder.id.setText(vo.getId());
            holder.name.setText(vo.getName());
            String image = vo.getImage();
            if(image != null) {
                Picasso.with(MainActivity.this).load(BASE_URL + image).into(holder.image);
            }
            else {
                holder.image.setImageResource(R.drawable.ic_image);
            }
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                    intent.putExtra("id", vo.getId());
                    startActivity(intent);
                }
            });
            holder.chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.chk.isChecked()) {
                        arrDelete.add(vo.getId());
                    }
                    else {
                        arrDelete.remove(vo.getId());
                    }
                    System.out.println("........" + arrDelete.size());
                }
            });
            if(isDone) {
                holder.chk.setChecked(false);
            }
        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView id, name;
            RelativeLayout item;
            CheckBox chk;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                id = itemView.findViewById(R.id.id);
                name = itemView.findViewById(R.id.name);
                item = itemView.findViewById(R.id.item);
                chk = itemView.findViewById(R.id.chk);
            }
        }
    }
}