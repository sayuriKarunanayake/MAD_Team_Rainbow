package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.madproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_offers extends AppCompatActivity {
    RecyclerView recyclerView1;
    DatabaseReference dbRef1;
    MyAdaptor myAdaptor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offers);

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
