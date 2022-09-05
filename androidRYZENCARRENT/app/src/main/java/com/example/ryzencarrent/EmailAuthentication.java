package com.example.ryzencarrent;

import static com.example.ryzencarrent.Constants.Verified_Email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailAuthentication extends AppCompatActivity {

    TextInputEditText emailedittext;
    TextInputLayout layoutEmail1;
    ImageView back_btn;
    TextView authemail;
    RecyclerData user;
    ProgressBar PB;
    boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_email_authentication);

        back_btn = findViewById(R.id.back);
        layoutEmail1 = findViewById(R.id.Emailauth);

        emailedittext = findViewById(R.id.authemail);
        authemail = findViewById(R.id.next);
        PB = findViewById(R.id.idemailauth);

        authemail.setEnabled(false);
        authemail.setBackgroundResource(R.drawable.button_shape_disable);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailAuthentication.this, LoginScreen.class);
                startActivity(intent);
            }
        });
        authemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailedittext.getText().toString();

                if (valid) {
                    authemailcheck(email);
                }

            }
        });

        emailedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (valid) {

//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String emailInput = layoutEmail1.getEditText().getText().toString().trim();

                if (emailInput.isEmpty()) {
                    layoutEmail1.setError("Field can't be empty");
                    valid = false;
                    authemail.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (emailInput.length() > 40) {
                    layoutEmail1.setError("E-mail too long");
                    valid = false;
                    authemail.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    layoutEmail1.setError("Please enter a valid email address");
                    valid = false;
                    authemail.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    layoutEmail1.setError(null);
                    valid = true;
                    authemail.setEnabled(true);
                    authemail.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });
    }

    void authemailcheck(String email) {
        PB.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<RecyclerData> call = retrofitAPI.postEmail(email);
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                Log.d("TAG1", "onResponse: " + response);
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user.getStatus() != 1) {

                        Verified_Email = email;
                        PB.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(EmailAuthentication.this, EmailVerification.class);
                        intent.putExtra("otp", user.getStatus());
                        startActivity(intent);
                    } else {
                        PB.setVisibility(View.GONE);
                        Toast.makeText(EmailAuthentication.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                PB.setVisibility(View.GONE);

                Toast.makeText(EmailAuthentication.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(EmailAuthentication.this, LoginScreen.class);
        startActivity(intent);

    }
}