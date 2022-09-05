package com.example.ryzencarrent.drawermenu;

import static com.example.ryzencarrent.adapters.AppPreferences.APP_SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.adapters.UpdatePassword;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordScreen extends AppCompatActivity {

    TextView changpassword;
    TextInputLayout newpasslay, currentpasslay, confirmpasslay;
    TextInputEditText currentpassword, newpassword, confirmpassword;
    AppPreferences appPreferences;
    UpdatePassword updatePassword;
    ImageView back;
    boolean valid1 = false;
    boolean valid2 = false;
    boolean valid3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);

        appPreferences = new AppPreferences(getApplicationContext());


        changpassword = findViewById(R.id.search_btn);
        changpassword.setEnabled(false);
        changpassword.setBackgroundResource(R.drawable.button_shape_disable);

        newpasslay = findViewById(R.id.password);
        currentpasslay = findViewById(R.id.currentpass);
        confirmpasslay = findViewById(R.id.confirmpassword);

        currentpassword = findViewById(R.id.editcurrentpass);
        newpassword = findViewById(R.id.editpassword);
        confirmpassword = findViewById(R.id.editconfirmpass);
        back = findViewById(R.id.back);

        currentpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid1 && valid2 && valid3) {
                    changpassword.setEnabled(true);
                    changpassword.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String currentpassInput = currentpasslay.getEditText().getText().toString().trim();

                if (currentpassInput.isEmpty()) {
                    currentpasslay.setError("Field can't be empty");
                    valid1 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (currentpassInput.length() > 15) {
                    currentpasslay.setError("password is too long");
                    valid1 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (currentpassInput.length() < 8) {
                    currentpasslay.setError("password must be atleast 8 character long");
                    valid1 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    currentpasslay.setError(null);
                    valid1 = true;

                }
            }
        });
        newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!newpassword.getText().toString().equals(confirmpassword.getText().toString())) {
                    confirmpasslay.setError("Confirm password must be same");
                    valid1 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);
                }
                if (valid1 && valid2 && valid3) {
                    changpassword.setEnabled(true);
                    changpassword.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newpassInput = newpasslay.getEditText().getText().toString().trim();

                if (newpassInput.isEmpty()) {
                    newpasslay.setError("Field can't be empty");
                    valid3 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() > 15) {
                    newpasslay.setError("password is too long");
                    valid3 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() < 8) {
                    newpasslay.setError("password must be atleast 8 character long");
                    valid3 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    newpasslay.setError(null);
                    valid3 = true;

                }
            }
        });
        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid1 && valid2 && valid3) {
                    changpassword.setEnabled(true);
                    changpassword.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String conpassInput = confirmpasslay.getEditText().getText().toString().trim();

                if (conpassInput.isEmpty()) {
                    currentpasslay.setError("Field can't be empty");
                    valid2 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (conpassInput.length() > 15) {
                    confirmpasslay.setError("password is too long");
                    valid2 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (conpassInput.length() < 8) {
                    confirmpasslay.setError("password must be atleast 8 character long");
                    valid2 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!newpassword.getText().toString().equals(confirmpassword.getText().toString())) {
                    confirmpasslay.setError("Confirm password must be same");
                    valid2 = false;
                    changpassword.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    confirmpasslay.setError(null);
                    valid2 = true;

                }
            }
        });

        changpassword.setOnClickListener(new View.OnClickListener() {
            String newpass = newpassword.getText().toString();

            @Override
            public void onClick(View v) {
                if (valid1 && valid2 && valid3) {
                    changepassword(newpass);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void changepassword(String newpass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<UpdatePassword> call = retrofitAPI.updatepassword(appPreferences.getuidpref(),
                currentpassword.getText().toString(),
                newpassword.getText().toString()

        );
        call.enqueue(new Callback<UpdatePassword>() {
            @Override
            public void onResponse(Call<UpdatePassword> call, Response<UpdatePassword> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        updatePassword = response.body();

                        if (updatePassword.getStatus() == 1) {
                            Toast.makeText(ChangePasswordScreen.this, "Password Updated.", Toast.LENGTH_SHORT).show();
                            SharedPreferences _sharedPrefs;
                            _sharedPrefs = getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
                            _sharedPrefs.edit().clear().commit();
                            startActivity(new Intent(ChangePasswordScreen.this, LoginScreen.class));
                            finish();
                        } else {
                            Toast.makeText(ChangePasswordScreen.this, "Invalid Current Password.", Toast.LENGTH_SHORT).show();

                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<UpdatePassword> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(ChangePasswordScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}