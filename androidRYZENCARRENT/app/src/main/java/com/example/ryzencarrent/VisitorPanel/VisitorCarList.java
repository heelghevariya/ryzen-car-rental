package com.example.ryzencarrent.VisitorPanel;

import static com.example.ryzencarrent.Constants.Const_FromDate;
import static com.example.ryzencarrent.Constants.Const_ToDate;
import static com.example.ryzencarrent.Constants.Const_reviewModalList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.HomeScreen.CarListScreen;
import com.example.ryzencarrent.HomeScreen.FiltersortActivity;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.CarAdapter;
import com.example.ryzencarrent.adapters.CarAdapter1;
import com.example.ryzencarrent.Modals.CarModal;
import com.example.ryzencarrent.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisitorCarList extends AppCompatActivity {

    ImageView back_btn;
    TextView mTxtStDay, mTxtEtDay;
    RecyclerView mRcProduct;
    CarAdapter1 carAdapter;
    LinearLayout llFilterNSort;
    int seatingCap = 0;
    String fueltype, brandtype, pricetype;
    List<CarModal> reviewModalList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_car_list);

        back_btn = findViewById(R.id.mImgBack);
        mRcProduct = findViewById(R.id.mRcProduct);
        mTxtStDay = findViewById(R.id.mTxtStDay);
        mTxtEtDay = findViewById(R.id.mTxtEtDay);
        llFilterNSort = findViewById(R.id.llFilterNSort);
        mTxtStDay.setText(Const_FromDate);
        mTxtEtDay.setText(Const_ToDate);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        llFilterNSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorCarList.this, FiltersortActivity.class);
                startActivityForResult(intent, 1001);
//                startActivity(intent);
            }
        });

        setCarRecycler();
    }

    private void setCarRecycler() {
        mRcProduct.setHasFixedSize(true);
        mRcProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarModal>> call = retrofitAPI.getVehicleList();
        call.enqueue(new Callback<List<CarModal>>() {
            @Override
            public void onResponse(Call<List<CarModal>> call, Response<List<CarModal>> response) {
                if (response.isSuccessful()) {
                    List<CarModal> reviewModalList = response.body();*/
        if (!Const_reviewModalList.isEmpty()) {
            carAdapter = new CarAdapter1(VisitorCarList.this, Const_reviewModalList);
            mRcProduct.setAdapter(carAdapter);
        }

             /*   }
            }

            @Override
            public void onFailure(Call<List<CarModal>> call, Throwable t) {
                Toast.makeText(VisitorCarList.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    private void setCarRecyclerAgain() {
        List<CarModal> AGAINreviewModalList = new ArrayList<>();
        List<CarModal> AGAINreviewModalList1 = new ArrayList<>();

        mRcProduct.setHasFixedSize(true);
        mRcProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AGAINreviewModalList.clear();
        AGAINreviewModalList1.clear();
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarModal>> call = retrofitAPI.getVehicleList();
        call.enqueue(new Callback<List<CarModal>>() {
            @Override
            public void onResponse(Call<List<CarModal>> call, Response<List<CarModal>> response) {
                if (response.isSuccessful()) {
                    List<CarModal> reviewModalList = response.body();*/
        if (!Const_reviewModalList.isEmpty()) {
            reviewModalList=Const_reviewModalList;
        }
                    for (int i = 0; i < reviewModalList.size(); i++) {


                        if (seatingCap != 0 && fueltype != null && brandtype != null) {
                            if (seatingCap == Integer.parseInt(reviewModalList.get(i).getSeating_capacity()) && fueltype.equals(reviewModalList.get(i).getFuel_type()) && brandtype.equals(reviewModalList.get(i).getBrand_name())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));
                            }

                        } else if (seatingCap != 0 && fueltype != null) {
                            if (seatingCap == Integer.parseInt(reviewModalList.get(i).getSeating_capacity()) && fueltype.equals(reviewModalList.get(i).getFuel_type())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        } else if (seatingCap != 0 && brandtype != null) {
                            if (seatingCap == Integer.parseInt(reviewModalList.get(i).getSeating_capacity()) && brandtype.equals(reviewModalList.get(i).getBrand_name())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        } else if (fueltype != null && brandtype != null) {
                            if (fueltype.equals(reviewModalList.get(i).getFuel_type()) && brandtype.equals(reviewModalList.get(i).getBrand_name())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        } else if (seatingCap != 0) {
                            if (seatingCap == Integer.parseInt(reviewModalList.get(i).getSeating_capacity())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        } else if (fueltype != null) {
                            if (fueltype.equals(reviewModalList.get(i).getFuel_type())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        } else if (brandtype != null) {
                            if (brandtype.equals(reviewModalList.get(i).getBrand_name())) {
                                AGAINreviewModalList.add(reviewModalList.get(i));

                            }

                        }


                    }
                    if (pricetype != null && AGAINreviewModalList.size() == 0) {
                        for (int i = 0; i < reviewModalList.size(); i++) {
                            AGAINreviewModalList1.add(reviewModalList.get(i));

                        }

                        //condition
                          /*  if (pricetype.equals("low")) {
                                Collections.sort(AGAINreviewModalList1);

                            } else if (pricetype.equals("high")) {
                                Collections.sort(AGAINreviewModalList1);
                                Collections.reverse(AGAINreviewModalList1);

                            }*/


                    }
                    if (pricetype != null && AGAINreviewModalList.size() != 0) {
                        //condition

                        for (int i = 0; i < AGAINreviewModalList.size(); i++) {
                            AGAINreviewModalList1.add(AGAINreviewModalList.get(i));

                        }


                    } else if (pricetype == null && AGAINreviewModalList.size() != 0) {

                        for (int i = 0; i < AGAINreviewModalList.size(); i++) {
                            AGAINreviewModalList1.add(AGAINreviewModalList.get(i));

                        }

                    }
                    if (pricetype != null) {
                        if (pricetype.equals("low")) {
                            Collections.sort(AGAINreviewModalList1);

                        } else if (pricetype.equals("high")) {
                            Collections.sort(AGAINreviewModalList1);
                            Collections.reverse(AGAINreviewModalList1);

                        }
                    }
                    carAdapter = new CarAdapter1(VisitorCarList.this, AGAINreviewModalList1);
                    mRcProduct.setAdapter(carAdapter);
          /*      }
            }

            @Override
            public void onFailure(Call<List<CarModal>> call, Throwable t) {
                Toast.makeText(VisitorCarList.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                seatingCap = data.getIntExtra("result1", 0);
                fueltype = data.getStringExtra("result2");
                brandtype = data.getStringExtra("result3");
                pricetype = data.getStringExtra("result4");
                if (seatingCap != 0 || fueltype != null || brandtype != null || pricetype != null) {
                    setCarRecyclerAgain();
                } else {
                    setCarRecycler();
                }
            }
        }


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}