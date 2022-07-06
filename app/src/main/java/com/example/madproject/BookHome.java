package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BookHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_home);

        TextView tv_book_home;

            tv_book_home = findViewById(R.id.tv_book_home);
        }

        public void openSecond(View view){
            Intent intent = new Intent(this,SecondHome.class);
            startActivity(intent);

            //display toast message
            Toast.makeText(getApplicationContext(),"Welcome to the Book Store",Toast.LENGTH_SHORT).show();


        }
}