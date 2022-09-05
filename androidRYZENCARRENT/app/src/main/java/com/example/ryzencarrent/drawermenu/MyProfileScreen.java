package com.example.ryzencarrent.drawermenu;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.UpdateProfile;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfileScreen extends AppCompatActivity {

    TextView changepass;
    ImageView view_btn;
    TextInputLayout phonelay, doblay, citylay, statelay, countrylay, addresslay;
    TextInputEditText username, editdate, email, phoneno, add, city, stat, country;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int year, month, day;
    String getAddress, getCity, getState, getCountry, getMobile_no, getDob;
    AppPreferences appPreferences;
    LinearLayout mLytDetailProceed;
    UpdateProfile updateProfile;
    ImageView imgBack;
    boolean valid = false;
    boolean valid1 = false;
    boolean valid2 = false;
    boolean valid3 = false;
    boolean valid4 = false;
    boolean valid5 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_screen);
        appPreferences = new AppPreferences(getApplicationContext());

        final Calendar calender = Calendar.getInstance();

        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);

        editdate = findViewById(R.id.dateofbirth);
        username = findViewById(R.id.editUsername);
        email = findViewById(R.id.emailedit);
        phoneno = findViewById(R.id.editphone);
        add = findViewById(R.id.editaddress);
        city = findViewById(R.id.editcity);
        stat = findViewById(R.id.editstat);
        imgBack = findViewById(R.id.imgBack);
        country = findViewById(R.id.editcountry);
        mLytDetailProceed = findViewById(R.id.mLytDetailProceed);
        mLytDetailProceed.setEnabled(false);
        mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

        Bundle bundle = getIntent().getExtras();
        getAddress = bundle.getString("getAddress");
        getCity = bundle.getString("getCity");
        getState = bundle.getString("getState");
        getCountry = bundle.getString("getCountry");
        getMobile_no = bundle.getString("getMobile_no");
        getDob = bundle.getString("getDob");

        username.setText(appPreferences.getunamepref());
        email.setText(appPreferences.getemailpref());
        phoneno.setText(getMobile_no);
        editdate.setText(getDob);
        add.setText(getAddress);
        city.setText(getCity);
        stat.setText(getState);
        country.setText(getCountry);

        phonelay = findViewById(R.id.phone);
        doblay = findViewById(R.id.date);
        citylay = findViewById(R.id.city);
        statelay = findViewById(R.id.stat);
        countrylay = findViewById(R.id.Country);
        addresslay = findViewById(R.id.address);

        changepass = findViewById(R.id.change_btn);
        view_btn = findViewById(R.id.view);
        editdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MyProfileScreen.this, R.style.DatePickerTheme,
                        onDateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileScreen.this, EmailChangeScreen.class);
                startActivity(intent);
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileScreen.this, ChangePasswordScreen.class);
                startActivity(intent);
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
                editdate.setText((year + "-" + nm + "-" + nd));
            }
        };

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        phoneno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String phoneInput = phonelay.getEditText().getText().toString().trim();

                if (phoneInput.isEmpty()) {
                    phonelay.setError("Field can't be empty");
                    valid3 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (phoneInput.length() > 10) {
                    phonelay.setError("Phone Number is too long");
                    valid3 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (phoneInput.length() < 10) {
                    phonelay.setError("Phone Number must be atleast 10 Digit long");
                    valid3 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!phoneInput.matches("^[6-9]{1}[0-9]{9}$")) {
                    phonelay.setError("Please Enter Valid Mobile Number");
                    valid = false;
                } else {
                    phonelay.setError(null);
                    valid3 = true;

                }
            }
        });
        editdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String DateInput = doblay.getEditText().getText().toString().trim();

                if (DateInput.isEmpty()) {
                    doblay.setError("Field can't be empty");
                    valid4 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    doblay.setError(null);
                    valid4 = true;

                }
            }
        });
        add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String addressInput = addresslay.getEditText().getText().toString().trim();

                if (addressInput.isEmpty()) {
                    addresslay.setError("Field can't be empty");
                    valid5 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (addressInput.length() < 10) {
                    addresslay.setError("Address must be 10 character long");
                    valid5 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (addressInput.length() > 50) {
                    addresslay.setError("Address is too long");
                    valid5 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!addressInput.matches("[A-Za-z0-9/, /-]+")) {
                    addresslay.setError("Enter alphabet and Number only");
                    valid5 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    addresslay.setError(null);
                    valid5 = true;
                }
            }
        });
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String CityInput = citylay.getEditText().getText().toString().trim();

                if (CityInput.isEmpty()) {
                    citylay.setError("Field can't be empty");
                    valid = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CityInput.length() > 13) {
                    citylay.setError("Address is too long");
                    valid = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CityInput.matches(".*[^a-z^A-Z].*")) {
                    citylay.setError("Enter alphabet only");
                    valid = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    citylay.setError(null);
                    valid = true;
                }
            }
        });
        stat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String StateInput = statelay.getEditText().getText().toString().trim();

                if (StateInput.isEmpty()) {
                    statelay.setError("Field can't be empty");
                    valid1 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (StateInput.length() > 13) {
                    statelay.setError("Address is too long");
                    valid1 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (StateInput.matches(".*[^a-z^A-Z].*")) {
                    statelay.setError("Enter alphabet only");
                    valid1 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    statelay.setError(null);
                    valid1 = true;
                }
            }
        });
        country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valid = true;
                valid1 = true;
                valid2 = true;
                valid3 = true;
                valid4 = true;
                valid5 = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.splash_background);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String CountryInput = countrylay.getEditText().getText().toString().trim();

                if (CountryInput.isEmpty()) {
                    countrylay.setError("Field can't be empty");
                    valid2 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CountryInput.length() > 13) {
                    countrylay.setError("Address is too long");
                    valid2 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CountryInput.matches(".*[^a-z^A-Z].*")) {
                    countrylay.setError("Enter alphabet only");
                    valid2 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    countrylay.setError(null);
                    valid2 = true;
                }
            }
        });
        mLytDetailProceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(config.Email_signup_BASE_URL)
                            // on below line we are calling add Converter
                            // factory as GSON converter factory.
                            .addConverterFactory(GsonConverterFactory.create())
                            // at last we are building our retrofit builder.
                            .build();
                    // below line is to create an instance for our retrofit api class.
                    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                    Call<UpdateProfile> call = retrofitAPI.updateprofile(appPreferences.getuidpref(),
                            phoneno.getText().toString(),
                            editdate.getText().toString(),
                            add.getText().toString(),
                            country.getText().toString(),
                            stat.getText().toString(),
                            city.getText().toString()
                    );
                    call.enqueue(new Callback<UpdateProfile>() {
                        @Override
                        public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    updateProfile = response.body();
                                    Toast.makeText(MyProfileScreen.this, "Profile Updated.", Toast.LENGTH_SHORT).show();

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateProfile> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(MyProfileScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}