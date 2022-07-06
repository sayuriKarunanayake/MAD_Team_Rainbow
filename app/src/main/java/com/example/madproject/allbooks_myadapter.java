package com.example.mynewapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class allbooks_myadapter extends FirebaseRecyclerAdapter<model,allbooks_myadapter.myviewholder>
{
    public allbooks_myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull allbooks_myadapter.myviewholder holder, int position, @NonNull model model) {
        holder.bookname.setText(model.getBookname());
        holder.bookcategory.setText(model.getBookcategory());
        holder.bookdes.setText(model.getBookdes());
        holder.bookprice.setText(model.getBookprice());
        Glide.with(holder.img2.getContext()).load(model.getImgurl()).into(holder.img2);

        Context context = holder.itemView.getContext();

        holder.btn_allback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Book1.class);
                context.startActivity(i);
            }
        });


    }//end of onbind method

    @NonNull

    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_allbooks,parent,false);
        return new myviewholder(view);

    }

    class myviewholder extends RecyclerView.ViewHolder

    {
        CircleImageView img2;
        Button btn_allback2;
        TextView bookname,bookcategory,bookdes,bookprice;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img2=(CircleImageView) itemView.findViewById(R.id.img_allbooks_item);
            bookname=(TextView)itemView.findViewById(R.id.tv_allbooks_name);
            bookcategory=(TextView)itemView.findViewById(R.id.tv_allbooks_category);
            bookdes=(TextView)itemView.findViewById(R.id.tv_allbooks_description);
            bookprice=(TextView)itemView.findViewById(R.id.tv_allbooks_price);

            btn_allback2=(Button) itemView.findViewById(R.id.btn_allback2);




        }

    }

}


