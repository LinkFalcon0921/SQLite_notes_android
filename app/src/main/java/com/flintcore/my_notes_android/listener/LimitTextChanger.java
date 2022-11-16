package com.flintcore.my_notes_android.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class LimitTextChanger implements TextWatcher {



    private final TextView viewer;
    private final int MAX_LENGTH;

    public LimitTextChanger(TextView viewer, int maxLength) {
        this.viewer = viewer;
        this.MAX_LENGTH = maxLength;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int count, int newLength) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int count, int newLength) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.viewer.setText(Integer.toString(MAX_LENGTH - editable.length()));
    }

}
