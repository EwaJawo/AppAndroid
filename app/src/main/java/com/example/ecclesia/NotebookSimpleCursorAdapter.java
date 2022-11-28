package com.example.ecclesia;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;


public class NotebookSimpleCursorAdapter extends SimpleCursorAdapter {
    public NotebookSimpleCursorAdapter(Context context, int layout, Cursor c, String[]
                                       from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return  super. newView(context, cursor, parent);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.listTab = view.findViewById ( R.id.rowTab );
            view.setTag(holder );
        }
            else {
            holder.listTab.setBackgroundColor(context.getResources().getColor (R.color.navy));
        }
        }
        static class ViewHolder {
        View listTab;
    }
    }

