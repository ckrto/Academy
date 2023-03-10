package com.example.ex13;

import static com.example.ex13.RemoteService.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadActivity extends AppCompatActivity {
    String id;
    EditText edtid, name, pass;
    Retrofit retrofit;
    RemoteService service;
    CircleImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtid = findViewById(R.id.id);
        edtid.setEnabled(false);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        image = findViewById(R.id.image);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        getSupportActionBar().setTitle(id + "님의 정보 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button save = findViewById(R.id.save);
        save.setText("수정하기");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(ReadActivity.this);
                box.setTitle("질의");
                box.setMessage("내용을 수정하시겠습니까?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserVO vo = new UserVO();
                        vo.setId(id);
                        vo.setPass(pass.getText().toString());
                        vo.setName(name.getText().toString());
                        Call<Void> call = service.update(vo);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });
                box.setNegativeButton("아니요", null);
                box.show();
            }
        });

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(RemoteService.class);

        Call<UserVO> call = service.read(id);
        call.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                UserVO vo = response.body();
                edtid.setText(vo.getId());
                name.setText(vo.getName());
                pass.setText(vo.getPass());
                if(vo.getImage() != null) {
                    Picasso.with(ReadActivity.this).load(BASE_URL + vo.getImage()).into(image);
                }
            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}