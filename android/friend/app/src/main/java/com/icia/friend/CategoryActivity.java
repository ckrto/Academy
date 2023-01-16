package com.icia.friend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.icia.friend.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findViewById(R.id.han).setOnClickListener(mclick);
        findViewById(R.id.il).setOnClickListener(mclick);
        findViewById(R.id.joong).setOnClickListener(mclick);
        findViewById(R.id.yang).setOnClickListener(mclick);
        findViewById(R.id.asian).setOnClickListener(mclick);
        findViewById(R.id.boon).setOnClickListener(mclick);
        findViewById(R.id.fast).setOnClickListener(mclick);
        findViewById(R.id.dessert).setOnClickListener(mclick);
        findViewById(R.id.etc).setOnClickListener(mclick);
    }

    View.OnClickListener mclick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(CategoryActivity.this, StoreListActivity.class);
            switch (v.getId()){
                case R.id.han:
                    intent.putExtra("s_c_code",1);
                    break;
                case R.id.il:
                    intent.putExtra("s_c_code",2);
                    break;
                case R.id.joong:
                    intent.putExtra("s_c_code",3);
                    break;
                case R.id.yang:
                    intent.putExtra("s_c_code",4);
                    break;
                case R.id.asian:
                    intent.putExtra("s_c_code",5);
                    break;
                case R.id.boon:
                    intent.putExtra("s_c_code",6);
                    break;
                case R.id.fast:
                    intent.putExtra("s_c_code",7);
                    break;
                case R.id.dessert:
                    intent.putExtra("s_c_code",8);
                    break;
                case R.id.etc:
                    intent.putExtra("s_c_code",9);
                    break;
            }
            startActivity(intent);
        }
    };
}