package com.example.ex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }
    public void mClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn1 :
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.btn2 :
                intent = new Intent(this, MainActivity2.class);
                break;
            case R.id.btn3 :
                intent = new Intent(this, MainActivity3.class);
                break;
            case R.id.btn4 :
                intent = new Intent(this, MainActivity4.class);
                break;

        }
        startActivity(intent);
    }
}