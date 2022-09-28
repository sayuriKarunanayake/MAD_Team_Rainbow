package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Button  btn_viewAll, btn_update;

    //database reference
    DatabaseReference dbRef;

    //reference to book class
    newBookRequest bkObj;

    //global variables to store user entered data
    String b_book, b_author, b_name, b_number;

    //variable for auto increment
    long max_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_requests);

        dbRef = FirebaseDatabase.getInstance().getReference().child("newBookRequest");

        //ui elements from the interface
        etBook = findViewById(R.id.etBook);
        etAuthor = findViewById(R.id.etAuthor);
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);


        btn_viewAll = findViewById(R.id.btn_viewAll);
        btn_update = findViewById(R.id.btn_update);

        bkObj = new newBookRequest(); //create new member object

        btn_update.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(MainActivity4_requests.this, MainActivityHome.class));
            }
        });

    }




 //view data
    public void ShowRequest(View view) {

        Intent intent = getIntent();
        b_book = intent.getStringExtra("bookName");
        b_author = intent.getStringExtra("authorName");
        b_name = intent.getStringExtra("username");
        b_number = intent.getStringExtra("contactNumber");

        etBook.setText(b_book);
        etAuthor.setText(b_author);
        etUsername.setText(b_name);
        etPhone.setText(b_number);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Book").child(etBook.getText().toString().trim());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) { //check access to reference
                    Toast.makeText(getApplicationContext(), "Incorrect book name", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //update book request

    public void UpdateRequest(View view) {

        if (isBookNameChanged() || isAuthorNameChanged() || isCustomerNameChanged() || isNumberChanged()) {
            Toast.makeText(this, "Book request has been updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "No changes found in update", Toast.LENGTH_LONG).show();

    }

    private boolean isNumberChanged() {
        if (!b_number.equals(etPhone.getText().toString())) {
            dbRef.child(b_number).child("contactNumber").setValue(etPhone.getText().toString());
            b_number = etPhone.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isCustomerNameChanged() {

        if (!b_name.equals(etUsername.getText().toString())) {
            dbRef.child(b_name).child("username").setValue(etUsername.getText().toString());
            b_name = etUsername.getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isAuthorNameChanged() {

        if (!b_author.equals(etBook.getText().toString())) {
            dbRef.child(b_author).child("authorName").setValue(etAuthor.getText().toString());
            b_author = etAuthor.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isBookNameChanged() {

        if (!b_book.equals(etBook.getText().toString())) {
            dbRef.child(b_book).child("bookName").setValue(etBook.getText().toString());
            b_book = etBook.getText().toString();
            return true;
        } else {
            return false;
        }
    }




}