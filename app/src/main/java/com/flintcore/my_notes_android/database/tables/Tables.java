package com.flintcore.my_notes_android.database.tables;

import java.util.Locale;

public enum Tables {
    NOTES;

    public String getName() {
        return this.name().replaceAll("_", " ").toLowerCase(Locale.ROOT);
    }
}
