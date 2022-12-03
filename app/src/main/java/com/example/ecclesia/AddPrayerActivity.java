package com.example.ecclesia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPrayerActivity extends Activity implements View.OnClickListener {

    private Button btnImpart;
    private EditText  titleET;
    private EditText prayerET;
    private DatabaseManager  DM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setTitle( "Add" );
        setContentView ( R.layout.activity_add_prayer );



        prayerET = findViewById(R.id.prayerEdit);
        titleET = findViewById(R.id.titleEdiText);
        btnImpart = findViewById( R.id.btnAddPrayer);

        DM = new DatabaseManager (this);
        DM.open();
        btnImpart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                final String name = titleET.getText().toString();
                final String desc = prayerET.getText().toString ();

                DM.place(name,desc);

                Intent m = new Intent (AddPrayerActivity.this,PrayerBookActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(m);
                break;
        }
    }
}