package com.theshulmonies.lookowlt.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

public class LoginActivity extends AppCompatActivity {

    FirebaseUtility mFirebaseUtility;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private Button mLoginButton;
    private Button mCreateAccountButton;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Stuff and Stranger Things (YO BEST SHOW EVER BTW)
        mFirebaseUtility = new FirebaseUtility(mContext);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mLoginEmail = (EditText) findViewById(R.id.login_email);
        mLoginPassword = (EditText) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mCreateAccountButton = (Button) findViewById(R.id.create_account_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the data from the EditText views
                String email = mLoginEmail.getText().toString().trim();
                String password = mLoginPassword.getText().toString().trim();

                // Garbage in, garbage out....just checking the input before pressing onward
                // Would love to abstract this entire logic to FirebaseUtility
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if (mFirebaseUtility.isValidEmail(email) && mFirebaseUtility.isValidPassword(password)) {
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    checkUserExists();
                                }
                            }
                        });
                    } else {
                        // Gotta let them know they messed up, cha feels?
                        Toast.makeText(LoginActivity.this, "Please enter a valid Temple email address", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Will navigate user to the RegisterActivity so they can create accounts and stuff SKRRR.R.R.R~POPOPOPOPOPOP
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    // Would like to abstract this out to FirebaseUtility
    public void checkUserExists() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)) {
                    Intent loginIntent = new Intent(LoginActivity.this, ReportsFeedActivity.class);
                    startActivity(loginIntent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
