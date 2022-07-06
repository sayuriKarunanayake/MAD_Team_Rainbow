package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_book extends AppCompatActivity {
    //array list data
    RecyclerView recycler_add;
   // DatabaseReference productprojectDbRef;
    myadapter madapter;
    FloatingActionButton floatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);
//set title
        setTitle("Books");

        recycler_add = (RecyclerView) findViewById(R.id.recycler_add);
        // productprojectDbRef = FirebaseDatabase.getInstance().getReference();
        recycler_add.setLayoutManager(new LinearLayoutManager(this));

        //send data to firebase
        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("NewBooks"), model.class)
                .build();

        madapter = new myadapter(options);
        recycler_add.setAdapter(madapter);

//set float button to add new book details
        floatbtn = (FloatingActionButton) findViewById(R.id.btn_floating);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),add_data.class));

            }
        });


    }
//start and stop adapters
    @Override
    protected void onStart() {
        super.onStart();
        madapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        madapter.stopListening();

    }
}

