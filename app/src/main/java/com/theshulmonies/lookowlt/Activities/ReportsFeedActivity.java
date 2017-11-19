package com.theshulmonies.lookowlt.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Reports.EventsReport;
import com.theshulmonies.lookowlt.Utilities.EventViewHolder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportsFeedActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private String mImagePath;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 10);

        mRecyclerView = findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reports");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() == null) {
                Intent loginIntent = new Intent(ReportsFeedActivity.this, LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
            }
        };
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> onLaunchCamera());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseRecyclerAdapter<EventsReport, EventViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventsReport, EventViewHolder>
                (EventsReport.class, R.layout.card_item, EventViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, EventsReport model, int position) {
                viewHolder.setTitle(model.getReportTitle());
                viewHolder.setDesc(model.getReportDetails());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out) {
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLaunchCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ReportsFeedActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                }, 10);
                return;
            }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                mLocation = location;
            }
        });

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".png";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(mImagePath);
        Uri outputFileUri = Uri.fromFile(file);

        // A camera draft has been announced **Sirens** CALLING UPON ALL CAMERAS FOR SERVICE!!!!!!
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File mImageFile = new File(mImagePath);
            if (mImageFile.exists()) {
                if (mLocation != null) {
                    Intent detailsIntent = new Intent(this, AddEventReportActivity.class);
                    detailsIntent.putExtra("image", mImageFile.getAbsolutePath());
                    detailsIntent.putExtra("lat", Double.toString(mLocation.getLatitude()));
                    detailsIntent.putExtra("lon", Double.toString(mLocation.getLongitude()));
                    showToast(Double.toString(mLocation.getLatitude()) + " " + Double.toString(mLocation.getLongitude()));
                    startActivity(detailsIntent);
                } else {
                    Intent detailsIntent = new Intent(this, AddEventReportActivity.class);
                    detailsIntent.putExtra("image", mImageFile.getAbsolutePath());
                    startActivity(detailsIntent);
                }
            }
        }
    }

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}