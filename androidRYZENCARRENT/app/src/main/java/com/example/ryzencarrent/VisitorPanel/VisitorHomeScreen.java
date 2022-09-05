package com.example.ryzencarrent.VisitorPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.EmailAuthentication;
import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.Modals.VisitorSubscribeadd;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.GetPageDataModal;
import com.example.ryzencarrent.Modals.GetUser;
import com.example.ryzencarrent.Modals.RatingModal;
import com.example.ryzencarrent.adapters.ReviewAdapter;
import com.example.ryzencarrent.Modals.ReviewModal;
import com.example.ryzencarrent.Modals.Subscribe;
import com.example.ryzencarrent.adapters.adaptercar;
import com.example.ryzencarrent.adapters.adaptercar1;
import com.example.ryzencarrent.adapters.adapterphone1;
import com.example.ryzencarrent.Modals.carhelper;
import com.example.ryzencarrent.adapters.phonehelper;
import com.example.ryzencarrent.config;
import com.example.ryzencarrent.drawermenu.TermsScreen;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisitorHomeScreen extends AppCompatActivity implements adapterphone1.ListItemClickListener, adaptercar.ListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    CardView CardSelectCity;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView phoneRecycler, exListFaq, whyryzon;
    RecyclerView.Adapter adapter;
    ReviewAdapter reviewAdapter;
    AppPreferences appPreferences;
    AppBarLayout appBarLayout;
    List<GetUser> getUserList;
    TextInputEditText emailedit;
    VisitorSubscribeadd subscribemodal;
    RatingModal ratingModal;
    TextInputLayout sublay;
    TextView subscribe;
    boolean issubscribe = true;
    boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_home_screen);

        appPreferences = new AppPreferences(getApplicationContext());


        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav);
        toolbar = findViewById(R.id.tolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        CardSelectCity = findViewById(R.id.mCardSelectCity);
        subscribe = findViewById(R.id.subscribe);
        emailedit = findViewById(R.id.emailedit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
//        emailedit.setText(appPreferences.getemailpref());
//        funSubscribe();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                if (offset < -400) {

                    toolbar.setBackgroundColor(getResources().getColor(R.color.tool_top));

                } else {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.tool_bottom));

                }
            }
        });
        phoneRecycler = findViewById(R.id.my_recycler);
        exListFaq = findViewById(R.id.exListFaq);
        whyryzon = findViewById(R.id.whyryzoncar);
        sublay = findViewById(R.id.Email);

        phoneRecycler();
        reviewRecycler();
        whyryzon();

        emailedit.addTextChangedListener(new TextWatcher() {
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
                String emailInput = sublay.getEditText().getText().toString().trim();

                if (emailInput.length() > 40) {
                    sublay.setError("E-mail too long");
                    valid = false;
                    subscribe.setBackgroundResource(R.color.light_white);

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    sublay.setError("Please enter a valid email address");
                    valid = false;
                    subscribe.setBackgroundResource(R.color.light_white);

                } else {
                    sublay.setError(null);
                    valid = true;
                    subscribe.setEnabled(true);
                }
            }
        });
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        emailedit.setEnabled(true);

        CardSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorHomeScreen.this, VisiterDateSelection.class);
                startActivity(intent);
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid) {
                    funSubscribe();

                } else {
                    Toast.makeText(VisitorHomeScreen.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void funSubscribe() {
        if (issubscribe) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(config.Email_signup_BASE_URL)
                    // on below line we are calling add Converter
                    // factory as GSON converter factory.
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();
            // below line is to create an instance for our retrofit api class.
            RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

            Call<VisitorSubscribeadd> call = retrofitAPI.addvisitorsub(emailedit.getText().toString());
            call.enqueue(new Callback<VisitorSubscribeadd>() {
                @Override
                public void onResponse(Call<VisitorSubscribeadd> call, Response<VisitorSubscribeadd> response) {
                    if (response.isSuccessful()) {


                        if (response.body() != null) {
                            subscribemodal = response.body();
                            if (subscribemodal.getStatus() == 0) {
                                subscribe.setText("Subscribed");
                                emailedit.setEnabled(false);
                                subscribe.setTextColor(getResources().getColor(R.color.green));
                                issubscribe = false;
                            } else {
                                Toast.makeText(VisitorHomeScreen.this, "Already Subscribed with this Email!", Toast.LENGTH_SHORT).show();
                                issubscribe = false;

                            }

                        }


                    }
                }

                @Override
                public void onFailure(Call<VisitorSubscribeadd> call, Throwable t) {
                    // displaying an error message in toast
                    Toast.makeText(VisitorHomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(config.Email_signup_BASE_URL)
                    // on below line we are calling add Converter
                    // factory as GSON converter factory.
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();
            // below line is to create an instance for our retrofit api class.
            RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

            Call<Subscribe> call = retrofitAPI.removesub(emailedit.getText().toString());
            call.enqueue(new Callback<Subscribe>() {
                @Override
                public void onResponse(Call<Subscribe> call, Response<Subscribe> response) {
                    if (response.isSuccessful()) {


                        if (response.body() != null) {
                            subscribe.setText("Subscribe");
                            subscribe.setTextColor(getResources().getColor(R.color.red));
                            issubscribe = true;
                            emailedit.setEnabled(true);

                        }


                    }
                }

                @Override
                public void onFailure(Call<Subscribe> call, Throwable t) {
                    // displaying an error message in toast
                    Toast.makeText(VisitorHomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    private void phoneRecycler() {

        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<phonehelper> phonelocations = new ArrayList<>();
        phonelocations.add(new phonehelper(R.drawable.h1));
        phonelocations.add(new phonehelper(R.drawable.h2));
        phonelocations.add(new phonehelper(R.drawable.h3));


        adapter = new adapterphone1(phonelocations, this);
        phoneRecycler.setAdapter(adapter);


    }

    private void whyryzon() {
        whyryzon.setHasFixedSize(true);
        whyryzon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<carhelper> phoneLaocation = new ArrayList<>();
        phoneLaocation.add(new carhelper(R.drawable.h4));
        phoneLaocation.add(new carhelper(R.drawable.h6));
        phoneLaocation.add(new carhelper(R.drawable.h7));


        adapter = new adaptercar1(phoneLaocation, this);
        whyryzon.setAdapter(adapter);


    }

    private void reviewRecycler() {

        exListFaq.setHasFixedSize(true);
        exListFaq.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<ReviewModal>> call = retrofitAPI.getReviews();
        call.enqueue(new Callback<List<ReviewModal>>() {
            @Override
            public void onResponse(Call<List<ReviewModal>> call, Response<List<ReviewModal>> response) {
                if (response.isSuccessful()) {
                    List<ReviewModal> reviewModalList = response.body();

//                 for (int i = 0; i < reviewModalList.size(); i++) {

//                     reviewModals.add(new ReviewModal(reviewModalList.get(i).getUser_name(),
//                             reviewModalList.get(i).getReview(),reviewModalList.get(i).getRating()));
                    reviewAdapter = new ReviewAdapter(VisitorHomeScreen.this, reviewModalList);
                    exListFaq.setAdapter(reviewAdapter);
//                 }
                }
            }

            @Override
            public void onFailure(Call<List<ReviewModal>> call, Throwable t) {
                Toast.makeText(VisitorHomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {


//            Intent mIntent;
//            switch (clickedItemIndex){
//                case 0: //first item in Recycler view
//                    mIntent  = new Intent(FirstActivity.this, samsung.class);
//                    startActivity(mIntent);
//                    break;
//                case 1: //second item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, vivo.class);
//                    startActivity(mIntent);
//                    break;
//                case 2: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, apple.class);
//                    startActivity(mIntent);
//                    break;
        //              case 3: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, realme.class);
//                    startActivity(mIntent);
//                    break;
//                case 4: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, poco.class);
//                    startActivity(mIntent);
//                    break;
//
//        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        VisitorHomeScreen.this.drawerLayout.closeDrawers();

        switch (item.getItemId()) {


            case R.id.terms_condition:
                getData("terms");
                break;

            case R.id.private_policy:
                getData("privacy");

                break;

            case R.id.fq:
                getData("faqs");

                break;

            case R.id.about_us:
                getData("aboutus");
                break;

            case R.id.SigUp:
                startActivity(new Intent(VisitorHomeScreen.this, EmailAuthentication.class));
                break;

            case R.id.login:
                startActivity(new Intent(VisitorHomeScreen.this, LoginScreen.class));
                break;

            case R.id.inquiry:
                startActivity(new Intent(VisitorHomeScreen.this, VisiterInquiryScreen.class));

                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
            finish();
        }

    }

    private void getData(String ptype) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<GetPageDataModal>> call = retrofitAPI.getpagedata(ptype);
        call.enqueue(new Callback<List<GetPageDataModal>>() {
            @Override
            public void onResponse(Call<List<GetPageDataModal>> call, Response<List<GetPageDataModal>> response) {
                if (response.isSuccessful()) {
                    List<GetPageDataModal> getUserReviewModalList = response.body();

                    String getDetail = getUserReviewModalList.get(0).getDetail();
                    Intent intent = new Intent(VisitorHomeScreen.this, TermsScreen.class);
                    intent.putExtra("detail", getDetail);
                    intent.putExtra("ptype", ptype);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<List<GetPageDataModal>> call, Throwable t) {
                Toast.makeText(VisitorHomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}