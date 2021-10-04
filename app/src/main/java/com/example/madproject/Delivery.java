package com.example.madproject;

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

public class Delivery extends AppCompatActivity {

    //declare the all the UI elements
    EditText etbNo, etbName, etStreet, etDistrict;
    Button btn_d_continue, btn_d_change;

    //create a reference for the delivery details class
    DeliveryDetails deliveryObj;

    //define database reference
    DatabaseReference dbRef;
    FirebaseDatabase root;

    //declare variable for auto increment
    long max_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        //find all the UI elements from the interface

        etbNo = findViewById(R.id.et_dno);
        etbName = findViewById(R.id.et_building_name);
        etStreet = findViewById(R.id.et_street);
        etDistrict = findViewById(R.id.et_district);

        btn_d_continue = findViewById(R.id.btn_d_continue);
        btn_d_change = findViewById(R.id.btn_d_change);


        //Create an object
        deliveryObj = new DeliveryDetails();



        btn_d_change.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent a = new Intent(   Delivery.this, Payment.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

      }

    // Method to clear all user inputs
    public void ClearControls() {
        etbNo.setText("");
        etbName.setText("");
        etStreet.setText("");
        etDistrict.setText("");

    }

    //Set on click listener to the Continue button and start coding to insert
        public void CreateAddress(View view) {
          //define a table name using a child method
                root = FirebaseDatabase.getInstance();
                   dbRef = root.getReference().child("DeliveryDetails");


        //Check some validation whether the fields are empty or not
        if (TextUtils.isEmpty(etbNo.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter an No", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etbName.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter an Business or building name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etStreet.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter a Street", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etDistrict.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter a District", Toast.LENGTH_SHORT).show();
        else {

            //Take inputs from the user and assign these values to the Delivery object
            deliveryObj.setNo(etbNo.getText().toString().trim());
            deliveryObj.setbName(etbName.getText().toString().trim());
            deliveryObj.setStreet(etStreet.getText().toString().trim());
            deliveryObj.setDistrict(etDistrict.getText().toString().trim());
            String Num = etbNo.getText().toString().trim();
            String B_name = etbName.getText().toString().trim();
            String D_street = etStreet.getText().toString().trim();
            String D_district = etDistrict.getText().toString().trim();

            //pass the values to the database
                  dbRef.child(Num).setValue(deliveryObj);

            //Feedback to the user via Toast message
                  Toast.makeText(getApplicationContext(), "Address saved Successfully", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getApplicationContext(), Payment.class);

            intent.putExtra("no",Num);
            intent.putExtra("bName",B_name);
            intent.putExtra("street",D_street);
            intent.putExtra("district",D_district);

            Toast.makeText(getApplicationContext()," Successfully",Toast.LENGTH_LONG).show();

            startActivity(intent);


            ClearControls();
        }
    }
}