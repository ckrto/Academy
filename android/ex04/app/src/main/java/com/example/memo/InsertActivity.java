package com.example.memo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertActivity extends AppCompatActivity {
    MemoDB helper;
    SQLiteDatabase db;
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setTitle("메모등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper=new MemoDB(this);
        db=helper.getWritableDatabase();

        //저장버튼을 클릭한 경우
        TextView save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText content=findViewById(R.id.content);
                String strContent=content.getText().toString();
                String strDate=sdf.format(new Date());
                if(strContent.equals("")){
                    Toast.makeText(InsertActivity.this,"저장할 메모 내용이 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                //테이블에 메모 저장
                String sql="insert into memo(content,date) values(";
                sql+="'"+strContent+"',";
                sql+="'"+strDate+"')";
                db.execSQL(sql);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}