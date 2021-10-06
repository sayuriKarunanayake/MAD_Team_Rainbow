package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CardPayment extends AppCompatActivity {

    //declare attributes
    EditText CardNo,Nic, CnvNo;
    Button btn_card_confirm, btn_card_show;
    RadioButton VisaCard, MasterCard;

    //declare object
    PaymentDetails payObj;

    FirebaseDatabase database;
    DatabaseReference reference;

    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        //find all the UI elements from the interface
        btn_card_confirm = findViewById(R.id.btn_card_confirm);
        btn_card_show = findViewById(R.id.btn_card_show);

        VisaCard = findViewById(R.id.rbtn_visa_card);
        MasterCard = findViewById(R.id.rbtn_master_card);
        Nic = findViewById(R.id.et_card_nic);
        CardNo = findViewById(R.id.et_card_no);
        CnvNo = findViewById(R.id.et_cvn);

        //Create an object
        payObj = new PaymentDetails();

        reference = database.getInstance().getReference().child("CardPayment");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    i = (int)dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }








        });


        btn_card_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m1 = VisaCard.getText().toString();
                String m2 = MasterCard.getText().toString();
                //Take inputs from the user and assign these values to the Payment object
                payObj.setNic(Nic.getText().toString());
                payObj.setCardNo(CardNo.getText().toString());
                payObj.setCnvNo(CnvNo.getText().toString());
                reference.child(String.valueOf(i+1)).setValue(payObj);
                if(VisaCard.isChecked()){
                    payObj.setCardType((m1));
                    reference.child(String.valueOf(i+1)).setValue(payObj);
                }else{
                    payObj.setCardType(m2);
                    reference.child(String.valueOf(i+1)).setValue(payObj);
                }
                //Feedback to the user via Toast message
                Toast.makeText(getApplicationContext(), "Payment saved Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Payment.class);


            }



        });

        btn_card_show.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent a = new Intent(   CardPayment.this, MainActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
    }
}


