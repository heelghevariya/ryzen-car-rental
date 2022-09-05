package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.Constants;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.RetrofitAPI;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.example.ryzencarrent.Modals.KYCModal;
import com.example.ryzencarrent.config;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrivingLicenseDetailScreen extends AppCompatActivity {
    ImageView back, adharfrontimg, adharbackimg;
    TextInputEditText dno;
    Button front_btn, back_btn;
    LinearLayout next;
    TextView txtimg1, txtimg2;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST;
    private Uri filepath1;
    private Uri filepath2;
    TextInputLayout licencenolay;
    boolean valid = false;
    boolean valid1 = false;
    private String T_filepath1;
    private String T_filepath2;
    private Bitmap bitmap;
    int n = 0;
    public static final String UPLOAD_URL = "http://192.168.1.8/api/addkyc.php";
    AppPreferences appPreferences;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_license_detail_screen);
        appPreferences = new AppPreferences(getApplicationContext());
        pb = findViewById(R.id.pb);

        next = findViewById(R.id.mLytDetailProceed);
        front_btn = findViewById(R.id.front_img);
        back_btn = findViewById(R.id.back_img);
        dno = findViewById(R.id.licenseno);
        txtimg1 = findViewById(R.id.txtimg1);
        txtimg2 = findViewById(R.id.txtimg2);
        back = findViewById(R.id.imgBack);
        licencenolay = findViewById(R.id.licencenol);
        adharfrontimg = findViewById(R.id.adar1);
        adharbackimg = findViewById(R.id.adar2);

        dno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String licencenoInput = licencenolay.getEditText().getText().toString().trim();

                if (licencenoInput.isEmpty()) {
                    licencenolay.setError("Field can't be empty");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (licencenoInput.length() > 15) {
                    licencenolay.setError("Driving Licence Must be 15 Digit Long");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!licencenoInput.matches("^[A-Z]{2}[0-9]{13}$")) {
                    licencenolay.setError("Enter Valid Licence Number(EX: GJXXXXXXXXXXXXX)");
                    valid = false;
                } else {
                    licencenolay.setError(null);
                    valid = true;
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });


        front_btn.setOnClickListener(view -> {
            n = 1;
            ShowFileChooser();

        });
        back_btn.setOnClickListener(view -> {
            n = 2;
            ShowFileChooser();
        });
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        next.setOnClickListener(view -> {
            if (valid) {
                if (filepath1 != null && filepath2 != null) {
                    Constants.lno = dno.getText().toString();
                    Constants.limg1 = T_filepath1;
                    Constants.limg2 = T_filepath2;
                    File file = new File(Constants.aimg1);
                    File file2 = new File(Constants.aimg2);
                    File file3 = new File(Constants.pimg);
                    File file4 = new File(Constants.limg1);
                    File file5 = new File(Constants.limg2);
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
                    RequestBody getuidpref = RequestBody.create(MediaType.parse("text/plain"), "" + appPreferences.getuidpref());
                    RequestBody getemailpref = RequestBody.create(MediaType.parse("text/plain"), appPreferences.getemailpref());
                    RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Constants.fname);
                    RequestBody ano = RequestBody.create(MediaType.parse("text/plain"), Constants.ano);
                    RequestBody pno = RequestBody.create(MediaType.parse("text/plain"), Constants.pno);
                    RequestBody lno = RequestBody.create(MediaType.parse("text/plain"), Constants.lno);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
                    RequestBody requestFile1 = RequestBody.create(MediaType.parse("*/*"), file2);
                    RequestBody requestFile2 = RequestBody.create(MediaType.parse("*/*"), file3);
                    RequestBody requestFile3 = RequestBody.create(MediaType.parse("*/*"), file4);
                    RequestBody requestFile4 = RequestBody.create(MediaType.parse("*/*"), file5);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("aimg1", file.getName(), requestFile);
                    MultipartBody.Part body1 = MultipartBody.Part.createFormData("aimg2", file2.getName(), requestFile1);
                    MultipartBody.Part body2 = MultipartBody.Part.createFormData("pimg", file3.getName(), requestFile2);
                    MultipartBody.Part body3 = MultipartBody.Part.createFormData("limg1", file4.getName(), requestFile3);
                    MultipartBody.Part body4 = MultipartBody.Part.createFormData("limg2", file5.getName(), requestFile4);
                    Call<KYCModal> call1 = retrofitAPI1.addkyc(getuidpref, getemailpref
                            , fname
                            , ano
                            , pno
                            , lno
                            , body
                            , body1
                            , body2
                            , body3
                            , body4
                    );
                    call1.enqueue(new Callback<KYCModal>() {
                        @Override
                        public void onResponse(Call<KYCModal> call, Response<KYCModal> response) {
                            if (response.isSuccessful()) {


                                if (response.body() != null) {
                                    pb.setVisibility(View.GONE);
                                    Intent intent = new Intent(DrivingLicenseDetailScreen.this, KYCPending.class);
                                    startActivity(intent);
//                                Toast.makeText(DrivingLicenseDetailScreen.this, "Inquiry sent successfully.", Toast.LENGTH_SHORT).show();


                                }

                            } else {

                                ResponseBody errorBody = response.errorBody();

                                Gson gson = new Gson();

                                try {

                                    Response errorResponse = gson.fromJson(errorBody.string(), Response.class);
                                    Toast.makeText(DrivingLicenseDetailScreen.this, "" + errorResponse.toString(), Toast.LENGTH_SHORT).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<KYCModal> call, Throwable t) {
                            // displaying an error message in toast
                            Toast.makeText(DrivingLicenseDetailScreen.this, "Fail to get the data.." + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


//                uploadImage();


                } else {
                    Toast.makeText(this, "Please Upload Images", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }


    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            if (n == 1) {
                filepath1 = data.getData();

                try {
                    T_filepath1 = getPath(filepath1);
                    File fi = new File(T_filepath1);

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath1);
                    adharfrontimg.setImageBitmap(bitmap);

//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
//                imageView.setImageBitmap(bitmap);
                    txtimg1.setText(fi.getName());
                    // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
                } catch (Exception ex) {

                }
            } else if (n == 2) {
                filepath2 = data.getData();
                try {
                    T_filepath2 = getPath(filepath2);

                    File fi = new File(T_filepath2);
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath2);
                    adharbackimg.setImageBitmap(bitmap);

//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
//                imageView.setImageBitmap(bitmap);
                    txtimg2.setText(fi.getName());
                    // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
                } catch (Exception ex) {

                }
            }

        }
    }

    private String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
        );
        cursor.moveToFirst();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void uploadImage() {
      /*  String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();*/
        try {
            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
//                    .addParameter("" + appPreferences.getuidpref(), "uid")
                    /*   .addParameter(appPreferences.getemailpref(), "mail")
                       .addParameter(Constants.fname, "fname")
                       .addParameter(Constants.ano, "ano")
                       .addParameter(Constants.pno, "pno")
                       .addParameter(Constants.lno, "lno")*/
                    .addFileToUpload(Constants.aimg1, "aimg1")
                    /* .addFileToUpload(Constants.aimg2, "aimg2")
                     .addFileToUpload(Constants.pimg, "pimg")
                     .addFileToUpload(Constants.limg1, "limg1")
                     .addFileToUpload(Constants.limg2, "limg2")*/
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();

        } catch (Exception e) {
            Log.d("TAG2", "uploadImage: " + e.getLocalizedMessage());
        }


    }
}