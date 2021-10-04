package com.example.madproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class CartAdapter extends FirebaseRecyclerAdapter<ShoppingCart,CartAdapter.MyViewHolder> {

    public CartAdapter(@NonNull FirebaseRecyclerOptions<ShoppingCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull ShoppingCart model) {
        holder.cartBookTitle.setText(model.getBookTitle());
        holder.cartBookPrice.setText(model.getPrice());

        holder.cartItemDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.cartBookTitle.getContext());
                builder.setTitle("Remove Cart Item");
                builder.setMessage("Remove Item ?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Cart")
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout_list,parent,false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cartBookTitle, cartBookPrice;
        ImageButton cartItemDeleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cartBookPrice = itemView.findViewById(R.id.cartBookPrice);
            cartBookTitle = itemView.findViewById(R.id.cartBookTitle);
            cartItemDeleteBtn = itemView.findViewById(R.id.cartItemDeleteBtn);
        }
    }
}
