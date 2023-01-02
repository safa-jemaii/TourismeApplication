package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        bottom_navigation=findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:

                        Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                        startActivity(intent);

                    case R.id.profile:
                        Intent intent1 = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent1);

                    case R.id.map:
                        Intent intent2 = new Intent(getApplicationContext(),MapActivity.class);
                        startActivity(intent2);

                    case R.id.next:
                        Intent intent3 = new Intent(getApplicationContext(),HotelsActivity.class);
                        startActivity(intent3);
                }

                return true;
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng Hammamet = new LatLng(36.40764938836088, 10.61223488949049);
        map.addMarker(new MarkerOptions().position(Hammamet).title("Hammamet"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Hammamet));

        LatLng Sousse = new LatLng(35.875064391596034, 10.567925948036942);
        map.addMarker(new MarkerOptions().position(Sousse).title("Sousse"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Sousse));

        LatLng Kairaouan = new LatLng(35.66990653204258, 10.100841475543513);
        map.addMarker(new MarkerOptions().position(Kairaouan).title("Kairaouan"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Kairaouan));

        LatLng Tozeur = new LatLng(33.9351062445249, 8.111122073821011);
        map.addMarker(new MarkerOptions().position(Tozeur).title("Tozeur"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Tozeur));

        LatLng Bizerte = new LatLng(37.2758775031419, 9.866653322603982);
        map.addMarker(new MarkerOptions().position(Bizerte).title("Bizerte"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Bizerte));

        LatLng Monastir = new LatLng(35.7647141969858, 10.808378828708692);
        map.addMarker(new MarkerOptions().position(Monastir).title("Monastir"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Monastir));

        LatLng Douga = new LatLng(36.42360151570108, 9.219300812699615);
        map.addMarker(new MarkerOptions().position(Douga).title("Douga"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Douga));

        LatLng Djerba = new LatLng(33.83695725935651, 10.863481222160333);
        map.addMarker(new MarkerOptions().position(Djerba).title("Djerba"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Djerba));

        LatLng AainDrahem = new LatLng(36.77386837329041, 8.685057175966174);
        map.addMarker(new MarkerOptions().position(AainDrahem).title("AainDrahem"));
        map.moveCamera(CameraUpdateFactory.newLatLng(AainDrahem));

        LatLng Zaghouan = new LatLng(36.418251041046766, 10.123886642105612);
        map.addMarker(new MarkerOptions().position(Zaghouan).title("Zaghouan"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Zaghouan));


    }
}