package com.example.ecclesia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button logout;
    private Button addEvent;
    private Button addNotes;
    private Button addBook;
    private Button addChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);

        logout = findViewById(R.id.btnLogOut);
        addEvent = findViewById(R.id.btnAddEvent);
        addNotes = findViewById(R.id.btnAddNotes);
        addBook = findViewById(R.id.btnPreparatoryBook);
        addChat = findViewById(R.id.btnMessage);

        addBook.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreparatoryBookActivity.class);
                startActivity(intent);
            }
        } );

        addNotes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AnnouncementsActivity.class);
                startActivity(intent);
            }
        } );

        addEvent.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,EventsActivity.class);
                startActivity(intent);
            }
        } );

        addChat.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        } );

         logout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut ();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } );
    }
}