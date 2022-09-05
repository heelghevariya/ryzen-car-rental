package com.example.ryzencarrent;

import static com.example.ryzencarrent.Constants.Forgot_Mail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePassword extends AppCompatActivity {

    ImageView close;
    TextView changepass;
    TextInputEditText editconfirmpass, editpass;
    TextInputLayout Newpass, confirmPass;
    RecyclerData user;
    boolean valid = false;
    boolean valid1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_password);

        close = findViewById(R.id.close);
        changepass = findViewById(R.id.changepass);
        changepass.setEnabled(false);
        changepass.setBackgroundResource(R.drawable.button_shape_disable);

        editconfirmpass = findViewById(R.id.editconfirmpass);
        editpass = findViewById(R.id.editpass);
        Newpass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmpassword);

        close.setOnClickListener(view -> {
            Intent intent = new Intent(ChangePassword.this, LoginScreen.class);
            startActivity(intent);
        });




        editpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!editpass.getText().toString().equals(editconfirmpass.getText().toString())) {
                    confirmPass.setError("Confirm password must be same");
                    valid1 = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);
                }
                if (valid && valid1) {
                    changepass.setBackgroundResource(R.drawable.shape_plp_book);
                    changepass.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newpassInput = Newpass.getEditText().getText().toString().trim();

                if (newpassInput.isEmpty()) {
                    Newpass.setError("Field can't be empty");
                    valid = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() > 15) {
                    Newpass.setError("password is too long");
                    valid = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (newpassInput.length() < 8) {
                    Newpass.setError("password must be atleast 8 character long");
                    valid = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);

                } else {
                    Newpass.setError(null);
                    valid = true;
                }
            }
        });
        editconfirmpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    changepass.setBackgroundResource(R.drawable.shape_plp_book);
                    changepass.setEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String conpassInput = confirmPass.getEditText().getText().toString().trim();

                if (conpassInput.isEmpty()) {
                    confirmPass.setError("Field can't be empty");
                    valid1 = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (conpassInput.length() > 15) {
                    confirmPass.setError("password is too long");
                    valid1 = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (conpassInput.length() < 8) {
                    confirmPass.setError("password must be atleast 8 character long");
                    valid1 = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!editpass.getText().toString().equals(editconfirmpass.getText().toString())) {
                    confirmPass.setError("Confirm password must be same");
                    valid1 = false;
                    changepass.setBackgroundResource(R.drawable.button_shape_disable);
                } else {
                    confirmPass.setError(null);
                    valid1 = true;
                }
            }
        });
        changepass.setOnClickListener(view -> {
            String confirmpass = editpass.getText().toString();

//            if (editpass.getText().toString().equals(editconfirmpass.getText().toString())) {
                if (valid && valid1) {
                    changepassword(confirmpass);

                }
//            } else {
//            }

        });
    }

    private void changepassword(String confirmpass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.Email_signup_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<RecyclerData> call = retrofitAPI.PostForgetpassword(Forgot_Mail, confirmpass);
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                Log.d("TAG1", "onResponse: " + response.body());

                if (response.isSuccessful()) {
                    user = response.body();

                    if (user.getStatus() == 1) {
                        Intent intent = new Intent(ChangePassword.this, PassChangeDone.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ChangePassword.this, "Failed to confirm password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                Toast.makeText(ChangePassword.this, "No Internet Connection" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(ChangePassword.this, LoginScreen.class);
        startActivity(intent);

    }
}