package com.example.ex02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ArrayList<AddressVO> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setTitle("연습4");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.fruit);
        spinner.setVisibility(View.INVISIBLE);

        AddressVO vo = new AddressVO("홍길동", "010-1010-1010", "인천 서구");
        array.add(vo);

        vo = new AddressVO("심청이", "010-2020-2020", "서울 강서구 화곡동");
        array.add(vo);

        ListView list = findViewById(R.id.list);

        final Adapter adapter = new Adapter();
        list.setAdapter(adapter);

        ImageView insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LinearLayout layout = (LinearLayout)View.inflate(MainActivity4.this, R.layout.insert, null);
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity4.this);
                box.setTitle("주소등록");
                box.setView(layout);
                box.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name = layout.findViewById(R.id.name);
                        EditText tel = layout.findViewById(R.id.tel);
                        EditText address = layout.findViewById(R.id.address);

                        AddressVO vo = new AddressVO(name.getText().toString(),
                                tel.getText().toString(),
                                address.getText().toString()
                        );
                        array.add(vo);
                        adapter.notifyDataSetChanged();
                    }
                });
                box.setNegativeButton("취소", null);
                box.show();
            }
        });
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_address, parent, false);
            AddressVO vo = array.get(position);

            TextView name = convertView.findViewById(R.id.name);
            name.setText(vo.getName());

            TextView tel = convertView.findViewById(R.id.tel);
            tel.setText(vo.getTel());

            TextView address = convertView.findViewById(R.id.address);
            address.setText(vo.getAddress());

            ImageView delete = convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity4.this);
                    box.setTitle("질의");
                    box.setMessage(position + "번째 아이템을 삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            array.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }
            });
            return convertView;
        }
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