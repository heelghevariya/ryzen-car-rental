package com.example.ryzencarrent.drawermenu;

import static com.example.ryzencarrent.Constants.Verified_Email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.EmailAuthentication;
import com.example.ryzencarrent.EmailVerification;
import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RecyclerData;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailChangeScreen extends AppCompatActivity {

    EditText emailedittext;
    TextView email;
    RecyclerData user;
    ProgressBar PB;
    ImageView Back;
    boolean valid = false;
    TextInputLayout layoutEmail1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_change_screen);

        email = findViewById(R.id.email_btn);
        Back = findViewById(R.id.back);
        emailedittext = (TextInputEditText) findViewById(R.id.email);
        PB = (ProgressBar) findViewById(R.id.idemailauth);

        email.setEnabled(false);
        email.setBackgroundResource(R.drawable.button_shape_disable);

        layoutEmail1 = findViewById(R.id.EmailLL);

        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
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
                    email.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (emailInput.length() > 40) {
                    layoutEmail1.setError("E-mail too long");
                    valid = false;
                    email.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    layoutEmail1.setError("Please enter a valid email address");
                    valid = false;
                    email.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    layoutEmail1.setError(null);
                    valid = true;
                    email.setEnabled(true);
                    email.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid) {
                    String email = emailedittext.getText().toString();
                    authemailcheck(email);
                    PB.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    void authemailcheck(String email) {

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
                        Intent intent = new Intent(EmailChangeScreen.this, EmailChangeConfirm.class);
                        intent.putExtra("otp", user.getStatus());
                        startActivity(intent);
                    } else {
                        PB.setVisibility(View.GONE);
                        Toast.makeText(EmailChangeScreen.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                PB.setVisibility(View.GONE);

                Toast.makeText(EmailChangeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onBackPressed() {
        finish();
    }
}