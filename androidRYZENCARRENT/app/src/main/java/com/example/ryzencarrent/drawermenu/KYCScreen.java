package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ryzencarrent.ChangePassword;
import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.R;

public class KYCScreen extends AppCompatActivity {
ImageView back;
LinearLayout next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycscreen);

        next = findViewById(R.id.mLytDetailProceed);
        back = findViewById(R.id.imgBack);
        back.setOnClickListener(view -> {
           onBackPressed();
        });

        next.setOnClickListener(view -> {
            Intent intent = new Intent(KYCScreen.this, AadhaarVerifyScreen.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KYCScreen.this, HomeScreen.class);
        startActivity(intent);
        finishAffinity();
    }
}