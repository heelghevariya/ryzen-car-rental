package com.example.ryzencarrent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.adapters.AppPreferences;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
appPreferences=new AppPreferences(getApplicationContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.logo);
        logo = findViewById(R.id.ryzoncar);
        slogan = findViewById(R.id.slogen);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (appPreferences.isloggedin()) {
                     intent = new Intent(SplashScreen.this, HomeScreen.class);

                }else {
                     intent = new Intent(SplashScreen.this, LoginScreen.class);

                }
                Pair[] Pairs = new Pair[2];
                Pairs[0] = new Pair<View, String>(image, "logo_img");
                Pairs[1] = new Pair<View, String>(logo, "logo_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, Pairs);
                startActivity(intent, options.toBundle());
            finish();
        }
    },SPLASH_SCREEN);
}
}