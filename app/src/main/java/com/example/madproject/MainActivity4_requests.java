package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4_requests extends AppCompatActivity {

    EditText etBook, etAuthor, etUsername, etPhone;
    Button btn_delete, btn_viewAll, btn_update;
    DatabaseReference dbRef;
    Book bkObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_requests);

        etBook = findViewById(R.id.etBook);
        etAuthor = findViewById(R.id.etAuthor);
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        btn_delete = findViewById(R.id.btn_delete);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        btn_update = findViewById(R.id.btn_update);

        bkObj = new Book(); //create new member object
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Member");




    }

    public void ClearControls() {
        etBook.setText("");
        etAuthor.setText("");
        etUsername.setText("");
        etPhone.setText("");

    }

    public void ShowRequest(View view){
        DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child("Book").child("Book1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull   DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    etBook.setText(snapshot.child("book").getValue().toString());
                    etAuthor.setText(snapshot.child("author").getValue().toString());
                    etUsername.setText(snapshot.child("username").getValue().toString());
                    etPhone.setText(snapshot.child("phone").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No source to display", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //update

    public void UpdateRequest(View view){
        DatabaseReference updRef= FirebaseDatabase.getInstance().getReference().child("Book");
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.hasChild("Book1")){
                    bkObj.setBook(etBook.getText().toString().trim());
                    bkObj.setBook(etAuthor.getText().toString().trim());
                    bkObj.setBook(etUsername.getText().toString().trim());
                    bkObj.setBook(etPhone.getText().toString().trim());

                    dbRef=FirebaseDatabase.getInstance().getReference().child("Book").child("Book1");
                    dbRef.setValue(bkObj);

                    //feedback to user
                    Toast.makeText(getApplicationContext(),"Book details updated successfully",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    //delete user
    public void DeleteRequest(View view){
        DatabaseReference delRef= FirebaseDatabase.getInstance().getReference().child("Book");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild("Book1")){
                    dbRef=FirebaseDatabase.getInstance().getReference().child("Book").child("Book1");
                    dbRef.removeValue();

                    Toast.makeText(getApplicationContext(),"Book details updated successfully",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}