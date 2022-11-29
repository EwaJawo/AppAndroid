package com.example.ecclesia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> note;

    NoteAdapter(Context context, List<Note> note){
        this.inflater = LayoutInflater.from(context);
        this.note = note;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = inflater.inflate(R.layout.show_note, parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {

        String title = note.get(position).getNoteTitle();
        String date = note.get(position).getNoteDate();
        String time = note.get(position).getNoteTime();

        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return note.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle, nDate, nTime;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

        itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "element kliknięty", Toast.LENGTH_SHORT ).show();
            }
        } );

        }
    }
}
