package com.example.login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Feedback> feedbackList;


    public ListAdapter(Activity mContext, List<Feedback> feedbackList){
        super(mContext,R.layout.activity_view_feedbacks,feedbackList);
        this.mContext= mContext;
        this.feedbackList=feedbackList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView=inflater.inflate(R.layout.activity_view_feedbacks, null,true);

        TextView tv_Message = listItemView.findViewById(R.id.tv_Message);

        Feedback feedback= feedbackList.get(position);

        tv_Message.setText(feedback.getMessage());
        return listItemView;

    }
}
