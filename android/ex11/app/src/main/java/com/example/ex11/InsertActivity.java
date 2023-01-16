package com.example.ex11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText content;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String email;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        db = FirebaseDatabase.getInstance("https://ex10-c00fe-default-rtdb.firebaseio.com");
        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();

        getSupportActionBar().setTitle("메모작성 : "+email);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        content = findViewById(R.id.content);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            String strContentn = content.getText().toString();
            if(strContentn.equals("")){
                AlertDialog.Builder box = new AlertDialog.Builder(InsertActivity.this);
                box.setTitle("메세지");
                box.setMessage("내용을 입력하세요");
                box.setPositiveButton("확인",null);
                box.show();
            }else {
                MemoVO vo = new MemoVO();
                vo.setContent(strContentn);
                vo.setDate(sdf.format(new Date()));
                vo.setEmail(email);
                ref=db.getReference("memos2").push();
                vo.setKey(ref.getKey());
                ref.setValue(vo);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}