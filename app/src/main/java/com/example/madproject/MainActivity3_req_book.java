package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ValueEventRegistration;

public class MainActivity3_req_book extends AppCompatActivity {

    //declare ui elements
    EditText etBook, etAuthor, etUsername, etPhone;
    Button btn_submit;
//database reference
    DatabaseReference dbRef;

    FirebaseDatabase root;

    newBookRequest bkObj; //book class reference
    long maximumId = 0; //for auto increment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_req_book);

        etBook = findViewById(R.id.etBook);
        etAuthor = findViewById(R.id.etAuthor);
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);

        btn_submit = findViewById(R.id.btn_submit);

        bkObj = new newBookRequest(); //create new member object




    }

    //clear all user inputs after submit
    public void ClearControls() {
        etBook.setText("");
        etAuthor.setText("");
        etUsername.setText("");
        etPhone.setText("");

    }

    //dbRef.child("Member").setValue(memObj);
    //Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();

    //set onClickListner to submit btn_request


    public void CreateRequest(View view) {

        root = FirebaseDatabase.getInstance();
        dbRef = root.getReference().child("newBookRequest");


        //validations

        if (TextUtils.isEmpty(etBook.getText().toString()))
            Toast.makeText(getApplicationContext(), "Book name field can not be empty", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etAuthor.getText().toString()))
            Toast.makeText(getApplicationContext(), "Authorname field can not be empty", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etUsername.getText().toString()))
            Toast.makeText(getApplicationContext(), "Your name field can not be empty", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etPhone.getText().toString()))
            Toast.makeText(getApplicationContext(), "Contact number field can not be empty", Toast.LENGTH_SHORT).show();
        else {
            //assigning user input values to Book object
            bkObj.setBookName(etBook.getText().toString().trim());
            bkObj.setAuthorName(etAuthor.getText().toString().trim());
            bkObj.setUsername(etUsername.getText().toString().trim());
            bkObj.setContactNumber(Integer.valueOf(etPhone.getText().toString().trim()));

            String bookname = etBook.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();


            //pass values to the db
            dbRef.child(bookname).setValue(bkObj);
            //faadback response to the user
            Toast.makeText(getApplicationContext(), "Book request sent successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity4_requests.class);

            intent.putExtra("bookName",bookname);
            intent.putExtra("authorName",author);
            intent.putExtra("username",username);
            intent.putExtra("contactNumber",phone);


            Toast.makeText(getApplicationContext(), " Successfully", Toast.LENGTH_LONG).show();

            startActivity(intent);
            ClearControls();
        }


    }
}









