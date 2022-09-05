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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.Constants;
import com.example.ryzencarrent.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

public class PanVerifyScreen extends AppCompatActivity {
    ImageView back, adharfrontimg;
    LinearLayout next;
    TextInputEditText pannumber;
    TextInputLayout panlay;
    Button imgupload;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST;
    private Uri filepath1;
    boolean valid = false;
    private String T_filepath1;
    private Bitmap bitmap;
    TextView txtimg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_verify_screen);
        imgupload = findViewById(R.id.img_uploda);
        pannumber = findViewById(R.id.panno);
        next = findViewById(R.id.mLytDetailProceed);
        txtimg1 = findViewById(R.id.txtimg1);
        back = findViewById(R.id.imgBack);
        next.setEnabled(false);
        next.setBackgroundResource(R.drawable.button_shape_disable);
        panlay = findViewById(R.id.pannol);
        adharfrontimg = findViewById(R.id.adar1);
        back.setOnClickListener(view -> {
            onBackPressed();
        });
        imgupload.setOnClickListener(view -> {

            ShowFileChooser();

        });

        pannumber.addTextChangedListener(new TextWatcher() {
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
                String PannoInput = panlay.getEditText().getText().toString().trim();

                if (PannoInput.isEmpty()) {
                    panlay.setError("Field can't be empty");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (PannoInput.length() > 10) {
                    panlay.setError("Pan Number Must be 10 Digit Long");
                    valid = false;
                    next.setBackgroundResource(R.drawable.button_shape_disable);
                } else if (!PannoInput.matches("[A-Z0-9]+")) {
                    panlay.setError("Enter Valid Pan Number");
                    valid = false;
                } else {
                    panlay.setError(null);
                    valid = true;
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.shape_plp_book);
                }
            }
        });


        next.setOnClickListener(view -> {

            if (!pannumber.getText().toString().equals("") && filepath1 != null) {
                Constants.pno = pannumber.getText().toString();
                Constants.pimg = T_filepath1;
                Intent intent = new Intent(PanVerifyScreen.this, DrivingLicenseDetailScreen.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Upload Image", Toast.LENGTH_SHORT).show();
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