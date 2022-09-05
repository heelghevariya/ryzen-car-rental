package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.R;

public class KYCConfirm extends AppCompatActivity {
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycconfirm);

        back_btn = findViewById(R.id.back);

        back_btn.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    public void onBackPressed() {
        finish();
    }

}