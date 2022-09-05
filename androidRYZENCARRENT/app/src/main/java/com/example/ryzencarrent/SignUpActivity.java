package com.example.ryzencarrent;

import static com.example.ryzencarrent.Constants.Verified_Email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Year;
import java.util.Calendar;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    Button login_btn;
    ImageView image;
    TextView logoText, sloganText, callSignUp;
    RecyclerData user;
    TextInputLayout Username, Password, Phoneno, Date, Email, Confirmpass, City, State, Country, Add;
    TextInputEditText emailedit, editaddress,
            editcity, editconfirmpass, editcountry,
            editphone, editstat, editUsername, editdate, editnewpass;
    /*private AppPreferences _appPrefs;*/
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int year, month, day;
    ProgressBar PB;
    boolean valid = false;
    boolean valid1 = false;
    boolean valid2 = false;
    boolean valid3 = false;
    boolean valid4 = false;
    boolean valid5 = false;
    boolean valid6 = false;
    boolean valid7 = false;
    boolean valid8 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        PB = (ProgressBar) findViewById(R.id.idemailauth);
        final Calendar calender = Calendar.getInstance();

        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);


        login_btn = findViewById(R.id.backtologin);
        Date = findViewById(R.id.date);
        image = findViewById(R.id.logo);
        logoText = findViewById(R.id.logoname);
        sloganText = findViewById(R.id.tagline);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        emailedit = findViewById(R.id.emailedit);
        Phoneno = findViewById(R.id.phone);
        Password = findViewById(R.id.password);

        callSignUp = findViewById(R.id.signup);
        callSignUp.setEnabled(false);
        callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

        Confirmpass = findViewById(R.id.confirmpassword);
        Add = findViewById(R.id.address);
        City = findViewById(R.id.city);
        State = findViewById(R.id.stat);
        Country = findViewById(R.id.Country);
        emailedit.setText(Verified_Email);


        editdate = findViewById(R.id.dateofbirth);
        editaddress = findViewById(R.id.editaddress);
        editnewpass = findViewById(R.id.editpassword);
        editcity = findViewById(R.id.editcity);
        editconfirmpass = findViewById(R.id.editconfirmpass);
        editcountry = findViewById(R.id.editcountry);
        editphone = findViewById(R.id.editphone1);
        editstat = findViewById(R.id.editstat);
        editUsername = findViewById(R.id.editUsername);

        editdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SignUpActivity.this, R.style.DatePickerTheme,
                        onDateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
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
                editdate.setText((year + "-" + nm + "-" + nd));
            }
        };

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editUsername.getText().toString();
                String email = emailedit.getText().toString();
                String confirmpass = editconfirmpass.getText().toString();
                String mobileno = editphone.getText().toString();
                String address = editaddress.getText().toString();
                String city = editcity.getText().toString();
                String state = editstat.getText().toString();
                String country = editcountry.getText().toString();
                String date = editdate.getText().toString();

                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {

                    signinCheck(username, email, confirmpass, mobileno, date, address, city, state, country);

                }

                //                if(){
//
//                }else{

