package com.example.mynewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class category_insertion extends AppCompatActivity {

    EditText et_add_cate_name;
    Button btn_show_cate ,btn_add_new_cate;
    DatabaseReference cateDbRef;
    Categories categoryObj;
    long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_insertion);
        et_add_cate_name = findViewById(R.id.et_add_cate_name);
        btn_add_new_cate= findViewById(R.id.btn_add_new_cate);
        btn_show_cate=findViewById(R.id.btn_show_cate);
        btn_show_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RetreiveData.class));

            }
        });
        categoryObj = new Categories();
        btn_add_new_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategoryItem();
            }
        });
    }
    //clear edit text after insertion
    public void clearEditText(){
        et_add_cate_name.setText("");
    }

    //add category item function
    public void addCategoryItem() {
        cateDbRef = FirebaseDatabase.getInstance().getReference().child("Categories");
        cateDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    id = (snapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        try {
            if(TextUtils.isEmpty(et_add_cate_name.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a category name", Toast.LENGTH_SHORT).show();
            else{
                //take inputs from user and set value
                categoryObj.setCatename(et_add_cate_name.getText().toString().trim());
                //auto increment id and store in DB
                cateDbRef.child(String.valueOf(id+1)).setValue(categoryObj);

                Toast.makeText(getApplicationContext(), "Category saved successfully", Toast.LENGTH_SHORT).show();
                clearEditText();
            }
        }
        catch(NullPointerException e){

        }
    }
}