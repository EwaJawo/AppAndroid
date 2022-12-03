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

public class PrayerBookActivity extends AppCompatActivity {

    private DatabaseManager dbM;
    private ListView lView;
    private SimpleCursorAdapter simpleAdapter;

    final String[] from = new String[] {DBHelper._ID,
            DBHelper.TITLE, DBHelper.PRA};

    final int[] to = new int[]{R.id.idView, R.id.titleView, R.id.contView};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.prayer_book_fragment_list );

        dbM = new DatabaseManager(this);
        dbM.open();
        Cursor cursor = dbM.fetch();

        lView = findViewById(R.id.listViewPrayer);
        lView.setEmptyView(findViewById(R.id.blank));

        simpleAdapter = new SimpleCursorAdapter(this, R.layout.activity_view,
                cursor, from, to,0);
        simpleAdapter.notifyDataSetChanged();

        lView.setAdapter(simpleAdapter);

        lView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idVTextView = view.findViewById ( R.id.idView);
                TextView titleVTextView = view.findViewById ( R.id.titleView );
                TextView contVTextView = view.findViewById ( R.id.contView );

                String idView = idVTextView.getText ().toString();
                String titleView = titleVTextView.getText().toString();
                String contView = contVTextView.getText().toString();

                Intent modifiy = new Intent(getApplicationContext(), ModifyPrayerActivity.class);

                modifiy.putExtra("titleView", titleView);
                modifiy.putExtra("contView", contView);
                modifiy.putExtra("idView", idView);


                startActivity(modifiy);
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprayer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record);

        Intent addMenu = new Intent (this, AddPrayerActivity.class);
        startActivity(addMenu);



        return super.onOptionsItemSelected ( item );
    }
}