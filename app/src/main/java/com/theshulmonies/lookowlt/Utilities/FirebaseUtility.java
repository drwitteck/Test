package com.theshulmonies.lookowlt.Utilities;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by phil on 11/9/17.
 */

public class FirebaseUtility extends Utility {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseRef;

    public FirebaseUtility(Context context) {
        super(context);
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void logUserIntoFireBase(String email, String password) {
        mAuth.signInWithEmailAndPassword(formatEmail(email), password).addOnCompleteListener(task -> {
            showToast("Log in was successful");
        });
    }
}
