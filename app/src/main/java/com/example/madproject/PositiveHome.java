package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PositiveHome extends AppCompatActivity {
    TextView tvSplash,tvSubSplash;
    Button btn_getstart;
    Animation atg, btgone, btgtwo;
    ImageView ivSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positive_home);

        tvSplash = findViewById(R.id.tvSplash);
        tvSubSplash = findViewById(R.id.tvSubSplash);
        btn_getstart = findViewById(R.id.btn_getstart);
        ivSplash = findViewById(R.id.ivSplash);

        //load Animation
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this, R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this, R.anim.btgtwo);

        //passing animation
        ivSplash.startAnimation((atg));
        tvSplash.startAnimation(btgone);
        tvSubSplash.startAnimation(btgone);
        btn_getstart.startAnimation(btgtwo);

        //passing event
        btn_getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(   PositiveHome.this, PositiveQuotes.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

    }

}