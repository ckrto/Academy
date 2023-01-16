package com.icia.friend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.icia.friend.store.InfoFragment;
import com.icia.friend.store.StorePagerAdapter;
import com.icia.friend.vo.StoreVO;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreReadActivity extends AppCompatActivity {
    ImageView store_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_read);

        Intent intent = getIntent();
        StoreVO vo = (StoreVO) intent.getSerializableExtra("StoreVO");

//        //이미지
//        store_photo = findViewById(R.id.store_photo);
//        final Bitmap[] bitmap = new Bitmap[1];
//        String imageUrl = "http://192.168.0.193:8088/api/display?fileName=" + vo.getS_photo();
//
//        if (vo.getS_photo() != null) {
//            store_photo.setVisibility(View.VISIBLE);
//            Thread Thread = new Thread() {
//
//                @Override
//                public void run() {
//                    try {
//                        URL url = new URL(imageUrl);
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                        //     HttpURLConnection의 인스턴스가 될 수 있으므로 캐스팅해서 사용한다
//                        //     conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
//                        conn.connect();
//                        InputStream is = conn.getInputStream();
//                        //inputStream 값 가져오기
//                        bitmap[0] = BitmapFactory.decodeStream(is);
//                        // Bitmap으로 반환
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            Thread.start();
//            try {
//                //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
//                Thread.join();
//                store_photo.setImageBitmap(bitmap[0]);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } else {
//            store_photo.setVisibility(View.GONE);
//        }


        //탭 생성
        TabLayout s_tabs = findViewById(R.id.s_tabs);
        s_tabs.addTab(s_tabs.newTab().setText("메뉴"));
        s_tabs.addTab(s_tabs.newTab().setText("정보"));
        s_tabs.addTab(s_tabs.newTab().setText("리뷰"));
        s_tabs.setTabGravity(s_tabs.GRAVITY_FILL);

        //Adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.s_viewpager);
        final StorePagerAdapter StorePagerAdapter = new StorePagerAdapter(getSupportFragmentManager(), 3,vo);
        viewPager.setAdapter(StorePagerAdapter);

        //탭 선택 이벤트
        s_tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(s_tabs));
    }
}
