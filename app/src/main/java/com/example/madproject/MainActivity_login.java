package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_login extends AppCompatActivity {
//get ui elements
    EditText etLoginEmail, etLoginPwd;
    Button login ,btn_2signup;

    FirebaseAuth mAuth; //firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);


        btn_2signup= findViewById(R.id.btn_2signup);

        etLoginEmail = findViewById(R.id.etLoginName);
        etLoginPwd = findViewById(R.id.etLoginPwd);

        mAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.login);

        login.setOnClickListener(view->{
            loginUser();
        });

        //on click method for signup btn_req
        btn_2signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_login.this, act2_signup.class));
            }

        });


    }

    public void loginUser() {

        String email=etLoginEmail.getText().toString();
        String password=etLoginPwd.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError(("Email can not be empty"));
            etLoginEmail.requestFocus();

        } else if (TextUtils.isEmpty(password)) {
            etLoginPwd.setError(("Password can not be empty"));
            etLoginPwd.requestFocus();

        }else{
           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull   Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(MainActivity_login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(MainActivity_login.this,MainActivityHome.class));

                   }else{
                       Toast.makeText(MainActivity_login.this, "Login Error:" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                   }
               }
           });
        }






    }

}