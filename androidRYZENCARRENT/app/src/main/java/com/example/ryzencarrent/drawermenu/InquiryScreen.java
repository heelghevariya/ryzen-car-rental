package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.CompanyModal;
import com.example.ryzencarrent.Modals.InquiryModal;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InquiryScreen extends AppCompatActivity {
    TextInputEditText edituser, editemail, editphone, editmessage;
    ImageView expand, back, imgExpandMore;
    AppPreferences appPreferences;
    LinearLayout mLytDetailProceed, rvdriver;
    ProgressBar pb;
    boolean valid = false;
    boolean valid1 = false;
    TextInputLayout mobilelay, messagelay;
    TextView txt_email, txt_mobile, txt_address;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_screen);
        appPreferences = new AppPreferences(getApplicationContext());

        edituser = findViewById(R.id.username);
        editemail = findViewById(R.id.email);
        editphone = findViewById(R.id.mobile);
        editmessage = findViewById(R.id.message);
        expand = findViewById(R.id.imgExpandMore);
        mobilelay = findViewById(R.id.mobl);
        messagelay = findViewById(R.id.messagel);
        rvdriver = findViewById(R.id.rvdriver);
        back = findViewById(R.id.back);
        pb = findViewById(R.id.pb);
        txt_email = findViewById(R.id.txt_email);
        txt_mobile = findViewById(R.id.txt_mobile);
        txt_address = findViewById(R.id.txt_address);
        imgExpandMore = findViewById(R.id.imgExpandMore);


        mLytDetailProceed = findViewById(R.id.mLytDetailProceed);
        mLytDetailProceed.setEnabled(false);
        mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

        editphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String mobileInput = mobilelay.getEditText().getText().toString().trim();

                if (mobileInput.length() > 10) {
                    mobilelay.setError("mobile number Must be 10 Digit Long");
                    valid = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!mobileInput.matches("^[6-9]{1}[0-9]{9}$")) {
                    mobilelay.setError("Please Enter Valid Mobile Number");
                    valid = false;
                } else {
                    mobilelay.setError(null);
                    valid = true;
                }
            }
        });
        editmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String MessageInput = messagelay.getEditText().getText().toString().trim();

                if (MessageInput.isEmpty()) {
                    messagelay.setError("Field can't be empty");
                    valid1 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (MessageInput.length() > 100) {
                    messagelay.setError("Message Is Too Long");
                    valid1 = false;
                    mLytDetailProceed.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    messagelay.setError(null);
                    valid1 = true;
                    mLytDetailProceed.setEnabled(true);
                    mLytDetailProceed.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });

        edituser.setText(appPreferences.getunamepref());
        editemail.setText(appPreferences.getemailpref());
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        mLytDetailProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid1) {
                    submitData();
                }
            }
        });
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    expandata();
                    click = false;
                } else {
                    rvdriver.setVisibility(View.GONE);
                    imgExpandMore.setImageResource(R.drawable.ic_expand_more);
                    click = true;

                }


            }
        });

    }

    private void expandata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CompanyModal>> call = retrofitAPI.getcontactinfo();
        call.enqueue(new Callback<List<CompanyModal>>() {
            @Override
            public void onResponse(Call<List<CompanyModal>> call, Response<List<CompanyModal>> response) {
                if (response.isSuccessful()) {
                    rvdriver.setVisibility(View.VISIBLE);
                    imgExpandMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                    List<CompanyModal> reviewModalList = response.body();
                    String getAddress = reviewModalList.get(0).getAddress();
                    String getContact_no = reviewModalList.get(0).getContact_no();
                    String getEmail_id = reviewModalList.get(0).getEmail_id();
                    txt_email.setText(getEmail_id);
                    txt_mobile.setText(getContact_no);
                    txt_address.setText(getAddress);

                }
            }

            @Override
            public void onFailure(Call<List<CompanyModal>> call, Throwable t) {
                Toast.makeText(InquiryScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void submitData() {
        pb.setVisibility(View.VISIBLE);
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI1 = retrofit1.create(RetrofitAPI.class);

        Call<InquiryModal> call1 = retrofitAPI1.addinquiry(appPreferences.getunamepref(), appPreferences.getemailpref(), editphone.getText().toString(), editmessage.getText().toString());
        call1.enqueue(new Callback<InquiryModal>() {
            @Override
            public void onResponse(Call<InquiryModal> call, Response<InquiryModal> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        InquiryModal inquiryModal = response.body();
                        pb.setVisibility(View.GONE);
                        onBackPressed();
                        Toast.makeText(InquiryScreen.this, "Inquiry sent successfully.", Toast.LENGTH_SHORT).show();

                                  /*  if (ratingModal.getStatus()==0) {
                                        startActivity(new Intent(HomeScreen.this, ReviewRatingScreen.class));

                                    }else if(ratingModal.getStatus()==1){
                                        startActivity(new Intent(HomeScreen.this, ViewRatingReviewScreen.class));



                                    }
*/

                    }


                }
            }

            @Override
            public void onFailure(Call<InquiryModal> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(InquiryScreen.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}