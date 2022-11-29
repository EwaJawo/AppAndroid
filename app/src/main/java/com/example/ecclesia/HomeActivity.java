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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        logout = findViewById(R.id.btnLogOut);
        addEvent = findViewById(R.id.btnAddEvent);
        addNotes = findViewById(R.id.btnAddNotes);


        addEvent.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,Calendar_Activity.class);
                startActivity(intent);
            }
        } );

        addNotes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NotebookActivity.class);
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