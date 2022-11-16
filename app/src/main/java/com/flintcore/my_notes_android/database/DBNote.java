package com.flintcore.my_notes_android.database;

import static com.flintcore.my_notes_android.database.crud.SqlCRUD.TABLE_COMODIN;

import android.content.Context;

import androidx.annotation.Nullable;

import com.flintcore.my_notes_android.database.columns.Columns;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;
import com.flintcore.my_notes_android.database.utils.ColumnsAssemble;
import com.flintcore.my_notes_android.database.utils.StringAssemble;

import java.util.List;

public class DBNote extends DBHelper {


    /*Necessary*/
    private static final Tables TABLE = Tables.NOTES;
    private static final Columns[] COLUMNS = {
            Columns.ID_NOTE, Columns.NOTES_SUBJECT, Columns.NOTES_DESCRIPTION
    };

    public DBNote(@Nullable Context context) {
        super(context);
        this.columns = ColumnsAssemble.getNoteColumns();
    }

    @Override
    public String getCreateQuery() {
        return String.format(createQuery.getQuery()
                        .replaceFirst(TABLE_COMODIN, TABLE.getName()),
                StringAssemble.assemble(this.columns));
    }

    @Override
    public String getDropQuery() {
        return dropQuery.getQuery().replaceFirst(TABLE_COMODIN, TABLE.getName());
    }

    @Override
    public Tables getTable() {
        return TABLE;
    }

    @Override
    public Columns[] getColumns() {
        return COLUMNS;
    }
}
