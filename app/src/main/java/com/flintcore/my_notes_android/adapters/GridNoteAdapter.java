package com.flintcore.my_notes_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.flintcore.my_notes_android.R;
import com.flintcore.my_notes_android.database.DatabaseManager;
import com.flintcore.my_notes_android.database.crud.SqlCRUD;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;

import java.util.List;
import java.util.Objects;

public class GridNoteAdapter extends BaseAdapter {

    private final Context context;
    private final DatabaseManager databaseManager;

    public GridNoteAdapter(Context context) {
        this.context = context;
        databaseManager = DatabaseManager.getInstance();
    }

    private final SqlCRUD getDatabase() {
        return databaseManager.get(Tables.NOTES);
    }

    public List<Note> getNotes() {

        return this.getDatabase()
                .getAll();
    }

    @Override
    public int getCount() {
        return this.getNotes().size();
    }

    @Override
    public Note getItem(int i) {
        return this.getNotes().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (Objects.isNull(view)) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_note_view,
                    viewGroup, false);
        }
        Note actualNote = this.getItem(i);

        TextView subject_txt = view.findViewById(R.id.subject_txt);
        subject_txt.setText(actualNote.getSubject());

        TextView description_txt = view.findViewById(R.id.description_txt);
        description_txt.setText(actualNote.getDescription());

        this.notifyDataSetChanged();

        return view;

    }
}
