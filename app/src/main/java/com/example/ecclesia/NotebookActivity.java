package com.example.ecclesia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotebookActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    List<Note> noteList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notebook );


        recyclerView = findViewById(R.id.addRecyclerView);

        NoteDatabase noteDatabase = new NoteDatabase(this);
        noteList = noteDatabase.getNote();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(this,noteList);
        recyclerView.setAdapter(noteAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if (item.getItemId() == R.id.addNote);
        Intent i = new Intent (NotebookActivity.this, AddNoteActivity.class);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }

}