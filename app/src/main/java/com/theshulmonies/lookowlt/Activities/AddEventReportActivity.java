package com.theshulmonies.lookowlt.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Reports.EventsReport;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class AddEventReportActivity extends AppCompatActivity  {

    private ImageView mReportImage;
    private TextView mLatitude;
    private TextView mLongitude;
    private EditText mReportTitle;
    private EditText mReportDesc;
    private FirebaseDatabase mDatabase;
    //private DatabaseReference mReportRef;
    private StorageReference storageReference;
    private Bitmap mReportImageBitmap;
    private String encodedImage;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_report);


        mDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        //DatabaseReference mReportRef = mDatabase.getReference().child("Reports");
        mReportImage = findViewById(R.id.report_image);
        mLatitude = findViewById(R.id.lat_text);
        mLongitude = findViewById(R.id.lon_text);
        mReportTitle = findViewById(R.id.report_title_edit);
        mReportDesc = findViewById(R.id.report_desc_edit);
        Button submitReportButton = findViewById(R.id.submit_report_button);

        String filename = getIntent().getStringExtra("image");
        String latitude = getIntent().getStringExtra("lat");
        String longitude = getIntent().getStringExtra("lon");
        File reportImageFile = new File(filename);

        if (reportImageFile.exists()) {
            mReportImageBitmap = BitmapFactory.decodeFile(reportImageFile.getAbsolutePath());
            mReportImage.setImageBitmap(mReportImageBitmap);
        }
        mLatitude.setText(latitude);
        mLongitude.setText(longitude);


        /*
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mReportImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        encodedImage = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        */


        submitReportButton.setOnClickListener(view -> submitReport());
    }

    public void submitReport() {
        final String reportTitle = mReportTitle.getText().toString();
        final String reportDesc = mReportDesc.getText().toString();

        AsyncTask.execute(() -> {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mReportImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteFormat = stream.toByteArray();
            encodedImage = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        });


        if (!TextUtils.isEmpty(reportTitle) && !TextUtils.isEmpty(reportDesc)) {
            Toast.makeText(this, "I made it here at least", Toast.LENGTH_SHORT).show();
            DatabaseReference mReportRef = mDatabase.getReference().child("Reports");
            EventsReport eventsReport = new EventsReport(reportTitle, reportDesc, encodedImage);
            eventsReport.setImage(encodedImage);
            //Log.d("Tag", "Encoded String " + eventsReport.getEncodedImage());

            mReportRef.push().setValue(eventsReport);
            Toast.makeText(this, "Post successfully created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEventReportActivity.this, ReportsFeedActivity.class);
            startActivity(intent);
        }
    }
}
