package com.theshulmonies.lookowlt;

import com.theshulmonies.lookowlt.Users.UserAccount;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by phil on 11/10/17.
 */

public class UserAccountTest {

    UserAccount mUserAccount;

    @Before
    public void setUp() throws Exception {
        mUserAccount = new UserAccount();
    }


    @Test
    public void correctEmailFormatValidatorTest() {
        assertEquals("Email valid test: ", mUserAccount.isValidEmail("phil@temple.edu"), true);
    }

    @After
    public void tearDown() throws Exception {

    }
}
