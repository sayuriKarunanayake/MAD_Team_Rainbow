package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class add_offers extends AppCompatActivity {

    ImageButton chooseimage_btn_offers;
    Button save_offer_btn;
    ImageView offer_imageV;
    StorageReference imgdb;
    public Uri imageuri;

    //edit text fields
    EditText et_bookname_offer,offer_isbn,et_offerprice,et_offerdescript;
    Data data;
    DatabaseReference dbref;

    long maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);

        imgdb = FirebaseStorage.getInstance().getReference("images");

        dbref = FirebaseDatabase.getInstance().getReference().child("Offers");

        chooseimage_btn_offers = findViewById(R.id.chooseimage_btn_offers);
        save_offer_btn = findViewById(R.id.save_offer_btn);
        offer_imageV = findViewById(R.id.offer_imageV);

        //catching Edit text ui elements
        et_bookname_offer = findViewById(R.id.et_bookname_offer);
        offer_isbn= findViewById(R.id.offer_isbn);
        et_offerprice = findViewById(R.id.et_offerprice);
        et_offerdescript = findViewById(R.id.et_offerdescript);

        data = new Data();
        //onclick to choose images from storage
        chooseimage_btn_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filechooser();
            }
        });
        //onclick to upload data
        save_offer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fileuploader();
            }
        });
    }
    //method to clear all user inputs after button clicking
    public void ClearControls(){
        offer_isbn.setText("");
        et_bookname_offer.setText("");
        et_offerprice.setText("");
        et_offerdescript.setText("");
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void Fileuploader() {
        String imageid;
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        try {
            //check whether fields are empty
            if (TextUtils.isEmpty(et_bookname_offer.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter book title", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(offer_isbn.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter ISBN number of book", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(et_offerprice.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter offer price", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(et_offerdescript.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a offer description", Toast.LENGTH_SHORT).show();
            else{
                imageid = System.currentTimeMillis()+"."+getExtension(imageuri);
                //set data
                data.setBookname(et_bookname_offer.getText().toString().trim());
                data.setIsbn(offer_isbn.getText().toString().trim());
                data.setOfferprice(et_offerprice.getText().toString().trim());
                data.setOfferdescpt(et_offerdescript.getText().toString().trim());
                data.setImageid(imageid);
                dbref.child(String.valueOf(maxid+1)).setValue(data);
                StorageReference ref = imgdb.child(imageid);
                ref.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(getApplicationContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                                ClearControls();
                            }
                        });
                    }
                });
            }
        }
        catch(NumberFormatException e){ }
    }
    private void Filechooser(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri = data.getData();
            offer_imageV.setImageURI(imageuri);

        }
    }
}