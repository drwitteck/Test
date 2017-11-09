package com.theshulmonies.lookowlt;

/**
 * Created by davidseverns on 11/8/17.
 */

public interface Reportable {
    //a reportable object has the ability to be "up voted"
    public void userUpVote();

    //a reportable object has the ability to be "down voted"
    public void usDownVote();
}
