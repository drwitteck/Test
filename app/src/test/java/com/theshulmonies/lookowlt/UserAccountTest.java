package com.theshulmonies.lookowlt;

import android.content.Context;

import com.theshulmonies.lookowlt.Users.UserAccount;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;
import com.theshulmonies.lookowlt.Utilities.Utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by phil on 11/10/17.
 */

public class UserAccountTest {

    UserAccount mUserAccount;
    Utility utility;
    FirebaseUtility firebaseUtility;
    Context context;

    @Before
    public void setUp() throws Exception {
        mUserAccount = new UserAccount();
        utility = new Utility(context);
        //firebaseUtility = new FirebaseUtility(context);
    }

    @Test
    public void correctEmailFormatValidatorTest() {
        mUserAccount.setEmailAddress("phil@temple.edu");
        assertTrue(utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void incorrectEmailFormatValidatorTest() {
        mUserAccount.setEmailAddress("phil@google.com");
        assertFalse(utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void testForEduEmail() {
        mUserAccount.setEmailAddress("phil@temple.com");
        assertFalse(utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void testForValidPassword() {
        mUserAccount.setPassword("Password1!");
        assertTrue(utility.isValidPassword(mUserAccount.getPassword()));
    }

    @Test
    public void testForInvalidPassword() {
        mUserAccount.setPassword("Fail!");
        assertFalse(utility.isValidPassword(mUserAccount.getPassword()));
    }

    @Test
    public void testForEmptyPassword() {
        // Test that the method will return null if no password is given
        mUserAccount.setPassword("");
        assertFalse(utility.isValidPassword(mUserAccount.getPassword()));
    }

    @Test
    public void testForEmptyEmail() {
        mUserAccount.setEmailAddress("");
        assertFalse(utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
