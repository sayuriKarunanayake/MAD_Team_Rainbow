package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class act2_signup extends AppCompatActivity {
    EditText etName, etPwd, etEmail;
    Button btn_signup;
    DatabaseReference dbRef;
    Member memObj;
    long maxId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2_signup);

        etName = findViewById(R.id.etName);
        etPwd = findViewById(R.id.etPwd);
        etEmail = findViewById(R.id.etEmail);
        btn_signup = findViewById(R.id.btn_signup);

        memObj = new Member(); //create new member object
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Member");

    }
          public void ClearControls() {
            memObj.setName("");
            etPwd.setText("");
            etEmail.setText("");
        }
                //dbRef.child("Member").setValue(memObj);
                //Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();


      public void CreateData(View view){

          dbRef = FirebaseDatabase.getInstance().getReference().child("Member");

          dbRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if(snapshot.exists())
                      maxId=(snapshot.getChildrenCount());
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });

          try {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "please enter your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etPwd.getText().toString())) {
            Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "please enter your email address", Toast.LENGTH_SHORT).show();
        } else {
            memObj.setName(etName.getText().toString().trim());
            memObj.setPassword(etPwd.getText().toString().trim());
            memObj.setEmail(etEmail.getText().toString().trim());

             dbRef.push().setValue(memObj);
            dbRef.child("Member").setValue(memObj);
            Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_SHORT).show();
             ClearControls();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    }

        //onclick btn_submit events

