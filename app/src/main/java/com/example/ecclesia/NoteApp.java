package com.example.ecclesia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NoteApp extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;


    final String[] from = new String[] {DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.CONTENTS};

    final int[] to = new int[]{R.id.id, R.id.title, R.id.cont};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState );
        setContentView (R.layout.fragment_list );

        dbManager = new DBManager (this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById ( R.id.listViewNote );
        listView.setEmptyView ( findViewById ( R.id.empty));

        adapter = new SimpleCursorAdapter ( this, R.layout.activity_view_record,
                cursor, from, to, 0 );

        adapter.notifyDataSetChanged();

         listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewid) {
                TextView idTextView = view.findViewById ( R.id.id);
                TextView titleTextView = view.findViewById ( R.id.title );
                TextView contTextView = view.findViewById ( R.id.cont );

                String id = idTextView.getText ().toString ();
                String title = titleTextView.getText ().toString ();
                String cont = contTextView.getText ().toString ();

                Intent modify_intent = new Intent (getApplicationContext (),
                            ModifyNoteActivity.class );

                modify_intent.putExtra ( "title", title );
                modify_intent.putExtra ( "cont", cont );
                modify_intent.putExtra ( "id", id );

                startActivity (modify_intent );
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addRecord){

            Intent add_menu = new Intent (this, AddNoteActivity.class);
            startActivity(add_menu);
        }

        return super.onOptionsItemSelected( item );
    }
}