package com.example.ryzencarrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

public class VerifyOTP extends AppCompatActivity {

    ImageView close;
    TextView next;
    int id;
    PinView edtOtp;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);

        id = getIntent().getIntExtra("forgototp", 0);


        edtOtp = (PinView) findViewById(R.id.otpforgot);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        close = findViewById(R.id.close);
        next = findViewById(R.id.verifycode);

        close.setOnClickListener(view -> {
            Intent intent = new Intent(VerifyOTP.this, LoginScreen.class);
            startActivity(intent);
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OtpCode = edtOtp.getText().toString();
                if (OtpCode.equals("")) {
                    Toast.makeText(VerifyOTP.this, "Enter Otp", Toast.LENGTH_SHORT).show();
                } else {

//                    dialog.show();

                    if (id == Integer.parseInt(OtpCode)) {
                        Toast.makeText(VerifyOTP.this, "Email Verify Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerifyOTP.this, ChangePassword.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(VerifyOTP.this, "You entered wrong otp!", Toast.LENGTH_SHORT).show();

                    }

                }
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

    public void onBackPressed() {
        Intent intent = new Intent(VerifyOTP.this, LoginScreen.class);
        startActivity(intent);

    }
}