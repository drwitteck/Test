package com.theshulmonies.lookowlt.Utilities;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phil on 11/9/17.
 */

public class Utility {

    private Context mContext;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;

    public Utility(Context context) {
        mContext = context;
    }

    public boolean isValidEmail(String emailAddress) {
        if (emailAddress == null)
            return false;
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

    public String formatEmail(String email) {
        String lowerEmail = email.toLowerCase();
        String formattedEmail = "";

        for (int i = 0; i < lowerEmail.length(); i++) {
            if (lowerEmail.charAt(i) == ' ') {
            } else {
                formattedEmail = formattedEmail + lowerEmail.charAt(i);
            }
        }
        return formattedEmail;
    }

    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
