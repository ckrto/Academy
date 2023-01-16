package com.icia.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.icia.food.databinding.ActivityMapsBinding;

public class FoodInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    FoodVO vo = new FoodVO();
    FoodDAO dao = new FoodDAO();
    ImageView image, keep;
    TextView name, tel, description, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        getSupportActionBar().setTitle("음식정보");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        keep = findViewById(R.id.keep);
        tel = findViewById(R.id.tel);
        address = findViewById(R.id.address);
        description = findViewById(R.id.description);

        Intent intent = getIntent();

        FoodDB helper = new FoodDB(this);
        vo = dao.read(helper, intent.getIntExtra("id", 0));

        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String strimage = sdPath + "/pictures/" + vo.getImage() ;
        image.setImageBitmap(BitmapFactory.decodeFile(strimage));
        name.setText(vo.getName());
        tel.setText(vo.getTel());
        address.setText(vo.getAddress());
        description.setText(vo.getDescription());

        //전화번호를 클릭한 경우
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel : " + tel.getText().toString()));
                startActivity(callintent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("질의");

        if(vo.getKeep() == 1) {
            keep.setImageResource(R.drawable.ic_keep_on);
        }
        else {
            keep.setImageResource(R.drawable.ic_keep_off);
        }

        //즐겨찾기 버튼을 클릭한 경우
        ImageView keep = findViewById(R.id.keep);
        keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vo.getKeep()==1){
                    keep.setImageResource(R.drawable.ic_keep_on);
                    box.setMessage("즐겨찾기를 취소하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.updateKeep(helper, 0, vo.getId());
                            keep.setImageResource(R.drawable.ic_keep_off);
                            vo.setKeep(0);
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }else{
                    keep.setImageResource(R.drawable.ic_keep_off);
                    box.setMessage("즐겨찾기에 등록하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.updateKeep(helper, 1, vo.getId());
                            vo.setKeep(1);
                            keep.setImageResource(R.drawable.ic_keep_on);
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        LatLng latLng = new LatLng(vo.getLatitude(), vo.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(vo.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

    }
}