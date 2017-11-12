package com.theshulmonies.lookowlt.Utilities;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phil on 11/9/17.
 */

public class Utility {

    Context mContext;

    public Utility(Context context) {
        mContext = context;
    }

    public boolean isValidEmail(String emailAddress) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@*@temple\\.edu";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    /*
     * Between 6 and 21 characters
     * A password must contain at least 1 digit and 1 special character
     */
    public boolean isValidPassword(String password) {
        boolean special = false, digit = false, upperCase = false, lowerCase = false;

        if (password != null && password.length() > 5 && password.length() < 21) {
            for (int index = 0; index < password.length(); index++) {
                final Character character = password.charAt(index);
                if (Character.isWhitespace(character))
                    return false;
                else if (Character.isLowerCase(character))
                    lowerCase = true;
                else if (Character.isUpperCase(character))
                    upperCase = true;
                else if (Character.isDigit(character))
                    digit = true;
                else if (33 <= character.charValue() && 127 >= character.charValue())
                    special = true;
            }
        }
        return special && digit && upperCase && lowerCase;
    }
}
