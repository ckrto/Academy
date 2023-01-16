package com.example.ex06;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class InsertActivity extends AppCompatActivity {
    CircleImageView image;
    EditText name, tel, juso;
    String strImage = "";
    AddressDB helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setTitle("주소등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new AddressDB(this);
        db = helper.getWritableDatabase();

        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        Button insert = findViewById(R.id.insert);
        name = findViewById(R.id.name);
        tel = findViewById(R.id.tel);
        juso = findViewById(R.id.juso);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                String strTel = tel.getText().toString();
                String strJuso = juso.getText().toString();
                if(strName.equals("") || strTel.equals("") || strJuso.equals("")) {
                    Toast.makeText(InsertActivity.this, "이름과 전화번호 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sql = "insert into address(name, tel, juso, image) values(";
                sql += "'" + strName + "',";
                sql += "'" + strTel + "',";
                sql += "'" + strJuso + "',";
                sql += "'" + strImage + "')";
                db.execSQL(sql);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        image.setImageURI(data.getData());
        strImage = data.getData().toString();

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}