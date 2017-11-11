package com.theshulmonies.lookowlt.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phil on 11/9/17.
 */

public class Utility {



    public boolean isValidEmail(String emailAddress) {
        final String TEMPLE_DOMAIN = "@temple.edu";
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@*@temple\\.edu";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();

    }
}
