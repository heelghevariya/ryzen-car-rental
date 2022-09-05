package com.example.ryzencarrent.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.BrandAdapter;
import com.example.ryzencarrent.Modals.BrandModal;

import com.example.ryzencarrent.config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FiltersortActivity extends AppCompatActivity implements BrandAdapter.ItemClickListener {
    LinearLayout LL2Seat, LL5Seat, LL7Seat, petrol, diesel, cng, llpriceHtoL, llpriceLtoH;
    ImageView iv2, iv5, iv7, iv_p, iv_d, iv_c, iv_l, iv_h;
    BrandAdapter brandAdapter;
    int ib_2 = 0;
    String ib_f;
    String ib_b;
    String ib_p;
    TextView textResetAll;
    ArrayList<String> seatingArray = new ArrayList<>();
    AppCompatButton btn_yes, btn_no;
    RecyclerView brandRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtersort);
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        brandRecycler = findViewById(R.id.brandRecycler);
        textResetAll = findViewById(R.id.textResetAll);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result1", ib_2);
                returnIntent.putExtra("result2", ib_f);
                returnIntent.putExtra("result3", ib_b);
                returnIntent.putExtra("result4", ib_p);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        textResetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result1", 0);
                returnIntent.putExtra("result2", "");
                returnIntent.putExtra("result3", "");
                returnIntent.putExtra("result4", "");

                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        M_seatingCapacity();
        M_fuelType();
        M_price();
        M_brandRecycler();

    }

    private void M_price() {
        llpriceLtoH = findViewById(R.id.llpriceLtoH);
        llpriceHtoL = findViewById(R.id.llpriceHtoL);
        iv_l = findViewById(R.id.iv_l);
        iv_h = findViewById(R.id.iv_h);

        llpriceLtoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_l.setVisibility(View.VISIBLE);
                iv_h.setVisibility(View.GONE);
                ib_p = "low";

            }
        });

        llpriceHtoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_h.setVisibility(View.VISIBLE);
                iv_l.setVisibility(View.GONE);
                ib_p = "high";

            }
        });

    }

    private void M_brandRecycler() {
        brandRecycler.setHasFixedSize(true);
        brandRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<BrandModal>> call = retrofitAPI.getBrandname();
        call.enqueue(new Callback<List<BrandModal>>() {
            @Override
            public void onResponse(Call<List<BrandModal>> call, Response<List<BrandModal>> response) {
                if (response.isSuccessful()) {
                    List<BrandModal> brandlist = response.body();
                    brandAdapter = new BrandAdapter(FiltersortActivity.this, brandlist, FiltersortActivity.this);
                    brandRecycler.setAdapter(brandAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<BrandModal>> call, Throwable t) {
                Toast.makeText(FiltersortActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void M_seatingCapacity() {
        LL2Seat = findViewById(R.id.LL2Seat);
        LL5Seat = findViewById(R.id.LL5Seat);
        LL7Seat = findViewById(R.id.LL7Seat);
        iv2 = findViewById(R.id.iv2);
        iv5 = findViewById(R.id.iv5);
        iv7 = findViewById(R.id.iv7);

        LL2Seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv2.setVisibility(View.VISIBLE);
                iv5.setVisibility(View.GONE);
                iv7.setVisibility(View.GONE);
//                    seatingArray.add("2");
                ib_2 = 2;

            }
        });

        LL5Seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv5.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv7.setVisibility(View.GONE);
                ib_2 = 5;

            }
        });
        LL7Seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv7.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv5.setVisibility(View.GONE);
                ib_2 = 7;

            }
        });

    }


    private void M_fuelType() {
        petrol = findViewById(R.id.petrol);
        diesel = findViewById(R.id.diesel);
        cng = findViewById(R.id.cng);
        iv_p = findViewById(R.id.iv_p);
        iv_d = findViewById(R.id.iv_d);
        iv_c = findViewById(R.id.iv_c);

        petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_p.setVisibility(View.VISIBLE);
                iv_d.setVisibility(View.GONE);
                iv_c.setVisibility(View.GONE);
                ib_f = "Petrol";

            }
        });

        diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_d.setVisibility(View.VISIBLE);
                iv_p.setVisibility(View.GONE);
                iv_c.setVisibility(View.GONE);
                ib_f = "Diesel";

            }
        });
        cng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_c.setVisibility(View.VISIBLE);
                iv_p.setVisibility(View.GONE);
                iv_d.setVisibility(View.GONE);
                ib_f = "CNG";

            }
        });

    }

    @Override
    public void onItemClickListener(String s) {
        ib_b = s;
    }
}