package com.flintcore.my_notes_android.database;

import android.content.Context;

import com.flintcore.my_notes_android.database.crud.NoteCrud;
import com.flintcore.my_notes_android.database.crud.SqlCRUD;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DatabaseManager {

    private static DatabaseManager manager;
    private final Map<Tables, SqlCRUD> databases;

    public static DatabaseManager getInstance() {
        if (Objects.isNull(manager)) {
            manager = new DatabaseManager();
        }

        return manager;
    }

    private DatabaseManager() {
        this.databases = new HashMap<>();
    }

    public void add(Context cxt, Tables key) {
        if (!this.databases.containsKey(key)) {
            this.databases.put(key, convertTo(cxt, key));
        }
    }

    private SqlCRUD convertTo(Context cxt, @NotNull Tables table) {
        switch (table) {
            case NOTES:
                DBNote dbHelper = new DBNote(cxt);
                return new NoteCrud(dbHelper);
        }
        return null;
    }

    public SqlCRUD get(Tables table) {
        return this.databases.get(table);
    }

    @Deprecated
    public enum DBType {
        WRITE,
        READ
    }
}
