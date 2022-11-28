package com.example.ecclesia;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class NotebookActivity extends AppCompatActivity {

    private ListView mListView;
    private NotebookDbAdapter mDbAdapter;
    private NotebookSimpleCursorAdapter mCursorAdapter;

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState );
        setContentView (R.layout.activity_notebook);
        mListView = (ListView) findViewById ( R.id.noteListView );
        mListView.setDivider ( null );
        mDbAdapter = new NotebookDbAdapter ( this );
        mDbAdapter.open ();
        if (savedInstanceState == null) {
            mDbAdapter.deleteAllNote();
        }

        Cursor cursor = mDbAdapter.fetchAllNotes ();
        String[] from = new String[]{
                NotebookDbAdapter.COL_CONTENT
        };
        int[] to = new int[]{
                R.id.rowText
        };
        mCursorAdapter = new NotebookSimpleCursorAdapter (
                NotebookActivity.this,
                R.layout.notes_row,
                cursor,
                from,
                to,
                0 );
        mListView.setAdapter (mCursorAdapter);

        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NotebookActivity.this);
                ListView modeListView = new ListView(NotebookActivity.this);
                String[] modes = new  String[]{"Edycja notatki", "Usunięcie notatki"};
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<> (NotebookActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);
                final Dialog dialog = builder.create();
                dialog.show();
                modeListView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0){
                            int nId = getIdFromPosition(masterListPosition);
                            Note note = mDbAdapter.fetchNoteById(nId);
                            fireCustomDialog(note);
                        } else{
                            mDbAdapter.deleteNoteById(getIdFromPosition(masterListPosition));
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllNotes());
                        }
                            dialog.dismiss();
                    }
                } );
            }
        } );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.cam_menu, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item_delete_note:
                            for (int nC = mCursorAdapter.getCount() -1; nC >=0; nC--){ 
                                if (mListView.isItemChecked(nC)) { 
                                    mDbAdapter.deleteNoteById(getIdFromPosition(nC));
                                }
                            } 
                            mode.finish();  
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllNotes()); 
                            return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            } );
        }
    }

    private int getIdFromPosition(int nC) {
        return (int)mCursorAdapter.getItemId(nC);
    }

    private void fireCustomDialog(final Note note){
        final  Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);

        TextView titleView = (TextView) dialog.findViewById(R.id.customTitle);
        final EditText editCustom = (EditText) dialog.findViewById(R.id.customEditNote);
        Button commitButton = (Button) dialog.findViewById(R.id.btnConfirm);
        LinearLayout rootLayout = (LinearLayout) dialog.findViewById(R.id.customRootLayout);
        final boolean isEditOperation = (note !=null);

        if (isEditOperation) {
            titleView.setText("Edycja notatki");
            editCustom.setText(note.getContent());
            rootLayout.setBackgroundColor(getResources().getColor(R.color.blue_02));
        }
        commitButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editCustom.getText().toString();
                if (isEditOperation){
                    Note noteEdited = new Note(note.getId(), noteText);
                    mDbAdapter.updateNote(noteEdited);
                } else{
                    mDbAdapter.createNote(noteText);
                }
                mCursorAdapter.changeCursor(mDbAdapter.fetchAllNotes());
                dialog.dismiss();
            }
        } );
     Button buttonCancel = (Button) dialog.findViewById(R.id.btnCancel);
     buttonCancel.setOnClickListener( new View.OnClickListener () {
         @Override
         public void onClick(View v) {
             dialog.dismiss();
         }
     });
     dialog.show();
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notebook, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.actionNew:
                    fireCustomDialog(null);
                    return true;
            case R.id.actionExit:
                    finish ();
                    return true;
                default:
                    return false;
            }
        }
    }





