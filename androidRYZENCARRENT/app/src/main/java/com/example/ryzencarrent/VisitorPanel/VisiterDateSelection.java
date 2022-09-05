package com.example.ryzencarrent.VisitorPanel;

import static com.example.ryzencarrent.Constants.Const_FromDate;
import static com.example.ryzencarrent.Constants.Const_ToDate;
import static com.example.ryzencarrent.Constants.Const_reviewModalList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.HomeScreen.CarListScreen;
import com.example.ryzencarrent.HomeScreen.DateSelectionScreen;
import com.example.ryzencarrent.Modals.CarModal;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisiterDateSelection extends AppCompatActivity {

    TextInputEditText from, to;
    DatePickerDialog.OnDateSetListener onDateSetListener, onDateSetListener1;
    int year, Month, day;
    int year1, month1, day1;
    ImageView back_btn;
    TextInputLayout fromdt, todt;
    TextView search_btn;
    boolean valid11 = false;
    boolean valid12 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiter_date_selection);
        final Calendar calender = Calendar.getInstance();

        year1 = calender.get(Calendar.YEAR);
        month1 = calender.get(Calendar.MONTH);
        day1 = calender.get(Calendar.DAY_OF_MONTH);
        fromdt = findViewById(R.id.date);
        todt = findViewById(R.id.date1);
        year = calender.get(Calendar.YEAR);
        Month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);

        back_btn = findViewById(R.id.back);
        search_btn = findViewById(R.id.search_btndate);
        from = findViewById(R.id.fromdate);
        to = findViewById(R.id.todate);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid11 && valid12) {
                    if (!from.getText().toString().equals("") && !to.getText().toString().equals("")) {
                        Const_FromDate = from.getText().toString();
                        Const_ToDate = to.getText().toString();
                        setCarRecycler(from.getText().toString());


                    } else {
                        Toast.makeText(VisiterDateSelection.this, "Select From-Date and To-Date.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid11 && valid12) {
//                    search_btn.setEnabled(true);
//                    search_btn.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String FromDateInput = fromdt.getEditText().getText().toString().trim();

                if (FromDateInput.isEmpty()) {
                    fromdt.setError("Field can't be empty");
//                    search_btn.setBackgroundResource(R.drawable.button_shape_disable);
                    valid11 = false;

                } else {
                    fromdt.setError(null);
                    valid11 = true;
                }
            }
        });
        to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid11 && valid12) {
//                    search_btn.setEnabled(true);
//                    search_btn.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ToDateInput = todt.getEditText().getText().toString().trim();

                if (!(from.getText().toString().trim().compareTo(to.getText().toString().trim()) <= 0)) {
                    todt.setError("Enter Valid Date");
//                    search_btn.setBackgroundResource(R.drawable.button_shape_disable);
                    valid12 = false;

                } else if (ToDateInput.isEmpty()) {
                    todt.setError("Field can't be empty");
//                    search_btn.setBackgroundResource(R.drawable.button_shape_disable);
                    valid12 = false;

                } else {
                    todt.setError(null);
                    valid12 = true;
                }
            }
        });
        from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        VisiterDateSelection.this, R.style.DatePickerTheme,
                        onDateSetListener, year, Month, day);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        VisiterDateSelection.this, R.style.DatePickerTheme,
                        onDateSetListener1, year1, month1, day1);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
                String nd = "" + dayofmonth;
                String nm = "" + month;
                if (dayofmonth < 10) {
                    nd = "0" + dayofmonth;
                }


                if ((month + 1) < 10) {
                    nm = "0" + (month + 1);
                } else {
                    nm = "" + (month + 1);
                }
                Log.d("testValue", String.valueOf(month));

                // for button text
                from.setText((year + "-" + nm + "-" + nd));
            }
        };

        onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year1, int month1, int dayofmonth1) {
                String nd = "" + dayofmonth1;
                String nm = "" + month1;
                if (dayofmonth1 < 10) {
                    nd = "0" + dayofmonth1;
                }


                if ((month1 + 1) < 10) {
                    nm = "0" + (month1 + 1);
                } else {
                    nm = "" + (month1 + 1);
                }
                Log.d("testValue", String.valueOf(month1));

                // for button text
                to.setText((year1 + "-" + nm + "-" + nd));
            }
        };
    }
    private void setCarRecycler(String fromdt) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarModal>> call = retrofitAPI.getVehicleList(fromdt);
        call.enqueue(new Callback<List<CarModal>>() {
            @Override
            public void onResponse(Call<List<CarModal>> call, Response<List<CarModal>> response) {
                if (response.isSuccessful()) {
                    List<CarModal> reviewModalList = response.body();
                    Const_reviewModalList=reviewModalList;
                    Intent intent = new Intent(VisiterDateSelection.this, VisitorCarList.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<CarModal>> call, Throwable t) {
                Toast.makeText(VisiterDateSelection.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
    }
}