package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Books_Ratings_Reviews extends AppCompatActivity {
    TextView ratecount_ratings,tv_showRatings;
    EditText et_write_review, et_review_pname ;
    Button btn_ratings_save;
    RatingBar rating_bar_rating;
    float rateValue;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_ratings_reviews);
        ratecount_ratings = findViewById(R.id.ratecount_ratings);
        rating_bar_rating = findViewById(R.id.rating_bar_rating);
        et_write_review = findViewById(R.id.et_write_review);
        btn_ratings_save = findViewById(R.id.btn_ratings_save);
        tv_showRatings = findViewById(R.id.tv_showRatings);
        et_review_pname = findViewById(R.id.et_review_pname);

        rating_bar_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rateValue = rating_bar_rating.getRating();

                //set rate count
                if(rateValue<=1 && rateValue>0)
                    ratecount_ratings.setText("Bad" +"\n"+rateValue +"/5");
                else if (rateValue<=2 && rateValue>1)
                    ratecount_ratings.setText("Ok" +"\n"+rateValue +"/5");
                else if (rateValue<=3 && rateValue>2)
                    ratecount_ratings.setText("Good" +"\n" +rateValue +"/5");
                else if (rateValue<=4 && rateValue>3)
                    ratecount_ratings.setText("Very Good"+"\n" +rateValue +"/5");
                else if (rateValue<=5 && rateValue>4)
                    ratecount_ratings.setText("Best" +"\n"+  rateValue +"/5");
            }

        });
        btn_ratings_save.setOnClickListener(new View.OnClickListener() {
            String names = et_review_pname.getText().toString();
            String shows = tv_showRatings.getText().toString();
            String review = et_write_review.getText().toString();
            String count = ratecount_ratings.getText().toString();

            @Override
            public void onClick(View v) {
                temp = ratecount_ratings.getText().toString();
                String names = et_review_pname.getText().toString();
                String shows = tv_showRatings.getText().toString();
                String review = et_write_review.getText().toString();
                String count = ratecount_ratings.getText().toString();
                tv_showRatings.setText(et_review_pname.getText()+"\n"+"Your Rating : \n" + temp + "\n" + et_write_review.getText());
                et_write_review.setText("");
                rating_bar_rating.setRating(0);
                ratecount_ratings.setText("");
                et_review_pname.setText("");


            }
        });
    }
}