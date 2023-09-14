package com.example.ecclesia;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AnnounceViewHolder extends RecyclerView.ViewHolder {
    View mView;
    TextView textTopic, textTime;

    public AnnounceViewHolder(View itemView) {
        super ( itemView );
        mView = itemView;
        textTopic = mView.findViewById (R.id.announceTitle);
        textTime = mView.findViewById (R.id.announceTime);
    }
    public void setAnnouncementsTopic(String topic ){
        textTopic.setText(topic);
    }
    public void setAnnouncementsTime(String time){
        textTime.setText(time);
    }
}
