package com.flintcore.my_notes_android.filters;

import android.text.InputFilter;

public interface MaxLengthFilter {

    InputFilter SUBJECT_MAX_LENGTH = new InputFilter.LengthFilter(Lengths.MAX_SUBJECT_LENGTH);
    InputFilter DESCRIPTION_MAX_LENGTH = new InputFilter.LengthFilter(Lengths.MAX_DESCRIPTION_LENGTH);

    interface Lengths {

        int START_LENGTH_TEXT = 0;

        int MAX_SUBJECT_LENGTH = 30;
        int MAX_SUBJECT_LENGTH_VIEW = 10;

        int MAX_DESCRIPTION_LENGTH = 300;
        int MAX_DESCRIPTION_LENGTH_VIEW = 8;
    }

}
