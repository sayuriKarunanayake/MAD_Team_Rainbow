package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityHome extends AppCompatActivity {

    Button logout, btn_feedback, btn_req;
    FirebaseAuth mAuth; //firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        btn_req =findViewById(R.id.btn_req);
        btn_feedback =findViewById(R.id.btn_feedback);
        logout =findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        //on click method t logout page
        logout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivityHome.this, MainActivity_login.class));
        });

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this,MainActivity2_feedback.class));
            }

        });

        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                startActivity(new Intent(MainActivityHome.this,MainActivity3_req_book.class));
            }

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