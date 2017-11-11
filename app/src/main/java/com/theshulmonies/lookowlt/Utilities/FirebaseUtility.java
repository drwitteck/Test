package com.theshulmonies.lookowlt.Utilities;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theshulmonies.lookowlt.Utilities.Utility;

/**
 * Created by phil on 11/9/17.
 */

public class FirebaseUtility extends Utility {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private FirebaseDatabase mFirebaseDatabase;

    public FirebaseUtility(Context context) {
        super(context);
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUserToFirebase(final String name, String email, String password) {
        mDatabaseReference.child("Users");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = mDatabaseReference.child(user_id);
                    current_user_db.child("Name").setValue(name);
                }
            }
        });
    }



}
