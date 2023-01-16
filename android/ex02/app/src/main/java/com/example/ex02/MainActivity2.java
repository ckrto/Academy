package com.example.ex02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<MovieVO> array  = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("연습2");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //1.데이터생성
        MovieVO vo = new MovieVO();
        vo.setImage(R.drawable.a1);
        vo.setTitle("액션가면 VS 그래그래 마왕");
        vo.setActor("짱구, 봉미선, 신영만");
        array.add(vo);

        vo = new MovieVO();
        vo.setImage(R.drawable.a2);
        vo.setTitle("부리부리 왕국의 보물");
        vo.setActor("짱구, 봉미선, 신영만");
        array.add(vo);

        vo = new MovieVO();
        vo.setImage(R.drawable.a3);
        vo.setTitle("흑부리 마왕의 야망");
        vo.setActor("짱구, 봉미선, 신영만");
        array.add(vo);

        vo = new MovieVO();
        vo.setImage(R.drawable.a4);
        vo.setTitle("핸더랜드의 대모험");
        vo.setActor("짱구, 봉미선, 신영만");
        array.add(vo);

        //2.리스트뷰 생성
        list = findViewById(R.id.list);

        //3. 어댑터 생성
        MovieAdapter adapter = new MovieAdapter();
        list.setAdapter(adapter);
    }

    class MovieAdapter extends BaseAdapter {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_movie, parent, false);

            MovieVO vo = array.get(position);

            ImageView image = convertView.findViewById(R.id.image);
            image.setImageResource(vo.getImage());

            TextView title = convertView.findViewById(R.id.title);
            title.setText(vo.getTitle());

            TextView actor = convertView.findViewById(R.id.actor);
            actor.setText(vo.getActor());

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