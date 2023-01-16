package com.icia.friend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    SearchView search;
    Button s_submit;

    String query;
    Intent s_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.category);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });


        s_intent=new Intent(MainActivity.this,SearchListActivity.class);

        search=findViewById(R.id.search);
        search.isSubmitButtonEnabled();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                s_intent.putExtra("query",s);
                startActivity(s_intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query=newText;
                return true;
            }
        });

        s_submit=findViewById(R.id.s_submit);
        s_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_intent.putExtra("query",query);
                startActivity(s_intent);
            }
        });
    }
}