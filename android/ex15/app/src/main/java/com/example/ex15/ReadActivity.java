package com.example.ex15;

import static com.example.ex15.RetomeService.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadActivity extends AppCompatActivity implements OnMapReadyCallback {
    int id;
    Retrofit retrofit;
    RetomeService service;
    EditText name, tel, address;
    FoodVO vo = new FoodVO();
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent intent=getIntent();
        id=intent.getIntExtra("id", 0);

        getSupportActionBar().setTitle("맛집정보:" + id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=findViewById(R.id.name);
        tel=findViewById(R.id.tel);
        address=findViewById(R.id.address);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(RetomeService.class);
        Call<FoodVO> call=service.read(id);
        call.enqueue(new Callback<FoodVO>() {
            @Override
            public void onResponse(Call<FoodVO> call, Response<FoodVO> response) {
                vo=response.body();
                name.setText(vo.getName());
                tel.setText(vo.getTel());
                address.setText(vo.getAddress());

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(ReadActivity.this);
            }

            @Override
            public void onFailure(Call<FoodVO> call, Throwable t) {

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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //해당 음식점 위치로 이동
        System.out.println("x:" + vo.getX());
        System.out.println("y:" + vo.getY());
        mMap=googleMap;
        LatLng latLng=new LatLng(vo.getY(), vo.getX());
        mMap.addMarker(new MarkerOptions().position(latLng).title(vo.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }
}