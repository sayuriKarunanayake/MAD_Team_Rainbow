package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Book1 extends AppCompatActivity {
    Button btn_back_book1,btn_book1_review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);

        btn_back_book1=(Button)findViewById(R.id.btn_back_book1);
        btn_back_book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Allbooks_main.class));

            }
        });

        btn_book1_review=(Button)findViewById(R.id.btn_book1_review);
        btn_book1_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Review_details.class));

            }
        });

    }
}