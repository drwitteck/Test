package com.theshulmonies.lookowlt.Activities;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theshulmonies.lookowlt.R;

import java.io.IOException;

public class AddEventReportActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private android.hardware.Camera mCamera;
    SurfaceTexture mSurfaceTexture;
    private TextureView mTextureView;
    private Bitmap mReportBitmap;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReportRef;
    private Button mTakePictureButton;
    private int mWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_report);

        mDatabase = FirebaseDatabase.getInstance();
        mReportRef = mDatabase.getReference().child("Reports");
        mTakePictureButton = (Button) findViewById(R.id.take_picture_buttons);
        mTextureView = (TextureView) findViewById(R.id.texture);

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTakePictureButton.getText().toString().equals(getResources().getString(R.string.take_picture))) {
                    stopCamera();
                    mTakePictureButton.setText(getResources().getString(R.string.redo_picture));
                } else if (mTakePictureButton.getText().toString().equals(getResources().getString(R.string.redo_picture))) {
                    startCamera();
                    mTakePictureButton.setText(getResources().getString(R.string.take_picture));
                }
            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        mWidth = displaymetrics.widthPixels;
        mTextureView.setSurfaceTextureListener(this);
    }

    private void startCamera() {
        mCamera = android.hardware.Camera.open();
        android.hardware.Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));
        try {
            mCamera.setPreviewTexture(mSurfaceTexture);
        } catch (IOException t) {
            t.printStackTrace();
        }
        mCamera.startPreview();
        mTextureView.setAlpha(1.0f);
        mTextureView.setRotation(90.0f);
    }


    private void stopCamera() {
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        mCamera = android.hardware.Camera.open();
        mSurfaceTexture = surfaceTexture;

        android.hardware.Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));
        try {
            mCamera.setPreviewTexture(mSurfaceTexture);
        } catch(IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
        mTextureView.setAlpha(1.0f);
        mTextureView.setRotation(90.0f);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        stopCamera();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
