package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Review_details extends AppCompatActivity {

    //declare the all the UI elements
    EditText etRName, etREmail, etRComment;
    Button btnRSubmit, btnRUpdate;

    //create a reference for the class
    ReviewDetails reviewObj;

    //define database reference
    DatabaseReference dbRef;
    FirebaseDatabase root;


    // Global variables to hold user data inside this activity
    String r_name, r_email, r_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        //find all the UI elements from the interface

        etRName = findViewById(R.id.et_review_name);
        etREmail = findViewById(R.id.et_review_email);
        etRComment = findViewById(R.id.et_review_comment);


        btnRSubmit = findViewById(R.id.btn_review_submit);
        btnRUpdate = findViewById(R.id.btn_review_update);

        //Create an object
        reviewObj = new ReviewDetails();

    }


    //Set on click listener to the Submit button and start coding to insert
    public void CreateReview(View view) {

        //define a table name using a child method
        root = FirebaseDatabase.getInstance();
        dbRef = root.getReference().child("ReviewDetails");


        //Check some validation whether the fields are empty or not
        if (TextUtils.isEmpty(etRName.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etREmail.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter an Email", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etRComment.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter your Comment", Toast.LENGTH_SHORT).show();

        else {

            //Take inputs from the user and assign these values to the Delivery object
            reviewObj.setName(etRName.getText().toString().trim());
            reviewObj.setEmail(etREmail.getText().toString().trim());
            reviewObj.setComment(etRComment.getText().toString().trim());

            String UserName = etRName.getText().toString().trim();



            //pass the values to the database
            dbRef.child(UserName).setValue(reviewObj);

            //Feedback to the user via Toast message
            Toast.makeText(getApplicationContext(), "Review saved Successfully", Toast.LENGTH_SHORT).show();



        }


    }

}

