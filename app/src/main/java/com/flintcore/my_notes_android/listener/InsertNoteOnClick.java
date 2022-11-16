package com.flintcore.my_notes_android.listener;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flintcore.my_notes_android.database.DatabaseManager;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;

public class InsertNoteOnClick implements View.OnClickListener {

    private final int note_id;
    private final AppCompatActivity activity;
    private final TextView subj_txt;
    private final TextView desc_txt;

    public InsertNoteOnClick(int note_id, AppCompatActivity activity, TextView subj_txt, TextView desc_txt) {
        this.note_id = note_id;
        this.activity = activity;
        this.subj_txt = subj_txt;
        this.desc_txt = desc_txt;
    }

    @Override
    public void onClick(View view) {
        Note noteToAdd = new Note();
        noteToAdd.setId(note_id);
        noteToAdd.setSubject(this.subj_txt.getText().toString());
        noteToAdd.setDescription(this.desc_txt.getText().toString());

        saveNote(noteToAdd);
        this.activity.finish();
    }

    private void saveNote(Note noteToAdd) {
        switch (this.note_id) {
            case -1:
                DatabaseManager.getInstance()
                        .get(Tables.NOTES)
                        .create(noteToAdd);
                break;

            default:
                DatabaseManager.getInstance()
                        .get(Tables.NOTES)
                        .update(noteToAdd);

        }
    }
}
