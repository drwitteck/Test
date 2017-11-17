package com.theshulmonies.lookowlt.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

public class RegisterActivity extends AppCompatActivity {

    FirebaseUtility mFirebaseUtility;
    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mRegisterButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameField = (EditText) findViewById(R.id.nameField);
        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText) findViewById(R.id.passwordField);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mFirebaseUtility = new FirebaseUtility(mContext);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButtonClicked(view);
            }
        });
    }

    public void registerButtonClicked(View view) {
        final String name = mNameField.getText().toString().trim();
        final String email = mEmailField.getText().toString().trim();
        final String password = mPasswordField.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            if (mFirebaseUtility.isValidEmail(email) && mFirebaseUtility.isValidPassword(password)) {
                //mFirebaseUtility.writeNewUserToFirebase(name, email, password);
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.d("Create User Result Success:", "Creation was a success through Firebase Authentication");
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = mDatabase.child(user_id);
                            current_user_db.child("Name").setValue(name);
                            current_user_db.child("Image").setValue("default");

                            // Navigate user back to the LoginActivity to enter username/password to login
                            Intent backToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                            backToLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(backToLoginActivity);
                        } else {
                            task.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.v("Create User Result Failed", e.getLocalizedMessage());
                                }
                            });
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please enter a Temple email address", Toast.LENGTH_LONG).show();
            }
        }
    }
}
