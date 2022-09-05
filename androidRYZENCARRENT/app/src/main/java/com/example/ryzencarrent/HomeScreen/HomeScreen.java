package com.example.ryzencarrent.HomeScreen;


import static com.example.ryzencarrent.adapters.AppPreferences.APP_SHARED_PREFS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.ryzencarrent.LoginScreen;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.GetPageDataModal;
import com.example.ryzencarrent.Modals.GetUser;
import com.example.ryzencarrent.Modals.GetUserReviewModal;
import com.example.ryzencarrent.Modals.RatingModal;
import com.example.ryzencarrent.adapters.ReviewAdapter;
import com.example.ryzencarrent.Modals.ReviewModal;
import com.example.ryzencarrent.Modals.Subscribe;
import com.example.ryzencarrent.adapters.adaptercar;
import com.example.ryzencarrent.adapters.adapterphone;
import com.example.ryzencarrent.Modals.carhelper;
import com.example.ryzencarrent.adapters.phonehelper;
import com.example.ryzencarrent.config;
import com.example.ryzencarrent.drawermenu.BookingHistory;
import com.example.ryzencarrent.drawermenu.InquiryScreen;
import com.example.ryzencarrent.drawermenu.KYCConfirm;
import com.example.ryzencarrent.drawermenu.KYCPending;
import com.example.ryzencarrent.drawermenu.KYCScreen;
import com.example.ryzencarrent.drawermenu.MyProfileScreen;
import com.example.ryzencarrent.drawermenu.ReviewNotAligableScreen;
import com.example.ryzencarrent.drawermenu.ReviewRatingScreen;
import com.example.ryzencarrent.drawermenu.TermsScreen;
import com.example.ryzencarrent.drawermenu.ViewRatingReviewScreen;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity implements adapterphone.ListItemClickListener, adaptercar.ListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

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
    Subscribe subscribemodal;
    RatingModal ratingModal;
    TextView subscribe;
    boolean issubscribe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
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
        emailedit.setText(appPreferences.getemailpref());
        funSubscribe();

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

        phoneRecycler();
        reviewRecycler();
        whyryzon();


        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        setDrawerHeader();
        navigationView.setNavigationItemSelectedListener(this);

        CardSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, DateSelectionScreen.class);
                startActivity(intent);
            }
        });

    }

    private void funSubscribe() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Subscribe> call = retrofitAPI.chksubscribed(appPreferences.getemailpref());
        call.enqueue(new Callback<Subscribe>() {
            @Override
            public void onResponse(Call<Subscribe> call, Response<Subscribe> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        subscribemodal = response.body();
                        if (subscribemodal.getStatus() == 1) {
                            subscribe.setText("Subscribed");
                            issubscribe = false;

                            subscribe.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            issubscribe = true;

                            subscribe.setText("Subscribe");
                            subscribe.setTextColor(getResources().getColor(R.color.red));
                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<Subscribe> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    Call<Subscribe> call = retrofitAPI.addsubscriber(appPreferences.getemailpref());
                    call.enqueue(new Callback<Subscribe>() {
                        @Override
                        public void onResponse(Call<Subscribe> call, Response<Subscribe> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    subscribemodal = response.body();
                                    subscribe.setText("Subscribed");
                                    subscribe.setTextColor(getResources().getColor(R.color.green));
                                    issubscribe = false;
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<Subscribe> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
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

                    Call<Subscribe> call = retrofitAPI.removesub(appPreferences.getemailpref());
                    call.enqueue(new Callback<Subscribe>() {
                        @Override
                        public void onResponse(Call<Subscribe> call, Response<Subscribe> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    subscribemodal = response.body();
                                    subscribe.setText("Subscribe");
                                    subscribe.setTextColor(getResources().getColor(R.color.red));
                                    issubscribe = true;
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<Subscribe> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });
    }

    private void setDrawerHeader() {
        View header = navigationView.getHeaderView(0);
        TextView username = header.findViewById(R.id.username);
        TextView email = header.findViewById(R.id.email);
        username.setText(appPreferences.getunamepref());
        email.setText(appPreferences.getemailpref());
    }

    private void phoneRecycler() {

        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<phonehelper> phonelocations = new ArrayList<>();
        phonelocations.add(new phonehelper(R.drawable.h1));
        phonelocations.add(new phonehelper(R.drawable.h2));
        phonelocations.add(new phonehelper(R.drawable.h3));


        adapter = new adapterphone(phonelocations, this);
        phoneRecycler.setAdapter(adapter);


    }

    private void whyryzon() {
        whyryzon.setHasFixedSize(true);
        whyryzon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<carhelper> phoneLaocation = new ArrayList<>();
        phoneLaocation.add(new carhelper(R.drawable.h4));
        phoneLaocation.add(new carhelper(R.drawable.h6));
        phoneLaocation.add(new carhelper(R.drawable.h7));


        adapter = new adaptercar(phoneLaocation, HomeScreen.this);
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
                    reviewAdapter = new ReviewAdapter(HomeScreen.this, reviewModalList);
                    exListFaq.setAdapter(reviewAdapter);
//                 }
                }
            }

            @Override
            public void onFailure(Call<List<ReviewModal>> call, Throwable t) {
                Toast.makeText(HomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
        HomeScreen.this.drawerLayout.closeDrawers();

        switch (item.getItemId()) {

            case R.id.log_out:

                SharedPreferences _sharedPrefs;
                _sharedPrefs = getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
                _sharedPrefs.edit().clear().commit();
                startActivity(new Intent(HomeScreen.this, LoginScreen.class));
                finish();
                break;

            case R.id.profile_setting:

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                Call<List<GetUser>> call = retrofitAPI.getuser(appPreferences.getuidpref());
                call.enqueue(new Callback<List<GetUser>>() {
                    @Override
                    public void onResponse(Call<List<GetUser>> call, Response<List<GetUser>> response) {
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                getUserList = response.body();
                                String getAddress = getUserList.get(0).getAddress();
                                String getCity = getUserList.get(0).getCity();
                                String getState = getUserList.get(0).getState();
                                String getCountry = getUserList.get(0).getCountry();
                                String getMobile_no = getUserList.get(0).getMobile_no();
                                String getDob = getUserList.get(0).getDob();
                                Intent intent = new Intent(HomeScreen.this, MyProfileScreen.class);
                                Bundle bundle = new Bundle();

                                bundle.putString("getAddress", getAddress);
                                bundle.putString("getCity", getCity);
                                bundle.putString("getState", getState);
                                bundle.putString("getCountry", getCountry);
                                bundle.putString("getMobile_no", getMobile_no);
                                bundle.putString("getDob", getDob);


                                intent.putExtras(bundle);
                                startActivity(intent);

                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetUser>> call, Throwable t) {
                        // displaying an error message in toast
                        Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                    }
                });


//                startActivity(new Intent(HomeScreen.this, MyProfileScreen.class));
//                finish();
                break;

            case R.id.review_rating:
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI1 = retrofit1.create(RetrofitAPI.class);

                Call<RatingModal> call1 = retrofitAPI1.chkisbooked(appPreferences.getuidpref());
                call1.enqueue(new Callback<RatingModal>() {
                    @Override
                    public void onResponse(Call<RatingModal> call, Response<RatingModal> response) {
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                ratingModal = response.body();
                                if (ratingModal.getStatus() == 0) {
                                    startActivity(new Intent(HomeScreen.this, ReviewNotAligableScreen.class));

                                } else if (ratingModal.getStatus() == 1) {
                                    Retrofit retrofit1 = new Retrofit.Builder()
                                            .baseUrl(config.Email_signup_BASE_URL)
                                            // on below line we are calling add Converter
                                            // factory as GSON converter factory.
                                            .addConverterFactory(GsonConverterFactory.create())
                                            // at last we are building our retrofit builder.
                                            .build();
                                    // below line is to create an instance for our retrofit api class.
                                    RetrofitAPI retrofitAPI1 = retrofit1.create(RetrofitAPI.class);

                                    Call<RatingModal> call1 = retrofitAPI1.chkreviewraring(appPreferences.getuidpref());
                                    call1.enqueue(new Callback<RatingModal>() {
                                        @Override
                                        public void onResponse(Call<RatingModal> call, Response<RatingModal> response) {
                                            if (response.isSuccessful()) {


                                                if (response.body() != null) {
                                                    ratingModal = response.body();
                                                    if (ratingModal.getStatus() == 0) {
                                                        startActivity(new Intent(HomeScreen.this, ReviewRatingScreen.class));

                                                    } else if (ratingModal.getStatus() == 1) {
                                                        startActivity(new Intent(HomeScreen.this, ViewRatingReviewScreen.class));


                                                    }


                                                }


                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<RatingModal> call, Throwable t) {
                                            // displaying an error message in toast
                                            Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }


                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<RatingModal> call, Throwable t) {
                        // displaying an error message in toast
                        Toast.makeText(HomeScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                    }
                });


                break;

            case R.id.booking:
                startActivity(new Intent(HomeScreen.this, BookingHistory.class));
                break;

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


            case R.id.inquiry:
                startActivity(new Intent(HomeScreen.this, InquiryScreen.class));

                break;
            case R.id.kyc:
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(config.Email_signup_BASE_URL)
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                RetrofitAPI retrofitAPI2 = retrofit2.create(RetrofitAPI.class);

                Call<GetUserReviewModal> call2 = retrofitAPI2.chkiskyc(appPreferences.getuidpref());
                call2.enqueue(new Callback<GetUserReviewModal>() {
                    @Override
                    public void onResponse(Call<GetUserReviewModal> call, Response<GetUserReviewModal> response) {
                        if (response.isSuccessful()) {
                            GetUserReviewModal getUserReviewModalList = response.body();

                            int getStatus = getUserReviewModalList.getStatus();

                            if (getStatus == 0) {
                                startActivity(new Intent(HomeScreen.this, KYCScreen.class));

                            } else if (getStatus == 1) {
                                Retrofit retrofit2 = new Retrofit.Builder()
                                        .baseUrl(config.Email_signup_BASE_URL)
                                        // on below line we are calling add Converter
                                        // factory as GSON converter factory.
                                        .addConverterFactory(GsonConverterFactory.create())
                                        // at last we are building our retrofit builder.
                                        .build();
                                // below line is to create an instance for our retrofit api class.
                                RetrofitAPI retrofitAPI2 = retrofit2.create(RetrofitAPI.class);

                                Call<GetUserReviewModal> call2 = retrofitAPI2.getkycstatus(appPreferences.getuidpref());
                                call2.enqueue(new Callback<GetUserReviewModal>() {
                                    @Override
                                    public void onResponse(Call<GetUserReviewModal> call, Response<GetUserReviewModal> response) {
                                        if (response.isSuccessful()) {
                                            GetUserReviewModal getUserReviewModalList = response.body();

                                            int getStatus = getUserReviewModalList.getStatus();

                                            if (getStatus == 0) {
                                                startActivity(new Intent(HomeScreen.this, KYCPending.class));

                                            } else if (getStatus == 1) {

                                                startActivity(new Intent(HomeScreen.this, KYCConfirm.class));



                                            }else if (getStatus == 2) {

                                                startActivity(new Intent(HomeScreen.this, KYCScreen.class));



                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<GetUserReviewModal> call, Throwable t) {
                                        Toast.makeText(HomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                    }
                                });



                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserReviewModal> call, Throwable t) {
                        Toast.makeText(HomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });










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
                    Intent intent = new Intent(HomeScreen.this, TermsScreen.class);
                    intent.putExtra("detail", getDetail);
                    intent.putExtra("ptype", ptype);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<List<GetPageDataModal>> call, Throwable t) {
                Toast.makeText(HomeScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}