package com.example.ecclesia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity implements View.OnClickListener {

    private Button btnAddToDo;
    private EditText subEditText;
    private EditText noteEdit;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setTitle ("Add Record");
        setContentView ( R.layout.activity_add_note);

        subEditText = findViewById(R.id.subjectEdiText);
        noteEdit = findViewById(R.id.noteEdit);
        btnAddToDo= findViewById(R.id.btnAdd );

        dbManager = new DBManager (this);
        dbManager.open();
        btnAddToDo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()){
            case R.id.btnAdd:
                final String name = subEditText.getText().toString();
                final String cont = noteEdit.getText().toString();

                dbManager.insert(name,cont);

                Intent main = new Intent(AddNoteActivity.this,
                        NoteApp.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}