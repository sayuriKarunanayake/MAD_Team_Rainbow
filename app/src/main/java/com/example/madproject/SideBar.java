package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SideBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);

        Button btn_sidebar_Freestory, btn_sidebar_review;


        btn_sidebar_Freestory = findViewById(R.id.btn_sidebar_Freestory);
       // btn_sidebar_review = findViewById(R.id.btn_sidebar_review);

        btn_sidebar_Freestory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(SideBar.this, BookHome.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });


        //btn_sidebar_review.setOnClickListener(new View.OnClickListener() {

        //@Override
        // public void onClick(View view) {
        //    Intent a = new Intent(SideBar.this, order_details.class);
        //   a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //   startActivity(a);
        //  }
        // });
        //}
        //}

    }}
