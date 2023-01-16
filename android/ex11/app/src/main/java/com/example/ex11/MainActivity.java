package com.example.ex11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("로그인");

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void mClick(View v){
        final String strEmail = email.getText().toString();
        final String strPassword = password.getText().toString();
        final AlertDialog.Builder box= new AlertDialog.Builder(this);
        switch (v.getId()){
            case R.id.register://새로운 이메일 등록

                if(strPassword.length()<8){
                    Toast.makeText(this,"비밀번호가 너무 짧습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                box.setTitle("질문");
                box.setMessage("새로운 사용자를 등록합니다");
                box.setPositiveButton("에", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"등록완료",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this,"등록실패",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });
                box.setNegativeButton("아니오",null);
                box.show();

                break;
            case R.id.login://로그인

                box.setTitle("메세지");
                mAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this,MemoActivity.class);
                            startActivity(intent);
                        }else {
                            box.setMessage("로그인 실패");
                            box.setPositiveButton("확인",null);
                            box.show();
                        }
                    }
                });
                break;
        }
    }
}