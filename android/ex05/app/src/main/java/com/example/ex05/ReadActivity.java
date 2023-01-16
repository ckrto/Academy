package com.example.ex05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadActivity extends AppCompatActivity {
    MemoDB helper;
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("메모 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new MemoDB(this);
        db = helper.getWritableDatabase();

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        String sql = "select * from memo where _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);

        final EditText content = findViewById(R.id.content);
        if(cursor.moveToNext()) {
            content.setText(cursor.getString(1));
        }

        Button save = findViewById(R.id.save);
        save.setText("수정하기");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(ReadActivity.this);
                box.setTitle("질의");
                box.setMessage("수정하시겠습니까?");

                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strContent = content.getText().toString();
                        String strDate = sdf.format(new Date());
                        String sql = "update memo set content = '" + strContent + "', date = '" + strDate + "' where _id = " +id;
                        db.execSQL(sql);
                        finish();
                    }
                });
                box.setNegativeButton("아니요", null);
                box.show();
            }
        });
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