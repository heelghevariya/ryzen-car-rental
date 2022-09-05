package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.SubmitReview;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewRatingScreen extends AppCompatActivity {

    TextInputEditText message;
    TextInputLayout messagelay;
    boolean valid = false;
    ImageView imgBack;
    RatingBar r_rating;
    TextView search_btn;
    AppPreferences appPreferences;
    SubmitReview submitReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating_screen);
        appPreferences = new AppPreferences(getApplicationContext());

        message = findViewById(R.id.message);
        messagelay = findViewById(R.id.messagel);
        imgBack = findViewById(R.id.back);
        search_btn = findViewById(R.id.search_btn);
        r_rating = findViewById(R.id.r_rating);

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid) {
                    search_btn.setEnabled(true);
                    search_btn.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String messageInput = messagelay.getEditText().getText().toString().trim();

                if (messageInput.length() > 100) {
                    messagelay.setError("Message is too long");
                    valid = false;
                    search_btn.setEnabled(false);
                    search_btn.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    messagelay.setError(null);
                    valid = true;
                    search_btn.setEnabled(true);
                    search_btn.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rate = (int) r_rating.getRating();
                String mess = message.getText().toString();

                if (rate != 0 || !mess.equals("")) {
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(config.Email_signup_BASE_URL)
                            // on below line we are calling add Converter
                            // factory as GSON converter factory.
                            .addConverterFactory(GsonConverterFactory.create())
                            // at last we are building our retrofit builder.
                            .build();
                    // below line is to create an instance for our retrofit api class.
                    RetrofitAPI retrofitAPI1 = retrofit1.create(RetrofitAPI.class);

                    Call<SubmitReview> call1 = retrofitAPI1.addreviewrating(appPreferences.getuidpref(), mess, rate);
                    call1.enqueue(new Callback<SubmitReview>() {
                        @Override
                        public void onResponse(Call<SubmitReview> call, Response<SubmitReview> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    submitReview = response.body();
                                    Intent intent = new Intent(ReviewRatingScreen.this, ViewRatingReviewScreen.class);
                                    startActivity(intent);
                                    finish();
                                  /*  if (ratingModal.getStatus()==0) {
                                        startActivity(new Intent(HomeScreen.this, ReviewRatingScreen.class));

                                    }else if(ratingModal.getStatus()==1){
                                        startActivity(new Intent(HomeScreen.this, ViewRatingReviewScreen.class));



                                    }
*/

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<SubmitReview> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(ReviewRatingScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(ReviewRatingScreen.this, "Fill Above Fields!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

