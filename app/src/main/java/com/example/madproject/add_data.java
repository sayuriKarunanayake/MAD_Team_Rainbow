package com.example.mynewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class add_data extends AppCompatActivity {
		EditText bookname,bookcategory,bookdes,bookprice,imgurl;
		Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
		
		//create views
		bookname=(EditText)findViewById(R.id.et_name);
        bookcategory=(EditText)findViewById(R.id.et_bk_category);
        bookdes=(EditText)findViewById(R.id.et_description);
        bookprice=(EditText)findViewById(R.id.et_Price);
		imgurl=(EditText)findViewById(R.id.et_imgurl);

        back=(Button)findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Add_new_book.class));
            }
        });
        submit=(Button)findViewById(R.id.btn_back_add);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });

    }
//create method to add data
    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("bookname",bookname.getText().toString());
        map.put("bookcategory",bookcategory.getText().toString());
        map.put("bookdes",bookdes.getText().toString());
        map.put("bookprice",bookprice.getText().toString());
		map.put("imgurl",imgurl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("NewBooks").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       bookname.setText("");
                       bookcategory.setText("");
                       bookdes.setText("");
                       bookprice.setText("");
					   imgurl.setText("");
                        Toast.makeText(getApplicationContext(),"Details Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });
	

    }
}