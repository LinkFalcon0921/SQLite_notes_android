package com.flintcore.my_notes_android.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.flintcore.my_notes_android.R;
import com.flintcore.my_notes_android.database.DatabaseManager;
import com.flintcore.my_notes_android.database.tables.Tables;

public class Alert {

    private final Context context;

    public Alert(Context context) {
        this.context = context;
    }

    public void createSimpleAlert(int id, @StringRes int view_msg, @StringRes int positive_msg,
                                  @StringRes int negative_msg, Action actionPositive) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            View layout = LayoutInflater.from(context).inflate(R.layout.dialog_confirm, null);

//            Message
            ((TextView) layout.findViewById(R.id.valid_msg)).setText(view_msg);

            builder.setView(layout)
                    .setPositiveButton(positive_msg,
                            (dialog, i) -> {
                                DatabaseManager.getInstance().get(Tables.NOTES).delete(id);
                                dialog.dismiss();
                                actionPositive.play();
                            })
                    .setNegativeButton(negative_msg,
                            (dialog, i) -> dialog.dismiss());

            builder.create().show();

        } catch (Exception e) {
        }
    }
}
