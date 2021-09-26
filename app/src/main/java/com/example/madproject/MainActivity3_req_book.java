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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ValueEventRegistration;

public class MainActivity3_req_book extends AppCompatActivity {

    EditText etBook, etAuthor, etUsername, etPhone;
    Button btn_submit, btn_view;
    DatabaseReference dbRef;
    Book bkObj;
    long maxId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_req_book);

        etBook = findViewById(R.id.etBook);
        etAuthor = findViewById(R.id.etAuthor);
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        btn_submit = findViewById(R.id.btn_submit);
        btn_view = findViewById(R.id.btn_view);

        bkObj = new Book(); //create new member object
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Member");

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(MainActivity3_req_book.this,MainActivity4_requests.class);
                b.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(b);
            }
        });
        
    }

    public void ClearControls() {
        etBook.setText("");
        etAuthor.setText("");
        etUsername.setText("");
        etPhone.setText("");

    }
    //dbRef.child("Member").setValue(memObj);
    //Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();


    public void CreateRequest(View view) {

        dbRef = FirebaseDatabase.getInstance().getReference().child("Book");

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
            if (TextUtils.isEmpty(etBook.getText().toString())) {
                Toast.makeText(getApplicationContext(), "please enter book name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(etAuthor.getText().toString())) {
                Toast.makeText(getApplicationContext(), "please enter name of the author", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(etUsername.getText().toString())) {
                Toast.makeText(getApplicationContext(), "please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                bkObj.setUsername(etBook.getText().toString().trim());
                bkObj.setBook(etAuthor.getText().toString().trim());
                bkObj.setAuthor(etUsername.getText().toString().trim());
                bkObj.setPhone(Integer.parseInt(etPhone.getText().toString().trim()));

                //dbRef.push().setValue(bkObj);
               // dbRef.child("Book1").setValue(bkObj);
                Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                dbRef.child(String.valueOf(maxId+1)).setValue(bkObj);
                ClearControls();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
        }
    }








}


