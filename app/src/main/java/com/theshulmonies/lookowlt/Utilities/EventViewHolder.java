package com.theshulmonies.lookowlt.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.theshulmonies.lookowlt.R;

/**
 * Created by phil on 11/19/17.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
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
        //ImageView event_image = itemView.findViewById(R.id.report_image);
        //Picasso.with(context).load(image).into(event_image);
    }
}
