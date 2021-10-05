package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityHome extends AppCompatActivity {

    Button logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        logout =findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivityHome.this, MainActivity_login.class));
        });
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivityHome.this,MainActivity_login.class));

        }
    }

}