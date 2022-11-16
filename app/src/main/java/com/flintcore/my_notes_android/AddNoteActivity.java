package com.flintcore.my_notes_android;

import static com.flintcore.my_notes_android.filters.MaxLengthFilter.DESCRIPTION_MAX_LENGTH;
import static com.flintcore.my_notes_android.filters.MaxLengthFilter.SUBJECT_MAX_LENGTH;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.filters.MaxLengthFilter;
import com.flintcore.my_notes_android.listener.InsertNoteOnClick;
import com.flintcore.my_notes_android.listener.LimitTextChanger;
import com.flintcore.my_notes_android.utils.Alert;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity implements Activities {

    private EditText subject_txt;
    private EditText description_txt;
    private TextView max_subject_size;
    private TextView max_description_size;

    private Button btn_save;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.subject_txt = findViewById(R.id.subject_note);
        this.description_txt = findViewById(R.id.description_note);

        this.max_subject_size = findViewById(R.id.subject_limit_txt);
        this.max_description_size = findViewById(R.id.description_limit_txt);

//        Buttons

//        Save button
        this.btn_save = findViewById(R.id.btn_save);

        setConfirmButton();

//        Cancel button
        this.btn_cancel = findViewById(R.id.btn_cancel);
        this.btn_cancel.setOnClickListener(view -> finish());
//        Filter of editText
        setEditTextFilters();
        setViewTextRemained();
//        Data of the note
        setNoteIntentInfo();
    }

    private void setConfirmButton() {
        //      Set title of save edit btn
        int textId = getIntent().getIntExtra(BundleKey.SAVE_MSG.name(),
                R.string.save_msg);

        int note_id = getNoteId();

        this.btn_save.setOnClickListener(
                new InsertNoteOnClick(note_id, this, this.subject_txt, this.description_txt));

        this.btn_save.setText(textId);
    }

    private int getNoteId() {
        return getIntent().getIntExtra(BundleKey.NOTE_ID.name(), -1);
    }

    private Note getSerializable(Bundle bundle, BundleKey key) {
        return (Note) bundle.getSerializable(key.name());
    }

    private void setNoteIntentInfo() {
        Bundle bundle = getIntent().getExtras();
        if (Objects.isNull(bundle)) {
            return;
        }

        Note note = getSerializable(bundle, BundleKey.NOTE_OBJECT);

        if (Objects.nonNull(note)) {
            this.subject_txt.setText(note.getSubject());
            this.description_txt.setText(note.getDescription());
        }
    }

    private void setViewTextRemained() {
        this.subject_txt.addTextChangedListener(
                new LimitTextChanger(max_subject_size,
                        MaxLengthFilter.Lengths.MAX_SUBJECT_LENGTH));

        this.description_txt.addTextChangedListener(
                new LimitTextChanger(max_description_size,
                        MaxLengthFilter.Lengths.MAX_DESCRIPTION_LENGTH));
    }

    private void setEditTextFilters() {
        this.subject_txt.setFilters(new android.text.InputFilter[]{SUBJECT_MAX_LENGTH});
        this.description_txt.setFilters(new android.text.InputFilter[]{DESCRIPTION_MAX_LENGTH});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_crud_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_menu_item:
                new Alert(AddNoteActivity.this)
                        .createSimpleAlert(getNoteId(),
                                R.string.delete_verification_msg,
                                R.string.confirm_delete_msg,
                                R.string.cancel_msg,
                                () -> finish());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}