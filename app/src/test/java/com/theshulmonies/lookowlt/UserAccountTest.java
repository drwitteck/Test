package com.theshulmonies.lookowlt;

import android.content.Context;

import com.theshulmonies.lookowlt.Users.UserAccount;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;
import com.theshulmonies.lookowlt.Utilities.Utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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
        assertEquals("Valid Temple email: ", true,
                utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void incorrectEmailFormatValidatorTest() {
        mUserAccount.setEmailAddress("phil@google.com");
        assertEquals("Invalid Temple email: ", false,
                utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void testForEduEmail() {
        mUserAccount.setEmailAddress("phil@temple.com");
        assertEquals("Email valid test: ", false,
                utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void testForValidPassword() {
        mUserAccount.setPassword("Password1!");
        assertEquals("Valid Password Test: ", true,
                utility.isValidPassword(mUserAccount.getPassword()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
