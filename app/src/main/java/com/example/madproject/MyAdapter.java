package com.example.mynewapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import java.util.HashMap;
import java.util.Map;


import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final model model)
    {
     
	//get details from model class and set details
       holder.bookname.setText(model.getBookname());
       holder.bookcategory.setText(model.getBookcategory());
       holder.bookdes.setText(model.getBookdes());
       holder.bookprice.setText(model.getBookprice());
       Glide.with(holder.img2.getContext()).load(model.getImgurl()).into(holder.img2);

holder.edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img2.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                    .setExpanded(true,2000) //size for expand dialogplus
                                    .create();

//create views to update details from dialogplus
                            View myview=dialogPlus.getHolderView();
                            final EditText imgurl=myview.findViewById(R.id.et_nwbook_url_update);
                            final EditText bookname=myview.findViewById(R.id.et_nwbook_name_update);
                            final EditText bookcategory=myview.findViewById(R.id.et_nwbook_category_update);
                            final EditText bookdes=myview.findViewById(R.id.et_nwbook_description_update);
			    final EditText bookprice=myview.findViewById(R.id.et_nwbook_price_update);
                            Button submit=myview.findViewById(R.id.btn_updateitem);


                            imgurl.setText(model.getImgurl());
                            bookname.setText(model.getBookname());
                            bookcategory.setText(model.getBookcategory());
                            bookdes.setText(model.getBookdes());
			    bookprice.setText(model.getBookprice());

//show from dialogplus
                            dialogPlus.show();

                            //using map update details when the press submit button
                                submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("imgurl",imgurl.getText().toString());
                                        map.put("bookname",bookname.getText().toString());
                                        map.put("bookcateory",bookcategory.getText().toString());
                                        map.put("bookdes",bookdes.getText().toString());
 					map.put("bookprice",bookprice.getText().toString());

				//use incremental method
                                        FirebaseDatabase.getInstance().getReference().child("NewBooks")
                                                .child(getRef(position).getKey()).updateChildren(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
					
                                                    public void onSuccess(Void aVoid) {
                                                        dialogPlus.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        dialogPlus.dismiss();
                                                    }
                                                });
                                    }
                                });


                        }
                    });

		//when click delete btn
                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
		//alert dialog
                            AlertDialog.Builder builder=new AlertDialog.Builder(holder.img2.getContext());
                            builder.setTitle("Delete Panel");
                            builder.setMessage("Delete...?");

	//when user press yes dialog box it wil remove from database 
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    FirebaseDatabase.getInstance().getReference().child("NewBooks")
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

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
	//create views
        CircleImageView img2;
        ImageView edit,delete;
        TextView bookname,bookcategory,bookdes,bookprice;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img2=(CircleImageView) itemView.findViewById(R.id.img_book_item);
            bookname=(TextView)itemView.findViewById(R.id.tv_book_item_name);
            bookcategory=(TextView)itemView.findViewById(R.id.tv_book_item_category);
            bookdes=(TextView)itemView.findViewById(R.id.tv_book_item_description);
            bookprice=(TextView)itemView.findViewById(R.id.tv_book_item_price);

//create vews to edit and delte images
            edit=(ImageView)itemView.findViewById(R.id.edit_details);
            delete=(ImageView)itemView.findViewById(R.id.delete_bin);
        }
    }
}















































