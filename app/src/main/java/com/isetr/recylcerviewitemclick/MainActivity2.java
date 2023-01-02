package com.isetr.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    EditText username, password, repassword;
    BottomNavigationView bottom_navigation;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2
        );


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);


        DB = new DBHelper(this);

        findViewById(R.id.btnsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity2.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert) {
                                Toast.makeText(MainActivity2.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity2.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity2.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }

                }
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
                        Intent intent2 = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent2);

                    case R.id.map:
                        Intent intent3 = new Intent(getApplicationContext(),MapActivity.class);
                        startActivity(intent3);

                    case R.id.next:
                        Intent intent1 = new Intent(getApplicationContext(),HotelsActivity.class);
                        startActivity(intent1);
                }

                return true;
            }
        });




        findViewById(R.id.btnsignin).setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        });









    }
}