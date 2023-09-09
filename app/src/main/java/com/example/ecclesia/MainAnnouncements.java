package com.example.ecclesia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainAnnouncements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState );
        setContentView (R.layout.activity_main_announcements);
        //mAnnouncementsList = findViewById (R.id. announcementList);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.btnAddRecord:
                Intent newIntent = new Intent(MainAnnouncements.this,Announcements.class);
                startActivity(newIntent);
                break;
        }
            return true;
    }




}