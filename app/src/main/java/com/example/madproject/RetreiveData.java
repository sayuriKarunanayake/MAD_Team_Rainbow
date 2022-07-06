package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetreiveData extends AppCompatActivity {
    RecyclerView categoryRecyclerView;
    categoryAdapter adapter;
    DatabaseReference dbreference;
    Button btn_edit_cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        dbreference = FirebaseDatabase.getInstance().getReference("Categories");
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Categories> options = new FirebaseRecyclerOptions.Builder<Categories>()
                .setQuery(dbreference,Categories.class).build();

        adapter = new categoryAdapter(options);
        categoryRecyclerView.setAdapter(adapter);
        //add edit intent to button
        btn_edit_cate=findViewById(R.id.btn_edit_cate);
        btn_edit_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),edit_category.class));

            }
        });
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