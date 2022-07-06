package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondHome extends AppCompatActivity {
    //floating button to all books

    FloatingActionButton float_bk_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_home);

        float_bk_btn =(FloatingActionButton)findViewById(R.id.float_bk_btn);
        float_bk_btn.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),Allbooks_main.class));
               finish();

            }
        });


    }
}
