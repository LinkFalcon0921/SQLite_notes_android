package com.flintcore.my_notes_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.flintcore.my_notes_android.adapters.GridNoteAdapter;
import com.flintcore.my_notes_android.listener.LoadNoteView;
import com.flintcore.my_notes_android.listener.NoteViewSelectorListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewListNotes extends Fragment {

    private GridView listNotes;
    private FloatingActionButton btn_addNotes;
    private GridNoteAdapter noteAdapter;

    public ViewListNotes() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflated = inflater.inflate(R.layout.fragment_view_list_notes,
                container, false);

//        List of notes view
        this.listNotes = inflated.findViewById(R.id.list_notes);
        this.noteAdapter = new GridNoteAdapter(getContext());
        this.listNotes.setAdapter(this.noteAdapter);
        this.listNotes.setOnItemClickListener(
                new NoteViewSelectorListener(getContext(), AddNoteActivity.class)
        );

//        CREATE NOTE BUTTON
        this.btn_addNotes = inflated.findViewById(R.id.new_note_btn);
        this.btn_addNotes.setOnClickListener(new LoadNoteView(getContext()));

        return inflated;
    }

}