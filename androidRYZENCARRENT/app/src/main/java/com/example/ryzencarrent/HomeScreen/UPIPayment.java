package com.example.ryzencarrent.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.Modals.AddBookings;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.PayUPIModal;
import com.example.ryzencarrent.config;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UPIPayment extends AppCompatActivity {

    TextView send;
    EditText amountEt;
    final int UPI_PAYMENT = 0;
    int total, getVehicle_id, driverID;
    AppPreferences appPreferences;
    String Const_FromDate, Const_ToDate, msg_text;
    AddBookings addBookings;
    PayUPIModal payUPIModal;
    ProgressBar progressUPI;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upipayment);
        appPreferences = new AppPreferences(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        getVehicle_id = bundle.getInt("getVehicle_id", 0);
        total = bundle.getInt("total", 0);
        driverID = bundle.getInt("driverID", 0);
        Const_FromDate = bundle.getString("Const_FromDate");
        Const_ToDate = bundle.getString("Const_ToDate");
        msg_text = bundle.getString("msg_text");
        initializeViews();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amount = amountEt.getText().toString();
                String upiId = "Q793308899@ybl";
                String name = "RyzenCarRental";
                payUsingUpi(amount, upiId, name);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    void initializeViews() {
        send = findViewById(R.id.payupi);
        amountEt = findViewById(R.id.amountpay);
        progressUPI = findViewById(R.id.progressUPI);
        back = findViewById(R.id.back);
        amountEt.setText("" + total);
//        amountEt.setText("1");
    }

    void payUsingUpi(String amount, String upiId, String name) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(UPIPayment.this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(UPIPayment.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                progressUPI.setVisibility(View.VISIBLE);
                funpay();
                Log.d("UPI", "responseStr: " + approvalRefNo);
            } else if ("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(UPIPayment.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UPIPayment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(UPIPayment.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void funpay() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<AddBookings> call = retrofitAPI.addbooking(appPreferences.getuidpref(),
                getVehicle_id,
                driverID,
                Const_FromDate,
                Const_ToDate,
                msg_text);
        call.enqueue(new Callback<AddBookings>() {
            @Override
            public void onResponse(Call<AddBookings> call, Response<AddBookings> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        addBookings = response.body();
                        int status = addBookings.getStatus();

                        PayUPI(total, status);

                    }


                }
            }

            @Override
            public void onFailure(Call<AddBookings> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(UPIPayment.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void PayUPI(int amountEt, int status) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<PayUPIModal> call = retrofitAPI.paywithUPI(amountEt, status);
        call.enqueue(new Callback<PayUPIModal>() {
            @Override
            public void onResponse(Call<PayUPIModal> call, Response<PayUPIModal> response) {
                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        payUPIModal = response.body();
                        int status = payUPIModal.getStatus();
                        progressUPI.setVisibility(View.GONE);

                        Toast.makeText(UPIPayment.this, "Booked successfully.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UPIPayment.this, HomeScreen.class);

                        startActivity(intent);
                        finish();

                    }


                }
            }

            @Override
            public void onFailure(Call<PayUPIModal> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(UPIPayment.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        final Dialog exitDialog = new Dialog(UPIPayment.this);
        exitDialog.setContentView(R.layout.exit_popup_lay);

        exitDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        exitDialog.setCancelable(false);
        exitDialog.setCanceledOnTouchOutside(false);
        LinearLayout alertLay = exitDialog.findViewById(R.id.alertLay);
        TextView noBtn = exitDialog.findViewById(R.id.noBtn);
        TextView yesBtn = exitDialog.findViewById(R.id.yesBtn);

        noBtn.setOnClickListener(arg0 -> exitDialog.dismiss());

        yesBtn.setOnClickListener(arg0 -> {
            Intent intent = new Intent(UPIPayment.this, HomeScreen.class);
            startActivity(intent);
            finish();
        });
        exitDialog.show();


    }
}