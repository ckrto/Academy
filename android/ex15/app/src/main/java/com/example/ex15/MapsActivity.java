package com.example.ex15;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ex15.databinding.ActivityMapsBinding;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    double x, y;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent=getIntent();
        x = intent.getDoubleExtra("x", 0);
        y = intent.getDoubleExtra("y", 0);

        address=findViewById(R.id.address);
        //getAddress(new LatLng(y, x));

        Button insert=findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,
                        InsertActivity.class);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(y, x);
        mMap.addMarker(new MarkerOptions().position(latLng).title("현재위치"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                x=latLng.longitude;
                y=latLng.latitude;

                mMap.clear();
                MarkerOptions mOptions=new MarkerOptions();
                mOptions.title("위치");
                mOptions.position(latLng);
                mMap.addMarker(mOptions);

                //getAddress(latLng);
                System.out.println("x:" + x);
                System.out.println("y:" + y);

                //address.setText(getAddress(latLng));
                //System.out.println(getAddress(latLng));
            }
        });
    }

    //주소구하기
    public String getAddress(LatLng latLng){
        Geocoder geocoder=new Geocoder(MapsActivity.this,
                Locale.KOREAN);
        String strAddress="";
        try{
            List<Address> list=geocoder.getFromLocation(
                    latLng.latitude, latLng.longitude, 10);
            System.out.println("................" + list.size());
            if(list != null){
                if(list.size() == 0){
                    strAddress="주소찾기 오류";
                }else {
                    strAddress = list.get(0).getAddressLine(0);
                }
            }
        }catch (Exception e){
            System.out.println("오류:" + e.toString());
        }
        return strAddress;
    }
}