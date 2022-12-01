package com.example.ecclesia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button update, delete;
    private EditText noteText;

    private  long _id;
    private  DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setTitle("Modify Record");
        setContentView (R.layout.activity_modify_note);


        dbManager = new DBManager(this);
        dbManager.open();


        titleText = findViewById(R.id.subjectEdiText);
        noteText = findViewById(R.id.noteEdit);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDeleteNote);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id" );
        String name = intent.getStringExtra ("title");
        String cont = intent.getStringExtra("cont");

        _id = Long.parseLong(id);

        titleText.setText(name);
        noteText.setText(cont);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()){
            case R.id.btnUpdate:
                String title = titleText.getText().toString();
                String cont = noteText.getText().toString();
                dbManager.update(_id,title, cont);
                this.returnHome();
                break;

            case  R.id.btnDeleteNote:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(),
                NoteApp.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(home_intent);

    }
}