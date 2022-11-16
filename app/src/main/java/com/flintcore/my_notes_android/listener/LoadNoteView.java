package com.flintcore.my_notes_android.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.flintcore.my_notes_android.AddNoteActivity;
import com.flintcore.my_notes_android.BundleKey;
import com.flintcore.my_notes_android.R;

public class LoadNoteView implements View.OnClickListener {

    private final Context context;

    public LoadNoteView(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent toAddView = new Intent(context, AddNoteActivity.class);
        context.startActivity(toAddView);
    }
}
