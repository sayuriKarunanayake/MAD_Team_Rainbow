package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Panel extends AppCompatActivity {
    Button btn_addminbook,btn_admincate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btn_addminbook=findViewById(R.id.btn_addminbook);
        btn_addminbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Add_new_book.class));

            }
        });
        btn_admincate=findViewById(R.id.btn_admincate);
        btn_admincate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),category_insertion.class));

            }
        });




    }
}