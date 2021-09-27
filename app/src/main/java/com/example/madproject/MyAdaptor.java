package com.example.madproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdaptor extends FirebaseRecyclerAdapter<Data,MyAdaptor.MyViewHolder> {

    public MyAdaptor(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyAdaptor.MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Data model) {
        holder.isbn_offer_text.setText(model.getIsbn());
        holder.booktitle_offer_text.setText(model.getBookname());
        holder.offer_price_text.setText(model.getOfferprice());
        holder.offer_descript_text.setText(model.getOfferdescpt());

        //image get
        Log.d("Value","Image:"+model.getImageid());

        //get image from storage
        StorageReference sr = FirebaseStorage.getInstance().getReference().child("images");
        sr.child(model.getImageid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.image1.getContext()).load(uri).into(holder.image1);

                holder.editoffers_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DialogPlus dialogplus = DialogPlus.newDialog(holder.image1.getContext())
                                .setContentHolder(new ViewHolder(R.layout.dialog_content))
                                .setExpanded(true,1100)
                                .create();
                        //view existing data in edit panel fields
                        View myview = dialogplus.getHolderView();
                        EditText isbnoffer_editText = myview.findViewById(R.id.isbnoffer_editText);
                        EditText nameoffer_editext = myview.findViewById(R.id.nameoffer_editext);
                        EditText priceoffer_edittext = myview.findViewById(R.id.priceoffer_edittext);
                        EditText descpoffers_edittext = myview.findViewById(R.id.descpoffers_edittext);

                        Button usubmit = myview.findViewById(R.id.usubmit);

                        isbnoffer_editText.setText(model.getIsbn());
                        nameoffer_editext.setText(model.getBookname());
                        priceoffer_edittext.setText(model.getOfferprice());
                        descpoffers_edittext.setText(model.getOfferdescpt());

                        dialogplus.show();

                        //edit button onclick to update data
                        usubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("isbn",isbnoffer_editText.getText().toString());
                                map.put("bookname",nameoffer_editext.getText().toString());
                                map.put("offerdescpt",descpoffers_edittext.getText().toString());
                                map.put("offerprice",priceoffer_edittext.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("Offers")
                                        .child(Objects.requireNonNull(getRef(position).getKey()))
                                        .updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialogplus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialogplus.dismiss();
                                            }
                                        });
                            }
                        });
                    }
                });

            }
        });

        holder.deleteoffers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.image1.getContext());
                builder.setTitle("Delete Offer Item");
                builder.setMessage("Delete....?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Offers")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public MyAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_edit,parent,false);
        return new MyAdaptor.MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView isbn_offer_text,booktitle_offer_text,offer_price_text,offer_descript_text;
        CircleImageView image1;
        Button editoffers_btn,deleteoffers_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            isbn_offer_text = itemView.findViewById(R.id.isbn_offer_text);
            booktitle_offer_text = itemView.findViewById(R.id.booktitle_offer_text);
            offer_price_text = itemView.findViewById(R.id.offer_price_text);
            offer_descript_text = itemView.findViewById(R.id.offer_descript_text);
            image1 = itemView.findViewById(R.id.image1);
            editoffers_btn = itemView.findViewById(R.id.editoffers_btn);
            deleteoffers_btn = itemView.findViewById(R.id.deleteoffers_btn);
        }

    }
}
