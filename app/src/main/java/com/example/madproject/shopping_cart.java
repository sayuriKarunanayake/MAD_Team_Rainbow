package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.madproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class shopping_cart extends AppCompatActivity {
    RecyclerView RecyclerViewCart;
    DatabaseReference dbRef2;
    CartAdapter adapterCart;
    Button chechout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        chechout_btn = findViewById(R.id.chechout_btn);
        RecyclerViewCart = findViewById(R.id.RecyclerViewCart);
        dbRef2 = FirebaseDatabase.getInstance().getReference("Cart");
        RecyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ShoppingCart> options=
                new FirebaseRecyclerOptions.Builder<ShoppingCart>()
                        .setQuery(dbRef2, ShoppingCart.class)
                        .build();

        adapterCart = new CartAdapter(options);
        RecyclerViewCart.setAdapter(adapterCart);

        chechout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliv = new Intent(   shopping_cart.this, Delivery.class);
                deliv.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(deliv);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterCart.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapterCart.stopListening();
    }
}