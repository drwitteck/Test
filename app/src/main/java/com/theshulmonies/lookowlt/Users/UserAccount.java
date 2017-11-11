package com.theshulmonies.lookowlt.Users;

import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

/**
 * Created by phil on 11/10/17.
 */

public class UserAccount implements User {

    // Get all the cool Firebase stuff
    FirebaseUtility firebaseUtility;

    // Typical chill info
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUserName;

    // Keepin' track of dem h4tez0rz
    private int mReliabilityScore;
    private boolean isBlackListed;

    public UserAccount() {}

    public UserAccount(String emailAddress, String firstName, String lastName, String userName) {
        mEmailAddress = emailAddress;
        mFirstName = firstName;
        mLastName = lastName;
        mUserName = userName;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getReliabilityScore() {
        return mReliabilityScore;
    }

    public void setReliabilityScore(int reliabilityScore) {
        mReliabilityScore = reliabilityScore;
    }

    public boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(boolean blackListed) {
        isBlackListed = blackListed;
    }

    // Verifies the user is able to login/out successfully into
    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
