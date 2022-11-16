package com.flintcore.my_notes_android.database.columns;

import java.util.Locale;

public enum ColumnType {

    INTEGER,
    TEXT;

    public String getName() {
        return this.name().replaceAll("_", " ").toLowerCase(Locale.ROOT);
    }
}
