package com.theshulmonies.lookowlt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by drwitteck on 11/8/17.
 */

public class ReportsFeedActivity extends AppCompatActivity {
    //ReportsFeedAdapter feedAdapter;
    //FirebaseUtility feedFbUtility;
    ListView listReportsInFeed;
    TextView reportScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onReportItemClicked(){

    }

    public void refreshFeedData(){

    }

    public void loadNextDataSet(){

    }
}
