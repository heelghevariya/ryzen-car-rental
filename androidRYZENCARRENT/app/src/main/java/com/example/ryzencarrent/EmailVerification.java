package com.example.ryzencarrent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmailVerification extends AppCompatActivity {

    int id;
    PinView edtOtp;
    TextView btnsubmit;
    ProgressDialog dialog;
    ImageView close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        id = getIntent().getIntExtra("otp", 0);
        close_btn = (ImageView) findViewById(R.id.close);


        edtOtp = (PinView) findViewById(R.id.setpin);
        btnsubmit = (TextView) findViewById(R.id.verifycode);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailVerification.this, LoginScreen.class);
                startActivity(intent);

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OtpCode = edtOtp.getText().toString();
                if (OtpCode.equals("")) {
                    Toast.makeText(EmailVerification.this, "Enter Otp", Toast.LENGTH_SHORT).show();
                } else {

//                    dialog.show();

                    if (id == Integer.parseInt(OtpCode)) {
                        Toast.makeText(EmailVerification.this, "Email Verify Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EmailVerification.this, SignUpActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EmailVerification.this, "You entered wrong otp!", Toast.LENGTH_SHORT).show();

                    }
          /*          StringRequest str = new StringRequest(Request.Method.POST, config.BASE_URL + "registerotp.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (obj.getString("status").equals("Yes")) {
                                    Toast.makeText(EmailVerification.this, "Verify Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EmailVerification.this, SignUpActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(EmailVerification.this, "fail...", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EmailVerification.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("userid", id);
                            params.put("otpcode", OtpCode);
                            return params;
                        }
                    };
                    RequestQueue q = Volley.newRequestQueue(EmailVerification.this);
                    q.add(str);*/
                    //API
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EmailVerification.this, LoginScreen.class);
        startActivity(intent);

    }
}