package com.flintcore.my_notes_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.flintcore.my_notes_android.database.columns.Column;
import com.flintcore.my_notes_android.database.columns.Columns;
import com.flintcore.my_notes_android.database.tables.Tables;

import java.util.Set;

public abstract class DBHelper extends SQLiteOpenHelper {

    protected static final String DB_NAME = "my_notes";
    protected static final int DB_VERSION = 1;
    protected static final String ALL_COLUMNS = "*";


    /**
     * Set of Columns
     */
    protected Set<Column> columns;

    /**
     * 1: Table <br/>
     * 2: Columns with config
     */
    protected static final Query createQuery = () -> "Create table if not exists tb(%s)";

    /**
     * 1: Table
     */
    protected static final Query dropQuery = () -> "Drop table if exists tb";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(getDropQuery());
        onCreate(sqLiteDatabase);
    }

    abstract protected String getCreateQuery();

    abstract protected String getDropQuery();

    abstract public Tables getTable();

    abstract public Columns[] getColumns();

}
