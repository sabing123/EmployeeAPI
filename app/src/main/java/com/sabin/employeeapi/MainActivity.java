package com.sabin.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnsearchemployee, btnshowemployee, btnsearch,btnregister,btnmanage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnshowemployee = findViewById(R.id.btnshowemployee);
        btnregister = findViewById(R.id.btnregisteremployee);
        btnsearch  =findViewById(R.id.btnsearchemployee);
        btnmanage = findViewById(R.id.btnmanageemployee);


        btnshowemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(MainActivity.this,ShowAllEmployeeActivity.class);
                startActivity(show);
            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(search);
            }
        });

        btnmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manage = new Intent(MainActivity.this,ManageActivity.class);
                startActivity(manage);
            }
        });

    }
}
