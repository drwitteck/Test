package com.theshulmonies.lookowlt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ReportsFeedActivity extends AppCompatActivity {

    //ReportsFeedAdapter feedAdapter;
    //FirebaseUtility feedFbUtility;
    private RecyclerView mRecyclerView;

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
