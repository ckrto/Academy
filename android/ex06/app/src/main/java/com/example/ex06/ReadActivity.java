package com.example.ex06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReadActivity extends AppCompatActivity {
    AddressDB helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setTitle("주소수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        helper = new AddressDB(this);
        db = helper.getWritableDatabase();
        String sql = "select * from address where _id = " + id;

        CircleImageView image = findViewById(R.id.image);
        EditText name = findViewById(R.id.name);
        EditText tel = findViewById(R.id.tel);
        EditText juso = findViewById(R.id.juso);

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            String strImage = cursor.getString(4);
            if(strImage != null) {
                image.setImageURI(Uri.parse(cursor.getString(4)));
            }
            name.setText(cursor.getString(1));
            tel.setText(cursor.getString(2));
            juso.setText(cursor.getString(3));
        }
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