package com.example.ecclesia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainAnnouncements extends AppCompatActivity {

    private RecyclerView mAnnouncementsList;
    private GridLayoutManager gridLayoutManager;
    private DatabaseReference fAnnouncementsDatabase ;
    private FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState );
        setContentView (R.layout.activity_main_announcements);

        mAnnouncementsList = findViewById (R.id. announcementList);

        gridLayoutManager = new GridLayoutManager(  this,3, GridLayoutManager.VERTICAL,false);
        mAnnouncementsList.setHasFixedSize(true);
        mAnnouncementsList.setLayoutManager(gridLayoutManager);

        fireAuth = FirebaseAuth.getInstance();
        if(fireAuth.getCurrentUser() !=null){
            fAnnouncementsDatabase = FirebaseDatabase.getInstance().getReference().child("Announcements").child (fireAuth.getCurrentUser().getUid());
        }
        updateUI();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<AnnouncementsModel,AnnounceViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AnnouncementsModel,AnnounceViewHolder>(
                AnnouncementsModel.class,
                 R.layout.single_announce_layout,
                 AnnounceViewHolder.class,
                fAnnouncementsDatabase
        ) {
            @Override
            protected void populateViewHolder(AnnounceViewHolder viewHolder, AnnouncementsModel model, int position){
             String AnnounceId = getRef(position).getKey();
             fAnnouncementsDatabase.child(AnnounceId).addValueEventListener ( new ValueEventListener () {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     String topic = dataSnapshot.child("topic").getValue().toString();
                     String timestamp = dataSnapshot.child ("timestamp").getValue().toString();

                     viewHolder.setAnnouncementsTopic(topic);
                     viewHolder.setAnnouncementsTime(timestamp);
                 }

                 @Override
                 public void onCancelled(DatabaseError error) {
                 }
             } );
            }
        };
        mAnnouncementsList.setAdapter(firebaseRecyclerAdapter);
    }

    private void updateUI() {
        if (fireAuth.getCurrentUser () !=null){
            Log.i ("MainAnnouncements", "fireAuth !=null");
        }else{
            Intent intent = new Intent(MainAnnouncements.this,Registration.class);
            startActivity ( intent );
            finish ();
        }
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