package com.flintcore.my_notes_android.database.utils;

import com.flintcore.my_notes_android.database.columns.Column;
import com.flintcore.my_notes_android.database.columns.ColumnConfig;
import com.flintcore.my_notes_android.database.columns.ColumnType;
import com.flintcore.my_notes_android.database.columns.Columns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class ColumnsAssemble {

    private static Set<Column> columns;

    public static Set<Column> getNoteColumns() {
        try {
            columns = new HashSet<>();

            appendColumn(Columns.ID_NOTE, ColumnType.INTEGER,
                    ColumnConfig.PRIMARY_KEY, ColumnConfig.AUTOINCREMENT);

            appendColumn(Columns.NOTES_SUBJECT, ColumnType.TEXT, null);
            appendColumn(Columns.NOTES_DESCRIPTION, ColumnType.TEXT, null);

            return columns;
        } finally {
            columns = null;
        }
    }

    private static void appendColumn(Columns column, ColumnType columnType, ColumnConfig... configs) {
        int id_column = ColumnsAssemble.columns.size() + 1;
        Column coll = new Column(id_column, column, columnType, createList(configs));

        ColumnsAssemble.columns.add(coll);
    }

    private static <T> List<T> createList(T... values) {
        if (Objects.isNull(values)) {
            return new ArrayList<>();
        }

        return Arrays.stream(values)
                .collect(Collectors.toList());
    }

}
