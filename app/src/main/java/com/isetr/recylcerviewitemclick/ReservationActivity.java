package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {
    private ListView l;
    private DatabaseHelper db;
    private ReservationAdapter adapter;
    private ArrayList<Reservation> listReservation;
    private FloatingActionButton btn_add;
    private ImageView edit;
    BottomNavigationView bottom_navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        bottom_navigation=findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
//                    case R.id.home:
//
//                        Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
//                        startActivity(intent);
//
                    case R.id.profile:
                        Intent intent1 = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent1);

                    case R.id.map:
                        Intent intent2 = new Intent(getApplicationContext(),MapActivity.class);
                        startActivity(intent2);

//                    case R.id.next:
//                        Intent intent3 = new Intent(getApplicationContext(),HotelsActivity.class);
//                        startActivity(intent3);
                }

                return true;
            }
        });
//        l=findViewById(R.id.list_view);
//        db=new DatabaseHelper(this);
//        int nbReservation=db.getReservationCount();
//        if(nbReservation==0)
//            Toast.makeText(this, "Empty database", Toast.LENGTH_SHORT).show();
//        else
//        {
//            listReservation=db.getAllReservation();
//            adapter=new ReservationAdapter(this,listReservation);
//            l.setAdapter(adapter);
//        }
        btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(ReservationActivity.this);
                View subView=inflater.inflate(R.layout.reservation_add,null,false);
                final EditText us=subView.findViewById(R.id.username);
                final EditText sd=subView.findViewById(R.id.startday);
                final EditText ed=subView.findViewById(R.id.endday);

                AlertDialog.Builder a=new AlertDialog.Builder(ReservationActivity.this);
                a.setTitle("add new reservation");
                a.setView(subView);
                a.create() ;

                a.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String username=us.getText().toString();
//                        String startday=sd.getText().toString();
//                        String endday=ed.getText().toString();
//                        Reservation reservation=new Reservation(username ,startday,endday);
//                        db.addReservation(reservation);
//                        finish();
//                        startActivity(getIntent());
                        Toast.makeText(ReservationActivity.this, "reservation add succesfuly", Toast.LENGTH_SHORT).show();

                    }
                });
                a.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ReservationActivity.this,"task cancel",Toast.LENGTH_LONG).show();
                    }
                });
                a.show();
            }

        });


    }
}