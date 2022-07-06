package com.example.mynewapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class editCategory_adapter extends FirebaseRecyclerAdapter<Categories,editCategory_adapter.ViewHolder> {

    public editCategory_adapter(@NonNull FirebaseRecyclerOptions<Categories> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Categories model) {
        holder.tv_categoryName.setText(model.getCatename());

        holder.delete_btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(holder.tv_categoryName.getContext());
                builder.setTitle("Delete Category");
                builder.setMessage("Do you want to delete category ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Categories")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_edit,parent,false);
        return new editCategory_adapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_categoryName;
        Button delete_btn_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_categoryName = itemView.findViewById(R.id.tv_categoryName);
            delete_btn_category = itemView.findViewById(R.id.delete_btn_category);
        }
    }
}
