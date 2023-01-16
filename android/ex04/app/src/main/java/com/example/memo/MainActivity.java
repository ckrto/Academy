package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    MemoDB helper;
    SQLiteDatabase db;
    Cursor cursor;
    String sqlAll="select * from memo order by _id desc";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("메모장");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

        helper=new MemoDB(this);
        db=helper.getWritableDatabase();
        cursor=db.rawQuery(sqlAll,null);

        ListView list=findViewById(R.id.list);
        adapter=new Adapter(this,cursor);
        list.setAdapter(adapter);

        //메모등록버튼을 누른경우
        FloatingActionButton insert=findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });


    }
    //메모 어댑터 정의
    class Adapter extends CursorAdapter{

        public Adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.item_memo,parent,false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView content=view.findViewById(R.id.content);
            content.setText(cursor.getString(1));
            TextView date=view.findViewById(R.id.date);
            date.setText(cursor.getString(2));

            final int id=cursor.getInt(0);

            RelativeLayout item=view.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(MainActivity.this,ReadActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }
    }

    //다른 엑티비티에서 finish할 경우 이곳으로 돌아온다.
    @Override
    protected void onRestart() {
        super.onRestart();
        cursor=db.rawQuery(sqlAll,null);
        adapter.changeCursor(cursor);
    }
}