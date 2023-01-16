package com.example.ex02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    EditText edit;
    ListView list;
    ImageView insert, delete;
    ArrayList<String> array = new ArrayList<>();

    Spinner fruit;
    ArrayList<String> farray = new ArrayList<>();

    String strFruit = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setTitle("연습3");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //1. 데이터 생성
        array.add("딸기");
        array.add("포도");
        array.add("복숭아");
        array.add("수박");

        //2. 리스트뷰 생성
        list = findViewById(R.id.list);

        //3. 어댑터 생성
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, array);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        edit = findViewById(R.id.edit);
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edit.getText().toString();
                array.add(strFruit);
                adapter.notifyDataSetChanged();
                edit.setText("");
                Toast.makeText(MainActivity3.this, strFruit + "이(가) 추가됩니다.", Toast.LENGTH_SHORT).show();
            }
        });

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = list.getCheckedItemPosition();
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity3.this);
                if (position == -1) {
                    box.setTitle("경고");
                    box.setMessage("아이템을 선택해주세요.");
                    box.setPositiveButton("닫기", null);
                    box.show();
                } else {
                    box.setTitle("질의");
                    box.setMessage(strFruit + "를 삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            array.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    box.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity3.this, "삭제 취소", Toast.LENGTH_SHORT).show();
                        }
                    });
                    box.show();
                }
            }
        });

        //1. 데이터 생성
        farray.add("망고");
        farray.add("두리안");
        farray.add("코코넛");

        //2. 어댑터 생성
        ArrayAdapter<String> fadapter = new ArrayAdapter<String>(MainActivity3.this, android.R.layout.simple_list_item_1, farray);

        //3. 스피너 생성
        fruit = findViewById(R.id.fruit);
        fruit.setAdapter(fadapter);

        fruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strFruit = farray.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}