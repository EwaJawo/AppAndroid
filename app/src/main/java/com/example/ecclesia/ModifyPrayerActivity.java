package com.example.ecclesia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyPrayerActivity extends Activity implements View.OnClickListener {

    private EditText topicEdiText;
    private Button updatePrayer, deletePrayer;
    private EditText  prayer;

    private long _id;
    private DatabaseManager  DM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setTitle("Modify");
        setContentView (R.layout.activity_modify_prayer);

        DM = new DatabaseManager(this);
        DM.open();

        topicEdiText = findViewById (R.id.titleEdiText);
        prayer = findViewById(R.id.prayerEdit);
        updatePrayer = findViewById(R.id.btnUpdatePrayer);
        deletePrayer = findViewById (R.id.btnDeletePrayer);

        Intent intent = getIntent();
        String i = intent.getStringExtra("id");
        String name = intent.getStringExtra("topic");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong (i);
        topicEdiText.setText(name);
        prayer.setText(desc);
        updatePrayer.setOnClickListener(this);
        deletePrayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdatePrayer:
                String topic =  topicEdiText.getText().toString();
                String desc = prayer.getText().toString();
                DM.update(_id,topic,desc);
                this.returnHome();
                break;

            case R.id.btnDeletePrayer:
                DM.delete(_id);
                break;

        }
    }

    private void returnHome() {
        Intent homeIntent = new Intent(getApplicationContext(),
                PrayerBookActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }
}