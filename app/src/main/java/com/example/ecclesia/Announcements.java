package com.example.ecclesia;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Announcements extends AppCompatActivity {
    private Button btnCreate;
    private EditText editTitle, editAnnouncements;
    private FirebaseAuth fireAuth;
    private DatabaseReference fAnnouncementsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_announcements );

        btnCreate = findViewById ( R.id. btnNewAnnouncement);
        editTitle = findViewById (R.id.newAnnouncement);
        editAnnouncements = findViewById (R.id.editContent);

        fireAuth = FirebaseAuth.getInstance();
        fAnnouncementsDatabase = FirebaseDatabase.getInstance().getReference().child("Announcements").child (fireAuth.getCurrentUser ().getUid());

        btnCreate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editAnnouncements.getText().toString().trim ();

                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content))
                {
                    createAnnouncements(title,content);
                } else {
                    Snackbar.make(v,"Błąd! Brak tytułu i treści ogłoszenia",Snackbar.LENGTH_SHORT).show();
                }
            }
        } );
    }
    private void createAnnouncements(String title, String content) {

        if (fireAuth.getCurrentUser() !=null)
        {
            final DatabaseReference newAnnounce = fAnnouncementsDatabase.push();

           final Map AnnounceMap = new HashMap();
            AnnounceMap.put("topic", editTitle.getText().toString().trim());
            AnnounceMap.put("content",editAnnouncements.getText().toString().trim());
            AnnounceMap.put("timestamp", ServerValue.TIMESTAMP);

             Thread mainThread = new Thread ( new Runnable () {
                @Override
                public void run() {
                    newAnnounce.setValue(AnnounceMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(Task<Void> task) {

                            if(task.isSuccessful ()) {
                                Toast.makeText(Announcements.this,"Dodano ogłoszenie", Toast.LENGTH_SHORT).show ();
                            }else{
                                Toast.makeText(Announcements.this,"Błąd:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show ();
                            }
                        }
                    });
                }
            } );
            mainThread.start();
        } else {
            Toast.makeText ( this, "Niezapisany użytkownik", Toast.LENGTH_SHORT ).show ();
        }
    }
}