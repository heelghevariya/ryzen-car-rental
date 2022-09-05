package com.example.ryzencarrent;

import static com.example.ryzencarrent.Constants.Forgot_Mail;
import static com.example.ryzencarrent.Constants.Verified_Email;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {

    ImageView back;
    TextView next_btn;
    TextInputEditText emailtext;
    TextInputLayout email;
    RecyclerData user;
    ProgressBar PB;
    boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        emailtext = findViewById(R.id.editForgetEmail);
        back = findViewById(R.id.back);
        next_btn = findViewById(R.id.next);
        email = findViewById(R.id.FpassEmail);
        PB = (ProgressBar) findViewById(R.id.idemailauth);

        next_btn.setEnabled(false);
        next_btn.setBackgroundResource(R.drawable.button_shape_disable);

        emailtext.addTextChangedListener(new TextWatcher() {
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
                String emailInput = email.getEditText().getText().toString().trim();

                if (emailInput.isEmpty()) {
                    email.setError("Field can't be empty");
                    valid = false;
                    next_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (emailInput.length() > 40) {
                    email.setError("E-mail too long");
                    valid = false;
                    next_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    email.setError("Please enter a valid email address");
                    valid = false;
                    next_btn.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    email.setError(null);
                    valid = true;
                    next_btn.setEnabled(true);
                    next_btn.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });
//
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgetpassemail = emailtext.getText().toString();
                if (valid) {
                    authemailcheck(forgetpassemail);

                }

            }
        });
    }

    private void authemailcheck(String forgetpassemail) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PB.setVisibility(View.VISIBLE);

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<RecyclerData> call = retrofitAPI.PostForgetpassemail(forgetpassemail);
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                Log.d("TAG1", "onResponse: " + response);

                if (response.isSuccessful()) {
                    user = response.body();

                    if (user.getStatus() != 0) {

                        Forgot_Mail = forgetpassemail;

                        PB.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(ForgotPassword.this, VerifyOTP.class);
                        intent.putExtra("forgototp", user.getStatus());
                        startActivity(intent);
                    } else {
                        PB.setVisibility(View.GONE);
                        Toast.makeText(ForgotPassword.this, "Account with this E-mail doesn't exist.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                PB.setVisibility(View.GONE);
                Toast.makeText(ForgotPassword.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(ForgotPassword.this, LoginScreen.class);
        startActivity(intent);

    }
}
