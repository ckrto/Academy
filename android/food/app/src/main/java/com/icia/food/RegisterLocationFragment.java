package com.icia.food;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class RegisterLocationFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    TextView txtAddress;
    ProgressBar progressBar;
    FoodVO vo = new FoodVO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register_location, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtAddress = view.findViewById(R.id.address);
        progressBar = view.findViewById(R.id.progressBar);

        Button next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("vo", vo);
                ((RegisterActivity)getActivity()).replaceFragment("input", bundle);

            }
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);

        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ActivityCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10.0f, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10.0f, locationListener);

    }
    //GPS ?????? ????????? ??????
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            mMap.addMarker(new MarkerOptions()
                    .position(latLng).title("????????????").icon(BitmapDescriptorFactory.fromResource(R.drawable.current_marker)));
                txtAddress.setText(getAddress(getContext(), latitude, longitude));
                vo.setLongitude(longitude);
                vo.setLatitude(latitude);
                vo.setAddress(txtAddress.getText().toString());
            progressBar.setVisibility(View.GONE);
        }
    };

    //??????, ?????? ????????? ?????? ????????????
    public static String getAddress(Context context, double latitude, double longitude) {
        String strAddress="??????????????? ?????? ????????? 661-6";
        Geocoder geocoder=new Geocoder(context, Locale.KOREA);
        List<Address> address; try {
            address = geocoder.getFromLocation(latitude, longitude, 1);
            strAddress = address.get(0).getAddressLine(0).toString();
        }catch (Exception e) {
            System.out.println("error...........:" + e.toString()); }
        return strAddress;
    }
}