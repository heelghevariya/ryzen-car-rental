package com.example.ryzencarrent.drawermenu;

import static com.example.ryzencarrent.Constants.Verified_Email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.ryzencarrent.EmailVerification;
import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RecyclerData;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.SignUpActivity;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.adapters.usermailchangedata;
import com.example.ryzencarrent.config;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailChangeConfirm extends AppCompatActivity {

    int id;
    PinView edtOtp;
    TextView btnsubmit;
    ImageView close_btn;
    ProgressDialog dialog;
AppPreferences appPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_change_confirm);
appPreferences=new AppPreferences(getApplicationContext());
        id = getIntent().getIntExtra("otp", 0);
        close_btn = (ImageView) findViewById(R.id.close);


        edtOtp = (PinView) findViewById(R.id.setpin);
        btnsubmit = (TextView) findViewById(R.id.search_btn);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OtpCode = edtOtp.getText().toString();
                if (OtpCode.equals("")) {
                    Toast.makeText(EmailChangeConfirm.this, "Enter Otp", Toast.LENGTH_SHORT).show();
                } else {

//                    dialog.show();

                    if (id == Integer.parseInt(OtpCode)) {
                        changemail();




                    } else {
                        Toast.makeText(EmailChangeConfirm.this, "You entered wrong otp!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    private void changemail() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<usermailchangedata> call = retrofitAPI.updateemail(Verified_Email,appPreferences.getuidpref());
        call.enqueue(new Callback<usermailchangedata>() {
            @Override
            public void onResponse(Call<usermailchangedata> call, Response<usermailchangedata> response) {
                Log.d("TAG1", "onResponse: " + response);
                appPreferences.saveemailpref(Verified_Email);

                if (response.isSuccessful()) {
                    usermailchangedata user = response.body();



                        Toast.makeText(EmailChangeConfirm.this, "Email Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EmailChangeConfirm.this, HomeScreen.class);
                        startActivity(intent);
                        finishAffinity();

                }
            }

            @Override
            public void onFailure(Call<usermailchangedata> call, Throwable t) {

                Toast.makeText(EmailChangeConfirm.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}