//                }
            }
        });


        login_btn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginScreen.class);

            Pair[] pairs = new Pair[10];
            pairs[0] = new Pair<View, String>(image, "logo_img");
            pairs[1] = new Pair<View, String>(logoText, "logo_name");
            pairs[2] = new Pair<View, String>(sloganText, "tagline");
            pairs[3] = new Pair<View, String>(Username, "username");
            pairs[4] = new Pair<View, String>(Password, "password");
            pairs[5] = new Pair<View, String>(login_btn, "signup");
            pairs[6] = new Pair<View, String>(callSignUp, "login");
            pairs[7] = new Pair<View, String>(Email, "email");
            pairs[8] = new Pair<View, String>(Phoneno, "phone");
            pairs[9] = new Pair<View, String>(Add, "phone");


            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
            startActivity(intent, options.toBundle());
        });

        editUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userInput = Username.getEditText().getText().toString().trim();

                if (userInput.isEmpty()) {
                    Username.setError("Field can't be empty");
                    valid = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (userInput.length() > 20) {
                    Username.setError("Username is too long");
                    valid = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (userInput.matches(".*[^a-z^0-9].*")) {
                    Username.setError("Enter alphabet and Number only");
                    valid = false;
                } else {
                    Username.setError(null);
                    valid = true;
                }
            }
        });
        editnewpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newpassInput = Password.getEditText().getText().toString().trim();

                if (newpassInput.isEmpty()) {
                    Password.setError("Field can't be empty");
                    valid1 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() > 15) {
                    Password.setError("password is too long");
                    valid1 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() < 8) {
                    Password.setError("password must be atleast 8 character long");
                    valid1 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    Password.setError(null);
                    valid1 = true;

                }
            }
        });
        editconfirmpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String conpassInput = Confirmpass.getEditText().getText().toString().trim();

                if (conpassInput.isEmpty()) {
                    Confirmpass.setError("Field can't be empty");
                    valid2 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (conpassInput.length() > 15) {
                    Confirmpass.setError("password is too long");
                    valid2 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (conpassInput.length() < 8) {
                    Confirmpass.setError("password must be atleast 8 character long");
                    valid2 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!editnewpass.getText().toString().equals(editconfirmpass.getText().toString())) {
                    Confirmpass.setError("Confirm password must be same");
                    valid2 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    Confirmpass.setError(null);
                    valid2 = true;

                }
            }
        });
        editphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String phoneInput = Phoneno.getEditText().getText().toString().trim();

                if (phoneInput.isEmpty()) {
                    Phoneno.setError("Field can't be empty");
                    valid3 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (phoneInput.length() > 10) {
                    Phoneno.setError("Phone Number is too long");
                    valid3 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (phoneInput.length() < 10) {
                    Phoneno.setError("Phone Number must be atleast 10 Digit long");
                    valid3 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!phoneInput.matches("^[6-9]{1}[0-9]{9}$")) {
                    Phoneno.setError("Please Enter Valid Mobile Number");
                    valid = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    Phoneno.setError(null);
                    valid3 = true;

                }
            }
        });
        editdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String DateInput = Date.getEditText().getText().toString().trim();

                if (DateInput.isEmpty()) {
                    Date.setError("Field can't be empty");
                    valid4 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    Date.setError(null);
                    valid4 = true;

                }
            }
        });
        editaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String addressInput = Add.getEditText().getText().toString().trim();

                if (addressInput.isEmpty()) {
                    Add.setError("Field can't be empty");
                    valid5 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (addressInput.length() < 10) {
                    Add.setError("Address must be 10 character long");
                    valid5 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (addressInput.length() > 50) {
                    Add.setError("Address is too long");
                    valid5 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!addressInput.matches("[A-Za-z0-9/, /-]+")) {
                    Add.setError("Enter alphabet and Number only");
                    valid5 = false;
                } else {
                    Add.setError(null);
                    valid5 = true;
                }
            }
        });
        editcity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String CityInput = City.getEditText().getText().toString().trim();

                if (CityInput.isEmpty()) {
                    City.setError("Field can't be empty");
                    valid6 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CityInput.length() > 13) {
                    City.setError("City name is too long");
                    valid6 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CityInput.matches(".*[^a-z^A-Z].*")) {
                    City.setError("Enter alphabet only");
                    valid6 = false;
                } else {
                    City.setError(null);
                    valid6 = true;
                }
            }
        });
        editstat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String StateInput = State.getEditText().getText().toString().trim();

                if (StateInput.isEmpty()) {
                    State.setError("Field can't be empty");
                    valid7 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (StateInput.length() > 13) {
                    State.setError("State name is too long");
                    valid7 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (StateInput.matches(".*[^a-z^A-Z].*")) {
                    State.setError("Enter alphabet only");
                    valid7 = false;
                } else {
                    State.setError(null);
                    valid7 = true;
                }
            }
        });
        editcountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7 && valid8) {
                    callSignUp.setEnabled(true);
                    callSignUp.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String CountryInput = Country.getEditText().getText().toString().trim();

                if (CountryInput.isEmpty()) {
                    Country.setError("Field can't be empty");
                    valid8 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CountryInput.length() > 13) {
                    Country.setError("Country name is too long");
                    valid8 = false;
                    callSignUp.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (CountryInput.matches(".*[^a-z^A-Z].*")) {
                    Country.setError("Enter alphabet only");
                    valid8 = false;
                } else {
                    Country.setError(null);
                    valid8 = true;
                }
            }
        });


    }

    void signinCheck(String username, String email, String confirmpass, String mobileno, String
            date, String address
            , String city, String state, String country) {
        PB.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<RecyclerData> call = retrofitAPI.postRegister(username, email, confirmpass, mobileno, date, address, city, state, country);
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                Log.d("TAG1", "onResponse: " + response);

                if (response.isSuccessful()) {
                    user = response.body();

                    if (user.getStatus() != 0) {

                        Intent intent = new Intent(SignUpActivity.this, LoginScreen.class);
                        startActivity(intent);
                        PB.setVisibility(View.VISIBLE);

                        /*String b = user.getUsername();
                        int c = user.getUserid();
                        String a = email;
                        _appPrefs.saveemailpref(a);
                        _appPrefs.saveuidpref(c);
                        _appPrefs.saveunamepref(b);*/

                        finish();
                    } else {
                        PB.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "Mobile Number Already Exist !", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {

                PB.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this, LoginScreen.class);
        startActivity(intent);
    }
}