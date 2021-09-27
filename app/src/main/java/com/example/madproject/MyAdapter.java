package com.example.madproject;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter <Data,MyAdapter.MyViewHolder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {

        holder.isbn_offer_tv.setText(model.getIsbn());
        holder.booktitle_offer_tv.setText(model.getBookname());
        holder.offer_price_tv.setText(model.getOfferprice());
        holder.offer_descript_tv.setText(model.getOfferdescpt());
        //image get to log
        Log.d("Value","Image:"+model.getImageid());
        // get image to page
        StorageReference sr = FirebaseStorage.getInstance().getReference().child("images");
        sr.child(model.getImageid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.image.getContext()).load(uri).into(holder.image);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
        return new MyViewHolder(v);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView isbn_offer_tv,booktitle_offer_tv,offer_price_tv,offer_descript_tv;
        CircleImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            isbn_offer_tv = itemView.findViewById(R.id.isbn_offer_text);
            booktitle_offer_tv = itemView.findViewById(R.id.booktitle_offer_text);
            offer_price_tv = itemView.findViewById(R.id.offer_price_text);
            offer_descript_tv = itemView.findViewById(R.id.offer_descript_text);
            image = itemView.findViewById(R.id.image1);

        }
    }
}
