package com.example.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewPostActivity extends AppCompatActivity {
    Button btnAdd, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initControls();
        addEvents();
    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initControls() {
        btnAdd = findViewById(R.id.btnAdd);
        btnHome = findViewById(R.id.btnHome);
    }
}
