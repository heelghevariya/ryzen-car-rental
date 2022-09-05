package com.example.ryzencarrent.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.Modals.TotalDays;
import com.example.ryzencarrent.config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentMethod extends AppCompatActivity {
int Rstatus=-1;
    RadioButton upipay, barnchpay;
    TextView search_btn;
    TotalDays date;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        upipay = findViewById(R.id.upi);
        barnchpay = findViewById(R.id.payatbranch);
        search_btn = findViewById(R.id.search_btn);
        back = findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();
        String Const_FromDate = bundle.getString("Const_FromDate");
        String Const_ToDate = bundle.getString("Const_ToDate");
        String msg_text = bundle.getString("msg_text");
        int getPriceperday = bundle.getInt("getPriceperday");
        int driver_f = bundle.getInt("driver_f");
        int getVehicle_id = bundle.getInt("getVehicle_id");

        String getBrand_name = bundle.getString("getBrand_name");
        String getVehicle_title = bundle.getString("getVehicle_title");
        String getVimage1 = bundle.getString("getVimage1");
        String getSeating_capacity = bundle.getString("getSeating_capacity");
        String getFuel_type = bundle.getString("getFuel_type");
        int getModel_year = bundle.getInt("getModel_year",0);
        int driverID = bundle.getInt("driverID",0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        upipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Rstatus=1;
                }
            }
        });

        barnchpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Rstatus=0;
                }
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Rstatus==-1) {
                    Toast.makeText(PaymentMethod.this, "Select Payment Method.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                Call<TotalDays> call = retrofitAPI.postDate(Const_FromDate, Const_ToDate);
                call.enqueue(new Callback<TotalDays>() {
                    @Override
                    public void onResponse(Call<TotalDays> call, Response<TotalDays> response) {
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                date = response.body();
                                    int getTday = date.getTday();

                                    Intent intent = new Intent(PaymentMethod.this, BookingDetailsScreen.class);
                                    Bundle bundle = new Bundle();

                                    bundle.putString("Const_FromDate",Const_FromDate);
                                    bundle.putString("Const_ToDate",Const_ToDate);
                                    bundle.putInt("getPriceperday",getPriceperday);
                                    bundle.putInt("driver_f",driver_f);
                                    bundle.putInt("Rstatus",Rstatus);
                                    bundle.putInt("getTday",getTday);
                                    bundle.putInt("getVehicle_id",getVehicle_id);
                                    bundle.putString("msg_text",msg_text);

                                    bundle.putString("getBrand_name",getBrand_name);
                                    bundle.putString("getVehicle_title",getVehicle_title);
                                    bundle.putString("getVimage1",getVimage1);
                                    bundle.putString("getSeating_capacity",getSeating_capacity);
                                    bundle.putString("getFuel_type",getFuel_type);
                                    bundle.putInt("getModel_year",getModel_year);
                                    bundle.putInt("driverID",driverID);


                                    intent.putExtras(bundle);
                                    startActivity(intent);

                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<TotalDays> call, Throwable t) {
                        // displaying an error message in toast
                        Toast.makeText(PaymentMethod.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                    }
                });



//                Toast.makeText(PaymentMethod.this, "status:"+Rstatus, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}