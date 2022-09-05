package com.example.ryzencarrent.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.Modals.AddBookings;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.PayAtBranch;
import com.example.ryzencarrent.config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingDetailsScreen extends AppCompatActivity {

    TextView fromdate, todate, totalday, carfee, pm1, drivefee, drivefee1, total, tvDetailProceed, mTxtCarProducer, mTxtCarVenue, mTxtFuelType, mTxtmodalYear, mTxtSeats, mTxtCarPrice;
    ImageView mImgCar, imgBack;
    LinearLayout mLytDetailProceed;
    AddBookings addBookings;
    PayAtBranch payAtBranch;
    AppPreferences appPreferences;
    int k;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details_screen);
        appPreferences = new AppPreferences(getApplicationContext());

        fromdate = findViewById(R.id.fromdate1);
        todate = findViewById(R.id.todate1);
        totalday = findViewById(R.id.totalday1);
        carfee = findViewById(R.id.carfee1);
        drivefee = findViewById(R.id.drivefee);
        drivefee1 = findViewById(R.id.drivefee1);
        total = findViewById(R.id.total1);
        pm1 = findViewById(R.id.pm1);
        tvDetailProceed = findViewById(R.id.tvDetailProceed);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTxtCarProducer = findViewById(R.id.mTxtCarProducer);
        mTxtCarVenue = findViewById(R.id.mTxtCarVenue);
        mTxtFuelType = findViewById(R.id.mTxtFuelType);
        mTxtmodalYear = findViewById(R.id.mTxtmodalYear);
        mTxtSeats = findViewById(R.id.mTxtSeats);
        mTxtCarPrice = findViewById(R.id.mTxtCarPrice);
        mImgCar = findViewById(R.id.mImgCar);
        mLytDetailProceed = findViewById(R.id.mLytDetailProceed);


        Bundle bundle = getIntent().getExtras();
        String Const_FromDate = bundle.getString("Const_FromDate");
        String Const_ToDate = bundle.getString("Const_ToDate");
        int getPriceperday = bundle.getInt("getPriceperday");
        int driver_f = bundle.getInt("driver_f", 0);
        String msg_text = bundle.getString("msg_text");
        int Rstatus = bundle.getInt("Rstatus", 0);
        int getTday = bundle.getInt("getTday", 0);
        int getVehicle_id = bundle.getInt("getVehicle_id", 0);
        int driverID = bundle.getInt("driverID", 0);

        int getModel_year = bundle.getInt("getModel_year", 0);
        String getBrand_name = bundle.getString("getBrand_name");
        String getVehicle_title = bundle.getString("getVehicle_title");
        String getVimage1 = bundle.getString("getVimage1");
        String getSeating_capacity = bundle.getString("getSeating_capacity");
        String getFuel_type = bundle.getString("getFuel_type");


        mTxtCarProducer.setText(getBrand_name);
        mTxtCarVenue.setText(getVehicle_title);
        mTxtmodalYear.setText("" + getModel_year);
        mTxtSeats.setText(getSeating_capacity);
        mTxtFuelType.setText(getFuel_type);
        Glide.with(this).load(config.Email_Image_BASE_URL + getVimage1).placeholder(R.drawable.carlogo).into(mImgCar);

        if (Const_FromDate != null) {
            fromdate.setText(Const_FromDate);
        }
        if (Const_ToDate != null) {
            todate.setText(Const_ToDate);
        }
        if (getPriceperday != 0) {
            carfee.setText("₹" + getPriceperday);
            mTxtCarPrice.setText("₹" + getPriceperday);
        }

        if (getTday != 0) {
            totalday.setText("" + getTday);
        }
        if (driver_f == 0) {
            drivefee.setVisibility(View.GONE);
            drivefee1.setVisibility(View.GONE);
            k= getTday * (getPriceperday);
            total.setText("₹" + k);
        } else {
            drivefee1.setText("₹" + driver_f);
            k = getTday * (driver_f + getPriceperday);
            total.setText("₹" + k);

        }
        if (Rstatus == 0) {
            tvDetailProceed.setText("Book");
            pm1.setText("Pay at Branch");
        } else {
            tvDetailProceed.setText("Pay And Book");
            pm1.setText("UPI");

        }
        mLytDetailProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Rstatus == 0) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(config.Email_signup_BASE_URL)
                            // on below line we are calling add Converter
                            // factory as GSON converter factory.
                            .addConverterFactory(GsonConverterFactory.create())
                            // at last we are building our retrofit builder.
                            .build();
                    // below line is to create an instance for our retrofit api class.
                    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                    Call<AddBookings> call = retrofitAPI.addbooking(appPreferences.getuidpref(),
                            getVehicle_id,
                            driverID,
                            Const_FromDate,
                            Const_ToDate,
                            msg_text);
                    call.enqueue(new Callback<AddBookings>() {
                        @Override
                        public void onResponse(Call<AddBookings> call, Response<AddBookings> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    addBookings = response.body();
                                    int status = addBookings.getStatus();

                                    PayatBranch(status);

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<AddBookings> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(BookingDetailsScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Intent intent = new Intent(BookingDetailsScreen.this, UPIPayment.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("total", k);

                    bundle.putInt("getVehicle_id", getVehicle_id);
                    bundle.putInt("driverID", driverID);
                    bundle.putString("Const_FromDate", Const_FromDate);
                    bundle.putString("Const_ToDate", Const_ToDate);
                    bundle.putString("msg_text", msg_text);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    private void PayatBranch(int status) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<PayAtBranch> call = retrofitAPI.payatbranch(status,k);
        call.enqueue(new Callback<PayAtBranch>() {
            @Override
            public void onResponse(Call<PayAtBranch> call, Response<PayAtBranch> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        payAtBranch = response.body();
                        int status = payAtBranch.getStatus();
                        Toast.makeText(BookingDetailsScreen.this, "Booked successfully.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookingDetailsScreen.this, HomeScreen.class);
                             /*       Bundle bundle = new Bundle();

                                    bundle.putString("Const_FromDate",Const_FromDate);
                                    bundle.putString("Const_ToDate",Const_ToDate);
                                    bundle.putInt("getPriceperday",getPriceperday);
                                    bundle.putInt("driver_f",driver_f);
                                    bundle.putInt("Rstatus",Rstatus);
                                    bundle.putInt("getTday",getTday);
                                    bundle.putString("msg_text",msg_text);

                                    bundle.putString("getBrand_name",getBrand_name);
                                    bundle.putString("getVehicle_title",getVehicle_title);
                                    bundle.putString("getVimage1",getVimage1);
                                    bundle.putString("getSeating_capacity",getSeating_capacity);
                                    bundle.putString("getFuel_type",getFuel_type);
                                    bundle.putInt("getModel_year",getModel_year);


                                    intent.putExtras(bundle);*/
                        startActivity(intent);
                        finish();

                    }


                }
            }

            @Override
            public void onFailure(Call<PayAtBranch> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(BookingDetailsScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}