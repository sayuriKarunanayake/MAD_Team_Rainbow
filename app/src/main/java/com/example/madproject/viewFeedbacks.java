package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewFeedbacks extends AppCompatActivity {

    ListView myListView;
    List<Feedback> feedbackList;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedbacks);

        myListView=findViewById(R.id.myListView);
        feedbackList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedbackList.clear();

                for (DataSnapshot feedbackDatasnap : snapshot.getChildren()){
                    Feedback fbObj = feedbackDatasnap.getValue(Feedback.class);
                    feedbackList.add(fbObj);
                }

                ListAdapter adapter = new com.example.login.ListAdapter(viewFeedbacks.this,feedbackList);
                myListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
