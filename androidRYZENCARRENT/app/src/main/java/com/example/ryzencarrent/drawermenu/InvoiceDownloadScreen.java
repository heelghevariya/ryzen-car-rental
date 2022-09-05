package com.example.ryzencarrent.drawermenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.adapters.AppPreferences;
import com.vipul.hp_hp.library.Layout_to_Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InvoiceDownloadScreen extends AppCompatActivity {

    TextView customername, customeremail, customeradd, customermobno, companyadd, companymail, companycontactno,
            carmodalname, carfeesperday,c_country,c_stat,r_mobno,
            driverfeesperday, fromdate, todate, bookingstatus, paymentmethod, grandtotal, BookingNo, BookingDate;
    AppPreferences appPreferences;
    int UID;
TableRow driverfeerow;
    Layout_to_Image layout_to_image;
    Bitmap bitmap;
    CoordinatorLayout layoutProductDetail;
    int pageHeight = 1120;
    int pagewidth = 792;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_download_screen);

        appPreferences = new AppPreferences(getApplicationContext());
        UID = appPreferences.getuidpref();

        customername = findViewById(R.id.c_name);
        layoutProductDetail = findViewById(R.id.layoutProductDetail);
        customeremail = findViewById(R.id.c_email);
        customeradd   = findViewById(R.id.c_add);
        customermobno = findViewById(R.id.c_mobno);
        c_country = findViewById(R.id.c_country);
        c_stat = findViewById(R.id.c_stat);
        companyadd = findViewById(R.id.r_add);
        companymail = findViewById(R.id.r_mail);
        r_mobno = findViewById(R.id.r_mobno);
        carmodalname = findViewById(R.id.modal);
        carfeesperday = findViewById(R.id.carfees);
        driverfeesperday = findViewById(R.id.drivefees);
        fromdate = findViewById(R.id.from);
        todate = findViewById(R.id.to);
        bookingstatus = findViewById(R.id.status);
        paymentmethod = findViewById(R.id.payment);
        grandtotal = findViewById(R.id.totalpay);
        driverfeerow = findViewById(R.id.driverfeerow);
        BookingNo = findViewById(R.id.txtbookingno);
        BookingDate = findViewById(R.id.txtbookingdate);



        Bundle bundle = getIntent().getExtras();
        String getVehicle_title = bundle.getString("getVehicle_title");
        String getBrand_name = bundle.getString("getBrand_name");
        String getFuel_type = bundle.getString("getFuel_type");
        int getModel_year = bundle.getInt("getModel_year", 0);
        int getSeating_capacity = bundle.getInt("getSeating_capacity");
        int getPriceperday = bundle.getInt("getPriceperday");
        String getVimage1 = bundle.getString("getVimage1");
        int getBooking_no = bundle.getInt("getBooking_no");
        String getBooking_date = bundle.getString("getBooking_date");
        String getFrom_date = bundle.getString("getFrom_date");
        String getTo_date = bundle.getString("getTo_date");
        int getTotalnodays = bundle.getInt("getTotalnodays");
        int getDriver_fees = bundle.getInt("getDriver_fees");
        int getAmount_paid = bundle.getInt("getAmount_paid");
        int getP_status = bundle.getInt("getP_status");
        int getB_status = bundle.getInt("getB_status");
        String getMobile_no = bundle.getString("getMobile_no");
        String getAddress = bundle.getString("getAddress");
        String getState = bundle.getString("getState");
        String getCountry = bundle.getString("getCountry");
        String getCadd = bundle.getString("getCadd");
        String getCno = bundle.getString("getCno");
        String getCmail = bundle.getString("getCmail");



        fromdate.setText(getFrom_date);
        todate.setText(getTo_date);
        carfeesperday.setText("₹" + getPriceperday);
        carmodalname.setText(getVehicle_title);
        grandtotal.setText("₹" + getAmount_paid);
        BookingNo.setText("#" + getBooking_no);
        BookingDate.setText(getBooking_date.split(" ")[0]);

        if (getDriver_fees != 0) {
            driverfeesperday.setText("₹" + getDriver_fees);
        } else {
            driverfeerow.setVisibility(View.GONE);
        }
        grandtotal.setText("₹" + getAmount_paid);


        if (getP_status == 0) {
            paymentmethod.setText("Pay at Branch");

        } else if (getP_status == 1) {
            paymentmethod.setText("UPI");

        }
        if (getB_status == 0) {
            bookingstatus.setText("Pending");

        } else if (getB_status == 1) {
            bookingstatus.setText("Confirmed");

        } else if (getB_status == 2) {
            bookingstatus.setText("Cancelled");


        }

        companyadd.setText(getCadd);
        r_mobno.setText(getCno);
        companymail.setText(getCmail);
        customername.setText(appPreferences.getunamepref());
         customeremail.setText(appPreferences.getemailpref());
         customeradd.setText(getAddress);
         customermobno.setText(getMobile_no);
        c_country.setText(getCountry);
        c_stat.setText(getState);
        if (isWriteStoragePermissionGranted()) {
            downloadPdfFile();

        }


    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted2");
                return true;
            } else {

                Log.v("TAG","Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted2");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Log.d("TAG", "External storage2");
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
                    //resume tasks needing this permission
                    downloadPdfFile();
                }else{
//                    progress.dismiss();
                }
                break;

            /*case 3:
                Log.d(TAG, "External storage1");
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    //resume tasks needing this permission
                    SharePdfFile();
                }else{
                    progress.dismiss();
                }
                break;*/
        }
    }

    private void downloadPdfFile() {
        new SaveTask(InvoiceDownloadScreen.this, InvoiceDownloadScreen.this.getResources().getString(R.string.app_name), System.currentTimeMillis() + ".jpg", null).execute(new Void[0]);
    }
    private class SaveTask extends AsyncTask<Void, Void, Integer> {
        private Bitmap finalbm;
        private final String mFileName;
        private final String mFolderName;
        private final Handler mHandler;

        class C03791 implements MediaScannerConnection.OnScanCompletedListener {

            class C03781 implements Runnable {
                C03781() {
                }

                @SuppressLint("WrongConstant")
                public void run() {
//                    Toast.makeText(InvoiceDownloadScreen.this.getBaseContext(), "Image saved successfully!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(InvoiceDownloadScreen.this, "Invoice Downloaded.", Toast.LENGTH_SHORT).show();

                }
            }

            C03791() {
            }

            public void onScanCompleted(String path, Uri uri) {
                SaveTask.this.mHandler.post(new C03781());
            }
        }

        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
        }

        public SaveTask(InvoiceDownloadScreen fullScreenActivity, String folderName, String fileName, Bitmap bitmap) {
            this(folderName, fileName, 0, 0, bitmap);
        }

        public SaveTask(String folderName, String fileName, int width, int height, Bitmap bitmap) {
            this.mFolderName = folderName;
            this.mFileName = fileName;
            this.mHandler = new Handler();
        }

        protected Integer doInBackground(Void... params) {
            this.finalbm = InvoiceDownloadScreen.this.getImage();
            saveImage(this.mFolderName, this.mFileName, this.finalbm);
            return Integer.valueOf(0);
        }

        private void saveImage(String folderName, String fileName, Bitmap image) {
            Paint paint = new Paint();
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName + "/" + fileName);
            try {
                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder( image.getWidth(), image.getHeight(), 1).create();

//                file.getParentFile().mkdirs();
//                image.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
//                MediaScannerConnection.scanFile(InvoiceDownloadScreen.this, new String[]{file.toString()}, null, new C03791());
             PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
                Canvas canvas = myPage.getCanvas();
                canvas.drawBitmap(image, 1, 1, paint);
                pdfDocument.finishPage(myPage);
                File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), folderName);
                if (!file1.exists()) {
                    file1.mkdirs();

                }
                String str=System.currentTimeMillis()+".pdf";
                File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), folderName + "/" + str);

                try {
                    // after creating a file name we will
                    // write our PDF file to that location.
                    pdfDocument.writeTo(new FileOutputStream(file2));

                    // below line is to print toast message
                    // on completion of PDF generation.
                } catch (IOException e) {
                    // below line is used
                    // to handle error
                    e.printStackTrace();
//                    Toast.makeText(InvoiceDownloadScreen.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                // after storing our pdf to that
                // location we are closing our PDF file.
                pdfDocument.close();
                                MediaScannerConnection.scanFile(InvoiceDownloadScreen.this, new String[]{file2.toString()}, null, new C03791());

            } /*catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }*/catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private Bitmap getImage() {
        Bitmap bitmap1 = null;

        layout_to_image=new Layout_to_Image(InvoiceDownloadScreen.this,layoutProductDetail);

        bitmap1=layout_to_image.convert_layout();
        return bitmap1;
    }
}
