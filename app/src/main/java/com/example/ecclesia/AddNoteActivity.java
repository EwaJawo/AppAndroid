package com.example.ecclesia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    EditText title, content;
    Button btnAddNote;
    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_note );

        getSupportActionBar().setTitle("Dodano Notatkę");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.addNote);
        content = findViewById(R.id.noteContent);
        btnAddNote = findViewById(R.id.btnAddNote);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+":"+pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time" + todayDate+" and "+currentTime);

        btnAddNote.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Note note = new Note (title.getText().toString (),content.getText().toString(),todayDate,currentTime);
                NoteDatabase db = new NoteDatabase(AddNoteActivity.this);
                db.AddNote(note);

                Intent intent = new Intent(AddNoteActivity.this, NotebookActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Zapisano",Toast.LENGTH_LONG).show();
            }
        } );

    }
    public String pad(int i){
        if(i < 0)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item );
    }
}