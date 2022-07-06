package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_category extends AppCompatActivity {
    RecyclerView edit_category_recycler;
    DatabaseReference databaseRef;
    editCategory_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        edit_category_recycler = findViewById(R.id.editCategoryRecyclerView);
        databaseRef = FirebaseDatabase.getInstance().getReference("Categories");
        edit_category_recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Categories> options= new FirebaseRecyclerOptions.Builder<Categories>()
                .setQuery(databaseRef,Categories.class).build();

        adapter = new editCategory_adapter(options);
        edit_category_recycler.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}