package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
}