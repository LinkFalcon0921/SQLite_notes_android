package com.flintcore.my_notes_android.database.utils;

import com.flintcore.my_notes_android.database.columns.Column;
import com.flintcore.my_notes_android.database.columns.ColumnConfig;

import java.util.Set;
import java.util.StringJoiner;

public class StringAssemble {

    private static final String SPACE_DELIMITER = " ";
    private static final String COLUMN_DELIMITER = ", ";

    public static String assemble(Set<Column> columns) {
        StringJoiner joiner = new StringJoiner(COLUMN_DELIMITER);

        for (Column column : columns) {
            StringJoiner joinerInner = new StringJoiner(SPACE_DELIMITER);

            joinerInner.add(column.getName())
                    .add(column.getColumnType());

            column.getConfigs()
                            .forEach(joinerInner::add);

            joiner.add(joinerInner.toString());
        }

        return joiner.toString();
    }
}
