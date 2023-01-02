package com.isetr.recylcerviewitemclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GridItemActivity extends AppCompatActivity {
Button reserver;
    TextView griddata;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        griddata=findViewById(R.id.griddata);

        imageView=findViewById(R.id.imageView);

        Intent intent = getIntent();
        griddata.setText(intent.getStringExtra("griddata"));
        imageView.setImageResource(intent.getIntExtra("imageView",0 ));

        reserver=findViewById(R.id.reserver);
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReservationActivity.class);
                startActivity(intent);
            }
        });





    }
}