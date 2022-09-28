package com.example.login;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class act2_signup extends AppCompatActivity {

    EditText   etEmail,etPwd;
    Button btn_signup, btn_2signin;

    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2_signup);


        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etLoginPwd);


        mAuth=FirebaseAuth.getInstance();
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(View ->{
            createUser();
        });

        //btn_2signup direction

        btn_2signin = findViewById(R.id.btn_2signin);
        btn_2signin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(act2_signup.this, MainActivity_login.class));
            }
        });
    }


    public void createUser(){

        String email=etEmail.getText().toString();
        String password=etPwd.getText().toString();

            if (TextUtils.isEmpty(email)) {
                etEmail.setError(("Email can not be empty"));
                etEmail.requestFocus();

                 } else if (TextUtils.isEmpty(password)) {
                etPwd.setError(("Passwordcan not be empty"));
                etPwd.requestFocus();

            } else {
                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull  Task<AuthResult> task) {

                         if(task.isSuccessful()){
                             Toast.makeText(act2_signup.this, "User Registered successfully", Toast.LENGTH_SHORT).show();

                             startActivity(new Intent(act2_signup.this,MainActivity_login.class));
                         }else{
                             Toast.makeText(act2_signup.this, "Registration Error:" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                         }
                     }
                 });


            }



    }




}



