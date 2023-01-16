package com.example.ex05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {
    MemoDB helper;
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("메모작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new MemoDB(this);
        db = helper.getWritableDatabase();

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText content = findViewById(R.id.content);
                String strContent = content.getText().toString();
                if(strContent.equals("")) {
                    Toast.makeText(EditActivity.this, "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strDate = sdf.format(new Date());
                String sql = "insert into memo(content, date) values(";
                sql += "'" + strContent + "',";
                sql += "'" + strDate + "')";
                db.execSQL(sql);
                finish();
            }
        });
    }
}