package com.example.ecclesia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder>
{
    Context context;
    ArrayList<Events>  arrayList;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        Events events = arrayList.get(position);
        holder.Event.setText(events.getEVENT());
        holder.Date.setText(events.getDATE ());
        holder.Time.setText(events.getTIME());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Date,Event,Time;

        public MyViewHolder(@NonNull View itemView)
        {
            super ( itemView );
                Date = itemView.findViewById(R.id.eventDate);
                Event = itemView.findViewById(R.id.eventName);
                Time = itemView.findViewById(R.id.Time);

        }
    }
}
