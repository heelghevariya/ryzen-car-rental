package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.ryzencarrent.Modals.Addcarrating;
import com.example.ryzencarrent.Modals.CompanyModal;
import com.example.ryzencarrent.Modals.GetUserCarRating;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.BookingHistoryUIDadapter;
import com.example.ryzencarrent.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BookedHistroyDetail extends AppCompatActivity {
    TextView bookingno1, paymethod1, mTxtCarProducer,
            mTxtCarVenue, mTxtFuelType, mTxtmodalYear, mTxtSeats, mTxtCarPrice,
            bookingdate1, drivefee0, fromdate, todate, totalday, carfee, drivefee, total, bstatus1;
    ImageView mImgCar,Back;
    LinearLayout downloadinvoice,rvcar;
    File file;
CardView card_ratecar;
ImageView imgExpandMore;
    TextView rate_btn;
    RatingBar r_rating;
    boolean click = true;
    int getBooking_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_histroy_detail);

        bookingno1 = findViewById(R.id.bookingno1);
        bookingdate1 = findViewById(R.id.bookingdate1);
        paymethod1 = findViewById(R.id.paymethod1);
        mTxtCarProducer = findViewById(R.id.mTxtCarProducer);
        mTxtCarVenue = findViewById(R.id.mTxtCarVenue);
        mTxtFuelType = findViewById(R.id.mTxtFuelType);
        mTxtmodalYear = findViewById(R.id.mTxtmodalYear);
        mTxtSeats = findViewById(R.id.mTxtSeats);
        mTxtCarPrice = findViewById(R.id.mTxtCarPrice);
        mImgCar = findViewById(R.id.mImgCar);
        fromdate = findViewById(R.id.fromdate1);
        todate = findViewById(R.id.todate1);
        totalday = findViewById(R.id.totalday1);
        carfee = findViewById(R.id.carfee1);
        drivefee = findViewById(R.id.drivefee1);
        drivefee0 = findViewById(R.id.drivefee);
        total = findViewById(R.id.total1);
        bstatus1 = findViewById(R.id.bstatus1);
        downloadinvoice = findViewById(R.id.mLytDetailProceed);
        card_ratecar = findViewById(R.id.card_ratecar);
        imgExpandMore = findViewById(R.id.imgExpandMore);
        rvcar = findViewById(R.id.rvcar);
        r_rating = findViewById(R.id.r_rating);
        rate_btn = findViewById(R.id.rate_btn);

        Bundle bundle = getIntent().getExtras();
        String getVehicle_title = bundle.getString("getVehicle_title");
        String getBrand_name = bundle.getString("getBrand_name");
        String getFuel_type = bundle.getString("getFuel_type");
        int getModel_year = bundle.getInt("getModel_year", 0);
        int getSeating_capacity = bundle.getInt("getSeating_capacity");
        int getPriceperday = bundle.getInt("getPriceperday");
        String getVimage1 = bundle.getString("getVimage1");
         getBooking_no = bundle.getInt("getBooking_no");
        String getBooking_date = bundle.getString("getBooking_date");
        String getFrom_date = bundle.getString("getFrom_date");
        String getTo_date = bundle.getString("getTo_date");
        int getTotalnodays = bundle.getInt("getTotalnodays");
        int getDriver_fees = bundle.getInt("getDriver_fees");
        int getAmount_paid = bundle.getInt("getAmount_paid");
        int getP_status = bundle.getInt("getP_status");
        int getB_status = bundle.getInt("getB_status");
        String getMobile_no = bundle.getString("getMobile_no");
        String getAddress = bundle.getString("getAddress");
        String getState = bundle.getString("getState");
        String getCountry = bundle.getString("getCountry");
        String getCadd = bundle.getString("getCadd");
        String getCno = bundle.getString("getCno");
        String getCmail = bundle.getString("getCmail");


        Back = findViewById(R.id.imgBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        imgExpandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click) {
                    expandata();
                    click = false;
                } else {
                    rvcar.setVisibility(View.GONE);
                    imgExpandMore.setImageResource(R.drawable.ic_expand_more);
                    click = true;

                }
            }
        });
        rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRemoverate();
            }
        });
        downloadinvoice.setOnClickListener(view -> {
                  /*  ActivityCompat.requestPermissions(BookedHistroyDetail.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
                   creatPDF();   */
            Intent intent = new Intent(BookedHistroyDetail.this, InvoiceDownloadScreen.class);
            Bundle bundle1 = new Bundle();

            bundle1.putString("getVehicle_title", getVehicle_title);
            bundle1.putString("getFuel_type", getFuel_type);
            bundle1.putString("getBrand_name", getBrand_name);
            bundle1.putInt("getModel_year", getModel_year);
            bundle1.putInt("getPriceperday", getPriceperday);
            bundle1.putInt("getSeating_capacity", getSeating_capacity);
            bundle1.putString("getVimage1", getVimage1);
            bundle1.putString("getMobile_no", getMobile_no);
            bundle1.putString("getAddress", getAddress);
            bundle1.putString("getState", getState);
            bundle1.putString("getCountry", getCountry);
            bundle1.putString("getCadd", getCadd);
            bundle1.putString("getCno", getCno);
            bundle1.putString("getCmail", getCmail);
            bundle1.putInt("getBooking_no", getBooking_no);
            bundle1.putString("getBooking_date", getBooking_date);
            bundle1.putString("getFrom_date", getFrom_date);
            bundle1.putString("getTo_date", getTo_date);
            bundle1.putInt("getTotalnodays", getTotalnodays);
            bundle1.putInt("getDriver_fees", getDriver_fees);
            bundle1.putInt("getAmount_paid", getAmount_paid);
            bundle1.putInt("getP_status", getP_status);
            bundle1.putInt("getB_status", getB_status);
            intent.putExtras(bundle1);

            startActivity(intent);
        });


        bookingno1.setText("#" + getBooking_no);
        bookingdate1.setText(getBooking_date.split(" ")[0]);
        fromdate.setText(getFrom_date);
        todate.setText(getTo_date);

        totalday.setText("" + getTotalnodays);
        carfee.setText("₹" + getPriceperday);

        if (getDriver_fees != 0) {
            drivefee.setText("₹" + getDriver_fees);
        } else {
            drivefee.setVisibility(View.GONE);
            drivefee0.setVisibility(View.GONE);
        }
        total.setText("₹" + getAmount_paid);


        if (getP_status == 0) {
            paymethod1.setText("Pay at Branch");

        } else if (getP_status == 1) {
            paymethod1.setText("UPI");

        }
        if (getB_status == 0) {
            bstatus1.setText("Pending");
            card_ratecar.setVisibility(View.GONE);

        } else if (getB_status == 1) {
            bstatus1.setText("Confirmed");
            card_ratecar.setVisibility(View.VISIBLE);
        } else if (getB_status == 2) {
            bstatus1.setText("Cancelled");
            card_ratecar.setVisibility(View.GONE);


        }

        mTxtCarProducer.setText(getBrand_name);
        mTxtCarVenue.setText(getVehicle_title);
        mTxtFuelType.setText(getFuel_type);
        mTxtmodalYear.setText("" + getModel_year);
        mTxtSeats.setText("" + getSeating_capacity);
        mTxtCarPrice.setText("₹" + getPriceperday);
        Glide.with(this).load(config.Email_Image_BASE_URL + getVimage1).placeholder(R.drawable.carlogo).into(mImgCar);
    }

    private void addRemoverate() {


        if (rate_btn.getText().equals("Submit")) {

            if (r_rating.getRating()!=0) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                Call<Addcarrating> call = retrofitAPI.addcarrating(getBooking_no, (int) r_rating.getRating());
                call.enqueue(new Callback<Addcarrating>() {
                    @Override
                    public void onResponse(Call<Addcarrating> call, Response<Addcarrating> response) {
                        if (response.isSuccessful()) {

                            Addcarrating reviewModalList = response.body();

                                rate_btn.setText("Remove");
                                rate_btn.setBackgroundResource(R.drawable.shape_plp_remove);
                                r_rating.setIsIndicator(true);

                        }
                    }

                    @Override
                    public void onFailure(Call<Addcarrating> call, Throwable t) {
                        Toast.makeText(BookedHistroyDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                Toast.makeText(this, "Give Rating", Toast.LENGTH_SHORT).show();
            }

        }else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(config.Email_signup_BASE_URL)
                    // on below line we are calling add Converter
                    // factory as GSON converter factory.
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();
            // below line is to create an instance for our retrofit api class.
            RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

            Call<GetUserCarRating> call = retrofitAPI.removecarrating(getBooking_no);
            call.enqueue(new Callback<GetUserCarRating>() {
                @Override
                public void onResponse(Call<GetUserCarRating> call, Response<GetUserCarRating> response) {
                    if (response.isSuccessful()) {

                        GetUserCarRating reviewModalList = response.body();
                        r_rating.setRating(0);
                        rate_btn.setText("Submit");
                        rate_btn.setBackgroundResource(R.drawable.shape_plp_add);

                        r_rating.setIsIndicator(false);

                    }
                }

                @Override
                public void onFailure(Call<GetUserCarRating> call, Throwable t) {
                    Toast.makeText(BookedHistroyDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


//    public void screenshot(View view) {
//
////        View view1 = view.getRootView();
//        View view1 = getWindow().getDecorView().getRootView();
//
//        Bitmap bitmap = Bitmap.createBitmap(view1.getWidth(), view1.getHeight(), Bitmap.Config.ARGB_8888);
//
//        Canvas canvas = new Canvas(bitmap);
//
//        view1.draw(canvas);
//
//        File filescreenshot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//                Calendar.getInstance().getTime().toString() + ".jpg");
//
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(filescreenshot);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            Toast.makeText(this, "Screen shot Take Success ", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
//        }
//    }


    private void creatPDF() {
        downloadinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(BookedHistroyDetail.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                Paint myPaint1 = new Paint();

                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
                PdfDocument.Page mypage1 = myPdfDocument.startPage(myPageInfo1);

                Canvas canvas = mypage1.getCanvas();

                myPaint.setTextSize(15f);
                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setColor(Color.BLACK);
                canvas.drawText("INVOICE", 10, 30, myPaint);

                myPaint1.setColor(Color.BLACK);
                canvas.drawText("Bookingno" + bookingno1, 60, 80, myPaint1);


                myPdfDocument.finishPage(mypage1);

                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + bookingno1 + ".pdf");
                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(BookedHistroyDetail.this, "Pdf Create Succesfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(BookedHistroyDetail.this, "Pdf Not Created", Toast.LENGTH_SHORT).show();
                }
                myPdfDocument.close();
            }
        });
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

        Call<GetUserCarRating> call = retrofitAPI.getusercarrating(getBooking_no);
        call.enqueue(new Callback<GetUserCarRating>() {
            @Override
            public void onResponse(Call<GetUserCarRating> call, Response<GetUserCarRating> response) {
                if (response.isSuccessful()) {
                    rvcar.setVisibility(View.VISIBLE);
                    imgExpandMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                    GetUserCarRating reviewModalList = response.body();
                    int getVrating = reviewModalList.getVrating();
                    if (getVrating==0) {
                        rate_btn.setText("Submit");
                        rate_btn.setBackgroundResource(R.drawable.shape_plp_add);

                        r_rating.setIsIndicator(false);

                    }else {
                        rate_btn.setText("Remove");
                        rate_btn.setBackgroundResource(R.drawable.shape_plp_remove);

                        r_rating.setRating(getVrating);
                        r_rating.setIsIndicator(true);
                    }

                }
            }

            @Override
            public void onFailure(Call<GetUserCarRating> call, Throwable t) {
                Toast.makeText(BookedHistroyDetail.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}