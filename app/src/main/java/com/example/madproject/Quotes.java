package com.example.madproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Quotes extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        textView= findViewById(R.id.txt_quotes);
        String dStory = getIntent().getStringExtra( "story");

        textView.setText(dStory);



    }
}