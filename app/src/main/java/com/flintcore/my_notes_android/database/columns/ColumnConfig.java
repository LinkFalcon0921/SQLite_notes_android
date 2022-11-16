package com.flintcore.my_notes_android.database.columns;

import java.util.Locale;

public enum ColumnConfig {
    PRIMARY_KEY,
    AUTOINCREMENT;

    public String getName() {
        return this.name().replaceAll("_", " ").toLowerCase(Locale.ROOT);
    }
}
