package com.flintcore.my_notes_android.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.flintcore.my_notes_android.Activities;
import com.flintcore.my_notes_android.BundleKey;
import com.flintcore.my_notes_android.R;
import com.flintcore.my_notes_android.database.DatabaseManager;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;

public class NoteViewSelectorListener<F extends Activities> implements AdapterView.OnItemClickListener {

    private final Context ctx;
    private final Class<F> activityClass;

    public NoteViewSelectorListener(Context ctx, Class<F> activityClass) {
        this.ctx = ctx;
        this.activityClass = activityClass;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(ctx, activityClass);

        Note note = (Note) DatabaseManager.getInstance().get(Tables.NOTES).get(i);

        intent.putExtra(BundleKey.SAVE_MSG.name(), R.string.edit_msg);
        intent.putExtra(BundleKey.NOTE_OBJECT.name(), note);
        intent.putExtra(BundleKey.NOTE_ID.name(), i);

        ctx.startActivity(intent);
    }
}
