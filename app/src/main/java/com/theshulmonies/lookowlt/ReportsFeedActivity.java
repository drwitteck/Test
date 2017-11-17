package com.theshulmonies.lookowlt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.theshulmonies.lookowlt.Reports.EventsReport;

public class ReportsFeedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private Button mTempSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reports");

        mTempSignOut = findViewById(R.id.temp_sign_out);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(ReportsFeedActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }
            }
        };

        mTempSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        /*
        tempSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseRecyclerAdapter<EventsReport, EventViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventsReport, EventViewHolder>
                (EventsReport.class, R.layout.card_item, EventViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, EventsReport model, int position) {
                viewHolder.setTitle(model.getReportTitle());
                viewHolder.setDesc(model.getReportDetails());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(View itemView) {
            super(itemView);
            View mView = itemView;
        }

        public void setTitle(String title) {
            TextView event_title = itemView.findViewById(R.id.report_title);
            event_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView event_description = itemView.findViewById(R.id.report_description);
            event_description.setText(desc);
        }

        public void setImage(Context context, String image) {
            ImageView event_image = itemView.findViewById(R.id.report_image);
            Picasso.with(context).load(image).into(event_image);
        }
    }

    public void onReportItemClicked(){

    }

    public void refreshFeedData(){

    }

    public void loadNextDataSet(){

    }
}