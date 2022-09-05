package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.R;

public class KYCPending extends AppCompatActivity {
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycpending);

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(KYCPending.this, HomeScreen.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KYCPending.this, HomeScreen.class);
        startActivity(intent);
    }
}