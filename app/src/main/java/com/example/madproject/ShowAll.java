package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ShowAll extends AppCompatActivity {

    //declare the all the UI elements
    EditText etbNo, etbName, etStreet, etDistrict;
    Button  btn_d_delete ,btn_d_update,btn_d_show, btn_paynow;

    //create a reference for the delivery details class
    DeliveryDetails deliveryObj;

    //define database reference
    DatabaseReference dbRef;


    // Global variables to hold user data inside this activity
    String d_num , d_name , d_street , d_district;

    //declare variable for auto increment
    //long max_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        dbRef = FirebaseDatabase.getInstance().getReference("DeliveryDetails");

        //find all the UI elements from the interface

        etbNo = findViewById(R.id.et_dno);
        etbName = findViewById(R.id.et_building_name);
        etStreet = findViewById(R.id.et_street);
        etDistrict = findViewById(R.id.et_district);

        btn_d_update = findViewById(R.id.btn_d_update);
        btn_d_delete = findViewById(R.id.btn_d_delete);
        btn_d_show = findViewById(R.id.btn_d_show);
        btn_paynow = findViewById(R.id.btn_paynow);

        //Create an object
       deliveryObj = new DeliveryDetails();


        btn_paynow.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent a = new Intent(   ShowAll.this, Payment.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });



    }

    //Add a click listener to the Show button to view each user details
    public void ShowAddress(View view) {
        //Getting database reference to read the data

        Intent intent = getIntent();
         d_num = intent.getStringExtra("no");
         d_name = intent.getStringExtra("bName");
         d_street = intent.getStringExtra("street");
         d_district = intent.getStringExtra("district");

        etbNo.setText(d_num);
        etbName.setText(d_name);
        etStreet.setText(d_street);
        etDistrict.setText(d_district);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("DeliveryDetails").child(etbNo.getText().toString().trim());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Check can access for the particular reference and  read data set in to each input controller
                if (dataSnapshot.hasChildren()) {
                         Toast.makeText(getApplicationContext(),"Check again Postal address correct or not",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

  //Update User Address
    public void UpdateAddress(View view){
        if(isBnameChanged() || isStreetChanged() || isDistrictChanged()){
            Toast.makeText(this,"Address has been updated", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"Address is same cannot be updated", Toast.LENGTH_LONG).show();

    }

    private boolean isDistrictChanged() {
        if(!d_district.equals(etDistrict.getText().toString())){
            dbRef.child(d_num).child("district").setValue(etDistrict.getText().toString());
            d_district = etDistrict.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isStreetChanged() {
        if(!d_street.equals(etStreet.getText().toString())){
            dbRef.child(d_num).child("street").setValue(etStreet.getText().toString());
            d_street = etStreet.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isBnameChanged() {
        if(!d_name.equals(etbName.getText().toString())){
            dbRef.child(d_num).child("bName").setValue(etbName.getText().toString());
            d_name = etbName.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }


    //Add a click listener to the DELETE button and start coding
    public void DeleteAddress(View view) {
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("DeliveryDetails");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(etbNo.getText().toString().trim())){
                    dbRef=FirebaseDatabase.getInstance().getReference().child("DeliveryDetails").child(etbNo.getText().toString().trim());
                    dbRef.removeValue();

                    Toast.makeText(getApplicationContext(), "Address Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}