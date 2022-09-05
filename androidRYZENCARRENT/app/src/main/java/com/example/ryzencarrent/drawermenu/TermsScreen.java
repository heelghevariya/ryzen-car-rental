package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryzencarrent.R;

public class TermsScreen extends AppCompatActivity {

    ImageView imgback;
    TextView terms, txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_screen);

        imgback = findViewById(R.id.imgBack);
        terms = findViewById(R.id.terms);
        txtTitle = findViewById(R.id.txtTitle);

        String detail = getIntent().getStringExtra("detail");
        terms.setText(detail);
        String ptype = getIntent().getStringExtra("ptype");
        if (ptype.equals("terms")) {
            txtTitle.setText("Terms & Conditions");
        } else if (ptype.equals("privacy")) {
            txtTitle.setText("Privacy Policy");

        } else if (ptype.equals("faqs")) {
            txtTitle.setText("FAQ's");

        } else if (ptype.equals("aboutus")) {
            txtTitle.setText("About Us");

        }

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}