package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.madproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class offers extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference dbRef;
    MyAdapter myAdaptor;
    ImageButton toolbarcartbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        toolbarcartbtn = findViewById(R.id.toolbarcartbtn);
        recyclerView = findViewById(R.id.recycler_view);
        dbRef = FirebaseDatabase.getInstance().getReference("Offers");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //using firebase recyclerview
        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(dbRef, Data.class)
                        .build();

        myAdaptor = new MyAdapter(options);
        recyclerView.setAdapter(myAdaptor);

        toolbarcartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(   offers.this, shopping_cart.class);
                cart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(cart);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        myAdaptor.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdaptor.stopListening();
    }
}