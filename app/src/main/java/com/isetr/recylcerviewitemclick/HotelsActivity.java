package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HotelsActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;

    GridView gridView;
    String[] hotelName = {"hammamet", "Sousse", "Kairaouan", "Tozeur","Bizerte", "Monasstir", "Douga", "Djerba", "Aain Drahem", "Zaghouan", "Mednin","Sfax"};
    int[] hotelImage = {R.drawable.hotel1,R.drawable.hotel, R.drawable.businesshotel, R.drawable.h3, R.drawable.tozeurhotel, R.drawable.hotelmed,
            R.drawable.hotel33, R.drawable.whihotels,R.drawable.hotelsenti,R.drawable.pooldjerba , R.drawable.mednine, R.drawable.concordesfax};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        gridView= findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("griddata", hotelName[i]);
                intent.putExtra("imageView", hotelImage[i]);
                startActivity(intent);
            }
        });

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

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return hotelImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1= getLayoutInflater().inflate(R.layout.row_data,null);
            TextView griddata = view1.findViewById(R.id.hotels);
            ImageView imageView = view1.findViewById(R.id.images);

            griddata.setText(hotelName[i]);
            imageView.setImageResource(hotelImage[i]);
            return view1;
        }
    }
}