package com.example.ryzencarrent.HomeScreen;

import static com.example.ryzencarrent.Constants.Const_FromDate;
import static com.example.ryzencarrent.Constants.Const_ToDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.Modals.GetUserCarRating;
import com.example.ryzencarrent.Modals.GetUserReviewModal;
import com.example.ryzencarrent.Modals.Getcarrating;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.adapters.DriverAdapter;
import com.example.ryzencarrent.Modals.DriverModal;
import com.example.ryzencarrent.adapters.SliderAdapter;
import com.example.ryzencarrent.adapters.SliderItems;
import com.example.ryzencarrent.config;
import com.example.ryzencarrent.drawermenu.BookedHistroyDetail;
import com.example.ryzencarrent.drawermenu.KYCConfirm;
import com.example.ryzencarrent.drawermenu.KYCPending;
import com.example.ryzencarrent.drawermenu.KYCScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehicleDetail extends AppCompatActivity implements DriverAdapter.ItemClickListener1 {
    EditText mess;
    CheckBox air, child, brack, dbag, pbag, window, gps, led, freshner, cable, dcam;
    ImageView back_btn, imgExpandMore;
    TextView tvCarModelName,txtline, tvDieselPetrol, txtmodalyear, tvSeatQuantity, vehicle_detail, carprice;
    ViewPager2 my_pager;
    private Handler sliderHandler = new Handler();
    TextView endDateDay, startDateDay;
    RecyclerView rvdriver;
    View llview;
    LinearLayout lldriver, btnProceed;
    DriverAdapter driverAdapter;
    boolean b_drive = true;
    int getPriceperday, getVehicle_id;
    int driver_f = 0;
    String msg_text;
    AppPreferences appPreferences;
    String getBrand_name, getVehicle_title, getVehicle_detail, getVimage1, getSeating_capacity, getFuel_type;
    int getModel_year, driverID;
RatingBar r_rating;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vehicle_detail);
        appPreferences = new AppPreferences(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        getVehicle_title = bundle.getString("getVehicle_title");
        getSeating_capacity = bundle.getString("getSeating_capacity");
        getVehicle_detail = bundle.getString("getVehicle_detail");
        getBrand_name = bundle.getString("getBrand_name");
        getVimage1 = bundle.getString("getVimage1");
        getFuel_type = bundle.getString("getFuel_type");
        String getVimage2 = bundle.getString("getVimage2");
        String getVimage3 = bundle.getString("getVimage3");
        String getVimage4 = bundle.getString("getVimage4");
        String getVimage5 = bundle.getString("getVimage5");

        getModel_year = bundle.getInt("getModel_year", 0);
        getPriceperday = bundle.getInt("getPriceperday", 0);
        getVehicle_id = bundle.getInt("getVehicle_id", 0);
        int getAirconditioner = bundle.getInt("getAirconditioner", 0);
        int getBrakeassiste = bundle.getInt("getBrakeassiste", 0);
        int getPassengerairbage = bundle.getInt("getPassengerairbage", 0);
        int getSmartgps = bundle.getInt("getSmartgps", 0);
        int getAirfreshner = bundle.getInt("getAirfreshner", 0);
        int getDashcam = bundle.getInt("getDashcam", 0);
        int getChilddoorlock = bundle.getInt("getChilddoorlock", 0);
        int getDriverairbage = bundle.getInt("getDriverairbage", 0);
        int getPowerwindow = bundle.getInt("getPowerwindow", 0);
        int getLEDdisplay = bundle.getInt("getLEDdisplay", 0);
        int getAuxcable = bundle.getInt("getAuxcable", 0);


        back_btn = findViewById(R.id.imgBack);
        mess = findViewById(R.id.message);
        carprice = findViewById(R.id.pricecar);
        tvCarModelName = findViewById(R.id.tvCarModelName);
        tvDieselPetrol = findViewById(R.id.tvDieselPetrol);
        txtmodalyear = findViewById(R.id.txtmodalyear);
        tvSeatQuantity = findViewById(R.id.tvSeatQuantity);
        vehicle_detail = findViewById(R.id.vehicle_detail);
        my_pager = findViewById(R.id.my_pager);
        lldriver = findViewById(R.id.lldriver);
        llview = findViewById(R.id.llview);
        rvdriver = findViewById(R.id.rvdriver);
        btnProceed = findViewById(R.id.btnProceed);
        r_rating = findViewById(R.id.r_rating);
        txtline = findViewById(R.id.txtline);

        air = findViewById(R.id.c1);
        child = findViewById(R.id.c2);
        brack = findViewById(R.id.c3);
        dbag = findViewById(R.id.c4);
        pbag = findViewById(R.id.c5);
        window = findViewById(R.id.c6);
        gps = findViewById(R.id.c7);
        led = findViewById(R.id.c8);
        freshner = findViewById(R.id.c9);
        cable = findViewById(R.id.c10);
        dcam = findViewById(R.id.c11);
        imgExpandMore = findViewById(R.id.imgExpandMore);
        startDateDay = findViewById(R.id.startDateDay);
        endDateDay = findViewById(R.id.endDateDay);
        startDateDay.setText(Const_FromDate);
        endDateDay.setText(Const_ToDate);

        expandata();
        List<SliderItems> sliderItems = new ArrayList<>();

        mess.setBackground(null);
//        if (!mess.getText().toString().equals("")) {

//        }

        if (getVehicle_title != null) {
            tvCarModelName.setText(getVehicle_title);
        }
        if (getSeating_capacity != null) {
            tvSeatQuantity.setText(getSeating_capacity);
        }
        if (getVehicle_detail != null) {
            vehicle_detail.setText(getVehicle_detail);

        }
        if (getModel_year != 0) {
            txtmodalyear.setText("" + getModel_year);

        }
        if (getPriceperday != 0) {
            carprice.setText("₹" + getPriceperday);

        }
        if (getAirconditioner != 0) {
            air.setChecked(true);
        }
        if (getBrakeassiste != 0) {
            brack.setChecked(true);

        }
        if (getPassengerairbage != 0) {
            pbag.setChecked(true);

        }
        if (getSmartgps != 0) {
            gps.setChecked(true);

        }
        if (getAirfreshner != 0) {
            freshner.setChecked(true);

        }
        if (getDashcam != 0) {
            dcam.setChecked(true);

        }
        if (getChilddoorlock != 0) {
            child.setChecked(true);

        }
        if (getDriverairbage != 0) {
            dbag.setChecked(true);

        }
        if (getPowerwindow != 0) {
            window.setChecked(true);

        }
        if (getLEDdisplay != 0) {
            led.setChecked(true);

        }
        if (getAuxcable != 0) {
            cable.setChecked(true);

        }
        if (!getVimage1.equals("")) {
            sliderItems.add(new SliderItems(config.Email_Image_BASE_URL + getVimage1));

        }
        if (!getVimage2.equals("")) {
            sliderItems.add(new SliderItems(config.Email_Image_BASE_URL + getVimage2));

        }
        if (!getVimage3.equals("")) {
            sliderItems.add(new SliderItems(config.Email_Image_BASE_URL + getVimage3));

        }
        if (!getVimage4.equals("")) {
            sliderItems.add(new SliderItems(config.Email_Image_BASE_URL + getVimage4));

        }
        if (!getVimage5.equals("")) {

            sliderItems.add(new SliderItems(config.Email_Image_BASE_URL + getVimage5));

        }

        my_pager.setAdapter(new SliderAdapter(VehicleDetail.this, sliderItems, my_pager));
        my_pager.setClipToPadding(false);
        my_pager.setClipChildren(false);
        my_pager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        my_pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        my_pager.setPageTransformer(compositePageTransformer);
        my_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI2 = retrofit2.create(RetrofitAPI.class);

                Call<GetUserReviewModal> call2 = retrofitAPI2.chkiskyc(appPreferences.getuidpref());
                call2.enqueue(new Callback<GetUserReviewModal>() {
                    @Override
                    public void onResponse(Call<GetUserReviewModal> call, Response<GetUserReviewModal> response) {
                        if (response.isSuccessful()) {
                            GetUserReviewModal getUserReviewModalList = response.body();

                            int getStatus = getUserReviewModalList.getStatus();

                            if (getStatus == 0) {
                                KYCdialog();

                            } else if (getStatus == 1) {

                                funProceed();


                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserReviewModal> call, Throwable t) {
                        Toast.makeText(VehicleDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        imgExpandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b_drive) {
                    funDriver();
                    b_drive = false;
                } else {
                    imgExpandMore.setImageResource(R.drawable.ic_expand_more);

                    lldriver.setVisibility(View.GONE);
                    llview.setVisibility(View.GONE);
                    rvdriver.setVisibility(View.GONE);
                    b_drive = true;

                }
            }
        });
    }

    private void KYCdialog() {
        final Dialog exitDialog = new Dialog(VehicleDetail.this);
        exitDialog.setContentView(R.layout.kyc_popup_lay);

        exitDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        exitDialog.setCancelable(false);
        exitDialog.setCanceledOnTouchOutside(false);
        TextView noBtn = exitDialog.findViewById(R.id.noBtn);
        TextView yesBtn = exitDialog.findViewById(R.id.yesBtn);

        noBtn.setOnClickListener(arg0 -> exitDialog.dismiss());

        yesBtn.setOnClickListener(arg0 -> {
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(config.Email_signup_BASE_URL)
                    // on below line we are calling add Converter
                    // factory as GSON converter factory.
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();
            // below line is to create an instance for our retrofit api class.
            RetrofitAPI retrofitAPI2 = retrofit2.create(RetrofitAPI.class);

            Call<GetUserReviewModal> call2 = retrofitAPI2.getkycstatus(appPreferences.getuidpref());
            call2.enqueue(new Callback<GetUserReviewModal>() {
                @Override
                public void onResponse(Call<GetUserReviewModal> call, Response<GetUserReviewModal> response) {
                    if (response.isSuccessful()) {
                        GetUserReviewModal getUserReviewModalList = response.body();

                        int getStatus = getUserReviewModalList.getStatus();

                        if (getStatus == 0) {
                            startActivity(new Intent(VehicleDetail.this, KYCPending.class));
finish();
                        } else if (getStatus == 1) {

                            startActivity(new Intent(VehicleDetail.this, KYCConfirm.class));

                            finish();


                        }else if (getStatus == 2) {

                            startActivity(new Intent(VehicleDetail.this, KYCScreen.class));

                            finish();


                        }

                    }
                }

                @Override
                public void onFailure(Call<GetUserReviewModal> call, Throwable t) {
                    startActivity(new Intent(VehicleDetail.this, KYCScreen.class));

                    finish();
//                    Toast.makeText(VehicleDetail.this, "No Internet Connection"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
        exitDialog.show();

    }

        private void funProceed() {
        msg_text = mess.getText().toString();

        Intent intent = new Intent(VehicleDetail.this, PaymentMethod.class);
        Bundle bundle = new Bundle();

        bundle.putString("Const_FromDate", Const_FromDate);
        bundle.putString("Const_ToDate", Const_ToDate);
        bundle.putInt("getPriceperday", getPriceperday);
        bundle.putInt("driver_f", driver_f);
        bundle.putString("msg_text", msg_text);

        bundle.putString("getBrand_name", getBrand_name);
        bundle.putString("getVehicle_title", getVehicle_title);
        bundle.putString("getVimage1", getVimage1);
        bundle.putString("getSeating_capacity", getSeating_capacity);
        bundle.putString("getFuel_type", getFuel_type);
        bundle.putInt("getModel_year", getModel_year);
        bundle.putInt("getVehicle_id", getVehicle_id);
        bundle.putInt("driverID", driverID);


        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void funDriver() {
        rvdriver.setHasFixedSize(true);
        rvdriver.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<DriverModal>> call = retrofitAPI.getDriverDetail();
        call.enqueue(new Callback<List<DriverModal>>() {
            @Override
            public void onResponse(Call<List<DriverModal>> call, Response<List<DriverModal>> response) {
                if (response.isSuccessful()) {
                    List<DriverModal> driverModalList = response.body();

                    lldriver.setVisibility(View.VISIBLE);
                    llview.setVisibility(View.VISIBLE);
                    rvdriver.setVisibility(View.VISIBLE);
                    imgExpandMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    driverAdapter = new DriverAdapter(VehicleDetail.this, driverModalList, VehicleDetail.this);
                    rvdriver.setAdapter(driverAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DriverModal>> call, Throwable t) {
                Toast.makeText(VehicleDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            my_pager.setCurrentItem(my_pager.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override
    public void onItemClickListener(int s, int f) {
        if (s == 0) {
            driver_f = 0;
            driverID = 0;
        } else {
            driver_f = f;
            driverID = s;

        }

    }
    private void expandata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Getcarrating> call = retrofitAPI.getcarrating(getVehicle_id);
        call.enqueue(new Callback<Getcarrating>() {
            @Override
            public void onResponse(Call<Getcarrating> call, Response<Getcarrating> response) {
                if (response.isSuccessful()) {

                    Getcarrating reviewModalList = response.body();
                    float getVrating = reviewModalList.getStatus();
                    if (getVrating==0) {

                        r_rating.setIsIndicator(true);
                        txtline.setVisibility(View.VISIBLE);
                    }else {
                        txtline.setVisibility(View.GONE);

                        r_rating.setRating(getVrating);
                        r_rating.setIsIndicator(true);
                    }

                }
            }

            @Override
            public void onFailure(Call<Getcarrating> call, Throwable t) {
                Toast.makeText(VehicleDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}