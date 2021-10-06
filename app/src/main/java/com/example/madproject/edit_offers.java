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

public class edit_offers extends AppCompatActivity {
    RecyclerView recyclerView1;
    DatabaseReference dbRef1;
    MyAdaptor myAdaptor1;
    ImageButton btn_addoffers, toolbarcartbtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offers);

        toolbarcartbtn2 = findViewById(R.id.toolbarcartbtn2);
        btn_addoffers = findViewById(R.id.btn_addoffers);
        recyclerView1 = findViewById(R.id.recycler_view1);
        dbRef1 = FirebaseDatabase.getInstance().getReference("Offers");
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        //using firebase recyclerview
        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(dbRef1, Data.class)
                        .build();

        myAdaptor1 = new MyAdaptor(options);
        recyclerView1.setAdapter(myAdaptor1);

        btn_addoffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addoffers = new Intent(   edit_offers.this, add_offers.class);
                addoffers.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(addoffers);
            }
        });

        toolbarcartbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(   edit_offers.this, shopping_cart.class);
                cart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(cart);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        myAdaptor1.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdaptor1.stopListening();
    }
}
