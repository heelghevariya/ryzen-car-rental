package com.example.ryzencarrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.VisitorPanel.VisitorHomeScreen;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.LoginData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginScreen extends AppCompatActivity {

    Button callSignUp, forgotpass;
    TextView login_btn, SKIP;
    TextInputEditText LoginMail, LoginPass;
    private AppPreferences _appPrefs;
    LoginData user;
    TextInputLayout layoutEmail, layoutpass;
    boolean valid = false;
    boolean valid1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        layoutEmail = findViewById(R.id.Email);
        layoutpass = findViewById(R.id.password);
        _appPrefs = new AppPreferences(getApplicationContext());
        callSignUp = findViewById(R.id.signup);
        login_btn = findViewById(R.id.loginbtn);
        forgotpass = findViewById(R.id.forgetpass);
        login_btn.setEnabled(false);
        login_btn.setBackgroundResource(R.drawable.button_shape_disable);
        LoginMail = findViewById(R.id.Entermail);
        LoginPass = findViewById(R.id.Enterpass);
        SKIP = findViewById(R.id.skip);

        SKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, VisitorHomeScreen.class);
                startActivity(intent);
            }
        });
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, EmailAuthentication.class);
                startActivity(intent);
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        LoginMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    login_btn.setEnabled(true);
                    login_btn.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String emailInput = layoutEmail.getEditText().getText().toString().trim();

                if (emailInput.isEmpty()) {
                    layoutEmail.setError("Field can't be empty");
                    valid = false;
                    login_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (emailInput.length() > 40) {
                    layoutEmail.setError("E-mail too long");
                    valid = false;
                    login_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    layoutEmail.setError("Please enter a valid email address");
                    valid = false;
                    login_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    layoutEmail.setError(null);
                    valid = true;

                }
            }
        });

        LoginPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    login_btn.setEnabled(true);
                    login_btn.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwordInput = layoutpass.getEditText().getText().toString().trim();

                if (passwordInput.isEmpty()) {
                    layoutpass.setError("Field can't be empty");
                    valid1 = false;
                    login_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    layoutpass.setError(null);
                    valid1 = true;

                }
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginMail.getText().toString();
                String password = LoginPass.getText().toString();
//                if (!validateEmail() && !validatePassword()) {
//
//                }else {
                if (valid && valid1) {
                    loginCheck(email, password);


                }

//                }


            }
        });
    }

//    private boolean validateEmail() {
//        String emailInput = layoutEmail.getEditText().getText().toString().trim();
//
//        if (emailInput.isEmpty()) {
//            layoutEmail.setError("Field can't be empty");
//            return false;
//        } else if (emailInput.length() > 60) {
//            layoutEmail.setError("E-mail too long");
//            return false;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            layoutEmail.setError("Please enter a valid email address");
//            return false;
//        } else {
//            layoutEmail.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validatePassword() {
//        String passwordInput = layoutpass.getEditText().getText().toString().trim();
//
//        if (passwordInput.isEmpty()) {
//            layoutpass.setError("Field can't be empty");
//            return false;
//        } else {
//            layoutpass.setError(null);
//            return true;
//        }
//    }


    void loginCheck(String email, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<LoginData> call = retrofitAPI.postLogin(email, password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        user = response.body();
                        if (user.getStatus() == 1) {
                            int userid = user.getUserid();
                            String usermail = user.getUsermail();
                            String username = user.getUsername();
                            _appPrefs.saveuidpref(userid);
                            _appPrefs.saveemailpref(usermail);
                            _appPrefs.saveunamepref(username);
                            _appPrefs.setlogged(true);
                            startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                            finish();
                        } else {
                            Toast.makeText(LoginScreen.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(LoginScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}