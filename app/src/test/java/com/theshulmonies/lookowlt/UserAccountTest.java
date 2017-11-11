package com.theshulmonies.lookowlt;

import com.theshulmonies.lookowlt.Users.UserAccount;
import com.theshulmonies.lookowlt.Utilities.Utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by phil on 11/10/17.
 */

public class UserAccountTest {

    UserAccount mUserAccount;
    Utility utility;

    @Before
    public void setUp() throws Exception {
        mUserAccount = new UserAccount();
        utility = new Utility();
    }

    @Test
    public void correctEmailFormatValidatorTest() {
        mUserAccount.setEmailAddress("phil@temple.edu");
        assertEquals("Valid Temple email: ", true, utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void incorrectEmailFormatValidatorTest() {
        mUserAccount.setEmailAddress("phil@google.com");
        assertEquals("Invalid Temple email: ", false, utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @Test
    public void testForEduEmail() {
        mUserAccount.setEmailAddress("phil@temple.com");
        assertEquals("Email valid test: ", false, utility.isValidEmail(mUserAccount.getEmailAddress()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
