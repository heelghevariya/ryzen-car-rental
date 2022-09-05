package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.GetUserReviewModal;
import com.example.ryzencarrent.Modals.RatingModal;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewRatingReviewScreen extends AppCompatActivity {
    ImageView imgBack;
    TextInputEditText message;
    TextView search_btn, txtStatus;
    RatingBar r_rating;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating_review_screen);
        appPreferences = new AppPreferences(getApplicationContext());

        message = findViewById(R.id.message);
        search_btn = findViewById(R.id.search_btn);
        r_rating = findViewById(R.id.r_rating);
        txtStatus = findViewById(R.id.txtStatus);
        imgBack = findViewById(R.id.back);

        message.setBackground(null);
        getData();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI1 = retrofit1.create(RetrofitAPI.class);

                Call<RatingModal> call1 = retrofitAPI1.removereviewrating(appPreferences.getuidpref());
                call1.enqueue(new Callback<RatingModal>() {
                    @Override
                    public void onResponse(Call<RatingModal> call, Response<RatingModal> response) {
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                RatingModal ratingModal = response.body();

                                startActivity(new Intent(ViewRatingReviewScreen.this, ReviewRatingScreen.class));
                                finish();


                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<RatingModal> call, Throwable t) {
                        // displaying an error message in toast
                        Toast.makeText(ViewRatingReviewScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<GetUserReviewModal>> call = retrofitAPI.getuserreviewraring(appPreferences.getuidpref());
        call.enqueue(new Callback<List<GetUserReviewModal>>() {
            @Override
            public void onResponse(Call<List<GetUserReviewModal>> call, Response<List<GetUserReviewModal>> response) {
                if (response.isSuccessful()) {
                    List<GetUserReviewModal> getUserReviewModalList = response.body();

                    String getReview = getUserReviewModalList.get(0).getReview();
                    int getRating = getUserReviewModalList.get(0).getRating();
                    int getStatus = getUserReviewModalList.get(0).getStatus();


                    if (getReview.equals("")) {
                        message.setText("---");

                    } else {
                        message.setText(getReview);

                    }
                    r_rating.setRating(getRating);

                    if (getStatus == 0) {
                        txtStatus.setText("Inactive");
                        txtStatus.setTextColor(getResources().getColor(R.color.red));

                    } else if (getStatus == 1) {
                        txtStatus.setText("Active");
                        txtStatus.setTextColor(getResources().getColor(R.color.green));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetUserReviewModal>> call, Throwable t) {
                Toast.makeText(ViewRatingReviewScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}