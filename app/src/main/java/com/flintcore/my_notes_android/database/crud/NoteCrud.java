package com.flintcore.my_notes_android.database.crud;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.flintcore.my_notes_android.MainActivity;
import com.flintcore.my_notes_android.R;
import com.flintcore.my_notes_android.database.DBHelper;
import com.flintcore.my_notes_android.database.Query;
import com.flintcore.my_notes_android.database.columns.Columns;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;
import com.flintcore.my_notes_android.database.utils.DBMethods;
import com.flintcore.my_notes_android.database.utils.Utilities;
import com.flintcore.my_notes_android.filters.MaxLengthFilter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NoteCrud implements SqlCRUD<Integer, Note> {
    private final AtomicBoolean updated;

    private final DBHelper DB_TABLE;
    private final List<Note> notes;


    public NoteCrud(DBHelper dbHelper) {
        this.DB_TABLE = dbHelper;
        updated = new AtomicBoolean();
        this.notes = new ArrayList<>();
    }


    public List<Note> getNotes() {
        return notes;
    }

    public void commitRefresh() {
        this.updated.set(false);
    }

    @Override
    public boolean wasChanged() {
        return !this.updated.get();
    }

    @Override
    public List<Note> getAll() {
        if (!this.wasChanged()) {
            return this.getNotes();
        }

        List<String> columnsList = Utilities.getListOf(
                this.getColumn(0).getName(),
                DBMethods.SUBSTR(this.getColumn(1), MaxLengthFilter.Lengths.START_LENGTH_TEXT,
                        MaxLengthFilter.Lengths.MAX_DESCRIPTION_LENGTH_VIEW),
                DBMethods.SUBSTR(this.getColumn(2), MaxLengthFilter.Lengths.START_LENGTH_TEXT,
                        MaxLengthFilter.Lengths.MAX_DESCRIPTION_LENGTH_VIEW)
        );

        String sql = DBMethods.getQuery(getAll, this.DB_TABLE.getTable(), columnsList);
        Cursor query = getReadableDatabase().rawQuery(sql, null);

        this.getNotes().clear();

        while (query.moveToNext()) {
            Note note = getNote(query);

            this.getNotes().add(note);
        }

        this.updated.set(true);
        query.close();

        return this.getNotes();
    }

    /**
     * Id in the listItem.
     */
    @Override
    public Note get(Integer index) {
        List<String> columnsList = Utilities.getListOf(
                this.getColumn(0).getName(),
                this.getColumn(1).getName(),
                this.getColumn(2).getName()
        );

        String idNote = String.valueOf(this.getNotes().get(index).getId());
        String[] sqlArgs = {idNote};

        String sql = DBMethods.getSingleQuery(getById, this.DB_TABLE.getTable(),
                columnsList, columnsList.get(0));

        Cursor query = getReadableDatabase().rawQuery(sql, sqlArgs);

        Note note = null;

        if (query.moveToFirst()) {
            note = getNote(query);
        }

        query.close();
        return note;
    }

    @NonNull
    private Note getNote(Cursor query) {
        Note note = new Note();

        note.setId(query.getInt(0));
        note.setSubject(query.getString(1));
        note.setDescription(query.getString(2));

        return note;
    }

    @Override
    public void create(Note note) {
        try {
            List<String> columnsList = Utilities.getListOf(
                    this.getColumn(1).getName(),
                    this.getColumn(2).getName()
            );

            String columnsValues = Utilities.getJoinedColumnsValues(note.getSubject(),
                    note.getDescription());

            getWritableDatabase().execSQL(DBMethods.getQuery(insertQuery, Tables.NOTES,
                    columnsList, columnsValues));

            this.commitRefresh();
            MainActivity.messages.toastMessage(R.string.note_added, Toast.LENGTH_SHORT);
        } catch (SQLException e) {
            MainActivity.messages.snackBarMessage(R.string.no_added_note_message,
                    Snackbar.LENGTH_LONG);
        }

    }

    @Override
    public void update(Note note) {

        try {
            List<String> columnsList = Utilities.getListOf(
                    this.getColumn(0).getName(),
                    this.getColumn(1).getName(),
                    this.getColumn(2).getName()
            );

            List<String> columnsUpdated = Utilities.getListOf(
                    DBMethods.setColumnStringValue(UPDATE_FORMAT, this.getColumn(1),
                            note.getSubject()),
                    DBMethods.setColumnStringValue(UPDATE_FORMAT, this.getColumn(2),
                            note.getDescription())
            );

            String sql = DBMethods.getQuery(updateQuery, this.getTable(),
                    columnsUpdated, columnsList.get(0));

            int indexNote = note.getId();
            String[] args = {String.valueOf(this.getNotes().get(indexNote).getId())};

            getWritableDatabase().execSQL(sql, args);

            this.commitRefresh();
            MainActivity.messages.toastMessage(R.string.note_edited, Toast.LENGTH_LONG);
        } catch (SQLException e) {
            MainActivity.messages.snackBarMessage(R.string.no_edited_note_message,
                    Snackbar.LENGTH_LONG);
        }

    }

    @Override
    public void delete(Integer integer) {
        try {
            List<String> columnsList = Utilities.getListOf(
                    this.getColumn(0).getName()
            );

            String idNote = String.valueOf(this.getNotes().get(integer).getId());
            String[] args = {idNote};

            String sql = DBMethods.getQuery(deleteQuery, getTable(), columnsList);
            getWritableDatabase().execSQL(sql, args);

            this.commitRefresh();
            MainActivity.messages.toastMessage(R.string.note_deleted, Toast.LENGTH_LONG);
        } catch (SQLException e) {
            MainActivity.messages.snackBarMessage(R.string.no_deleted_note_message,
                    Toast.LENGTH_LONG);
        }
    }

    private Tables getTable() {
        return this.DB_TABLE.getTable();
    }

//    #Privates/ protected methods

    private Columns getColumn(int pos) {
        return this.DB_TABLE.getColumns()[pos];
    }


    private SQLiteDatabase getReadableDatabase() {
        return this.DB_TABLE.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.DB_TABLE.getWritableDatabase();
    }

}
