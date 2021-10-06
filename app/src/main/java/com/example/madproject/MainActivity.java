package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton toolbarcartbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarcartbtn5 = findViewById(R.id.toolbarcartbtn5);

        toolbarcartbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(   MainActivity.this, shopping_cart.class);
                cart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(cart);
            }
        });
    }

}