package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity{
    EditText username, password;
Button btnmap;
    DBHelper DB;
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username =  findViewById(R.id.username1);
        password =  findViewById(R.id.password1);
        DB = new DBHelper(this);
        btnmap =  findViewById(R.id.btnmap);


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
        findViewById(R.id.btnsignin1).setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View view) {
                                                                 String user = username.getText().toString();
                                                                 String pass = password.getText().toString();
                                                                 if (user.equals("") || pass.equals(""))
                                                                     Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                                                                 else {
                                                                     Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                                                                     if (checkuserpass) {
                                                                         Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                                                         Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                                         startActivity(intent);
                                                                     } else {
                                                                         Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                                                     }
                                                                 }
                                                             }

                                                             });



        findViewById(R.id.btnmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);


            }

        });
                                                         }






}