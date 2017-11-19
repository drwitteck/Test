package com.theshulmonies.lookowlt.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUtility mFirebaseUtility;
    private FirebaseAuth.AuthStateListener mFirebaseListener;
    private FirebaseAuth mAuth;
    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUtility = new FirebaseUtility(mContext);
        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        Button mLoginButton = findViewById(R.id.login_button);
        Button mCreateAccountButton = findViewById(R.id.create_account_button);

        mLoginButton.setOnClickListener(view -> {
            String email = mLoginEmail.getText().toString().trim();
            String password = mLoginPassword.getText().toString().trim();

            // Garbage in, garbage out....just checking the input before pressing onward
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                if (mFirebaseUtility.isValidEmail(email) && mFirebaseUtility.isValidPassword(password)) {
                    mFirebaseUtility.logUserIntoFireBase(email, password);
                } else {
                    // Gotta let them know they messed up, cha feels?
                    Toast.makeText(LoginActivity.this, "Please enter a valid Temple email address", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Will navigate user to the RegisterActivity so they can create accounts and stuff SKRRR.R.R.R~POPOPOPOPOPOP
        mCreateAccountButton.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        mFirebaseListener = mAuth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Intent loginIntent = new Intent(LoginActivity.this, ReportsFeedActivity.class);
                startActivity(loginIntent);
            }
        };
    }

    @Override
    protected void onPause(){
        super.onPause();
        mAuth.removeAuthStateListener(mFirebaseListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mFirebaseListener);
    }
}
