package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.madproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class offers extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference dbRef;
    MyAdapter myAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

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