package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.BookingHistoryModal;
import com.example.ryzencarrent.adapters.BookingHistoryUIDadapter;
import com.example.ryzencarrent.config;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingHistory extends AppCompatActivity {
    RecyclerView bookingdata;
    BookingHistoryUIDadapter bookingHistoryUIDadapter;
    AppPreferences appPreferences;
    ImageView Back;
    int UID;
TextView txtnodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking_history);
        appPreferences = new AppPreferences(getApplicationContext());
        UID = appPreferences.getuidpref();
        bookingdata = findViewById(R.id.bookingcar);
        Back = findViewById(R.id.imgBack);
        txtnodata = findViewById(R.id.txtnodata);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        setRecycle();
    }

    private void setRecycle() {
        bookingdata.setHasFixedSize(true);
        bookingdata.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<BookingHistoryModal>> call = retrofitAPI.getBookHistoryBYUID(UID);
        call.enqueue(new Callback<List<BookingHistoryModal>>() {
            @Override
            public void onResponse(Call<List<BookingHistoryModal>> call, Response<List<BookingHistoryModal>> response) {
                if (response.isSuccessful()) {
                    List<BookingHistoryModal> bookingHistoryList = response.body();
//                    Collections.sort(bookingHistoryList);
                    Collections.sort(bookingHistoryList, new Comparator<BookingHistoryModal>() {
                        public int compare(BookingHistoryModal o1, BookingHistoryModal o2) {
                            if (o1.getBooking_date() == null || o2.getBooking_date() == null)
                                return 0;
                            return o1.getBooking_date().compareTo(o2.getBooking_date());
                        }
                    });

                    Collections.reverse(bookingHistoryList);
                    if (bookingHistoryList.size()==0) {
                        txtnodata.setVisibility(View.VISIBLE);
                        bookingdata.setVisibility(View.GONE);
                    }else {
                        txtnodata.setVisibility(View.GONE);
                        bookingdata.setVisibility(View.VISIBLE);
                    }
                    bookingHistoryUIDadapter = new BookingHistoryUIDadapter(BookingHistory.this, bookingHistoryList);
                    bookingdata.setAdapter(bookingHistoryUIDadapter);
                }
            }

            @Override
            public void onFailure(Call<List<BookingHistoryModal>> call, Throwable t) {
                Toast.makeText(BookingHistory.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}