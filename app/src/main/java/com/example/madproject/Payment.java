package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Payment extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rButton_card_payment, rButton_bank_deposit;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        radioGroup=(RadioGroup) findViewById(R.id.rbtn_group_payment);
        rButton_card_payment=(RadioButton) findViewById(R.id.rbtn_card_payment);
        rButton_bank_deposit=(RadioButton) findViewById(R.id.rbtn_bank_deposit);

        next =(Button) findViewById(R.id.btn_payment_next);


        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Context context;
                if(rButton_card_payment.isChecked()) {
                    Intent intent= new Intent(   Payment.this, CardPayment.class);
                    startActivity(intent);
                }else if(rButton_bank_deposit.isChecked()){
                    Intent intent = new Intent( Payment.this, BankDeposit.class);
                    startActivity(intent);
                }


            }
        });
    }
}