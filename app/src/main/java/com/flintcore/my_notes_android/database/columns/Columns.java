package com.flintcore.my_notes_android.database.columns;

import java.util.Locale;

public enum Columns {

    ID_NOTE,
    NOTES_SUBJECT,
    NOTES_DESCRIPTION;

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

}
