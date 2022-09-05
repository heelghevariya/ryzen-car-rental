package com.example.ryzencarrent.drawermenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.Constants;
import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.util.UUID;

public class AadhaarVerifyScreen extends AppCompatActivity {
    ImageView back, adharfrontimg, adharbackimg;
    Button frontimg, backimg;
    LinearLayout next;
    TextInputLayout adharnamelay, adharnolay;
    TextInputEditText aadhaarname, aadharnumber;
    TextView txtimg1, txtimg2;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST;
    private Uri filepath1;
    private Uri filepath2;
    private Bitmap bitmap;
    private String T_filepath1;
    private String T_filepath2;
    boolean valid = false;
    boolean valid1 = false;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar_verify_screen);


        adharfrontimg = findViewById(R.id.adar1);
        adharbackimg = findViewById(R.id.adar2);
        frontimg = findViewById(R.id.front_img);
        backimg = findViewById(R.id.back_img);
        aadhaarname = findViewById(R.id.aadharname);
        aadharnumber = findViewById(R.id.aadhaarno);
        txtimg2 = findViewById(R.id.txtimg2);
        txtimg1 = findViewById(R.id.txtimg1);
        back = findViewById(R.id.imgBack);

        next = findViewById(R.id.mLytDetailProceed);
        next.setEnabled(false);
        next.setBackgroundResource(R.drawable.button_shape_disable);

        adharnamelay = findViewById(R.id.adharnamel);
        adharnolay = findViewById(R.id.adharnol);

        aadhaarname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String adharnameInput = adharnamelay.getEditText().getText().toString().trim();

                if (adharnameInput.isEmpty()) {
                    adharnamelay.setError("Field can't be empty");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (adharnameInput.length() > 40) {
                    adharnamelay.setError("Username is too long");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);

                } else if (!adharnameInput.matches("[A-Z ]+")) {
                    adharnamelay.setError("Please Enter Name In Capital Format");
                    valid = false;
                } else {
                    adharnamelay.setError(null);
                    valid = true;
                }
            }
        });
        aadharnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (valid && valid1) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String adharnoInput = adharnolay.getEditText().getText().toString().trim();

                if (adharnoInput.isEmpty()) {
                    adharnolay.setError("Field can't be empty");
                    valid1 = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (adharnoInput.length() > 12) {
                    adharnolay.setError("Aadhaar Number Must be 12 Digit");
                    valid1 = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!adharnoInput.matches("^[2-9]{1}[0-9]{11}$")) {
                    adharnolay.setError("Enter Valid Aadhaar Number");
                    valid1 = false;
                } else {
                    adharnolay.setError(null);
                    valid1 = true;
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });

        frontimg.setOnClickListener(view -> {
            n = 1;
            requestStoragePermission();

        });
        backimg.setOnClickListener(view -> {
            n = 2;

            requestStoragePermission();

        });
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        next.setOnClickListener(view -> {
            if (valid && valid1) {
                if (filepath1 != null && filepath2 != null) {
                    Constants.fname = aadhaarname.getText().toString();
                    Constants.ano = aadharnumber.getText().toString();
                    Constants.aimg1 = T_filepath1;
                    Constants.aimg2 = T_filepath2;
                    Intent intent = new Intent(AadhaarVerifyScreen.this, PanVerifyScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please, Upload Images", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            ShowFileChooser();
        }

//            return;

        else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
                ShowFileChooser();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onBackPressed() {
        finish();
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

}