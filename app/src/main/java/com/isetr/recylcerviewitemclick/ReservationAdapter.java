package com.isetr.recylcerviewitemclick;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    Activity context;
    ArrayList<Reservation> listReservation;
    DatabaseHelper db;


    public ReservationAdapter(Activity context, ArrayList<Reservation> listReservation) {
        super(context,R.layout.reservation_item,listReservation );
        this.context = context;
        this.listReservation = listReservation;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){

        View view= LayoutInflater.from(context).inflate(R.layout.reservation_item,null,false);
        TextView t1=view.findViewById(R.id.text_id);
        TextView t2=view.findViewById(R.id.text_username);
        TextView t3=view.findViewById(R.id.startday1);
        TextView t4=view.findViewById(R.id.endday1);

        ImageView im1=view.findViewById(R.id.edit_reservation);
        ImageView im2=view.findViewById(R.id.delete_reservation);

        t1.setText(String.valueOf(listReservation.get(position).getId()));
        t2.setText(listReservation.get(position).getUsername());
        t3.setText(listReservation.get(position).getStartday());
        t4.setText(listReservation.get(position).getEndday());

        final Reservation reservation=listReservation.get(position);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(context);
                View subView=inflater.inflate(R.layout.reservation_edit,null,false);
                final EditText us=subView.findViewById(R.id.username);
                final EditText sd=subView.findViewById(R.id.startday);
                final EditText ed=subView.findViewById(R.id.endday);
                us.setText(reservation.getId());
                us.setText(reservation.getUsername());
                sd.setText(reservation.getStartday());
                ed.setText(reservation.getEndday());

                AlertDialog.Builder a=new AlertDialog.Builder(context);
                a.setTitle("edit reservation");
                a.setView(subView);
                a.create() ;
                a.setPositiveButton("edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username=us.getText().toString();
                        String startday=sd.getText().toString();
                        String endday=ed.getText().toString();


                        db=new DatabaseHelper(context);
                        db.updateReservation(new Reservation(reservation.getId(),username,startday, endday));
                        context.finish();
                        context.startActivity(context.getIntent());



                    }
                });
                a.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"task cancel",Toast.LENGTH_LONG).show();
                    }
                });
                a.show();
            }
        });



        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DatabaseHelper(context);
                db.removeReservation(reservation.getId());
                context.finish();
                context.startActivity(context.getIntent());
            }
        });


        return view;

    }
}
