package com.flintcore.my_notes_android.database.utils;

import static com.flintcore.my_notes_android.database.crud.SqlCRUD.TABLE_COMODIN;

import androidx.annotation.NonNull;

import com.flintcore.my_notes_android.database.Query;
import com.flintcore.my_notes_android.database.columns.Columns;
import com.flintcore.my_notes_android.database.tables.Tables;

import java.util.List;
import java.util.Locale;

public class DBMethods {



    public static String setColumnStringValue(String format, Columns column, String value) {
        return String.format(format, column.getName(), Utilities.wrapString("'", value));
    }

    public static String setColumnValue(String format, Columns column, String value) {
        return String.format(format, column.getName(), value);
    }

    private interface MethodBody {
        String SUBSTR = "substr(%s, %d, %d)";
    }

    public static String SUBSTR(Columns column, int start, int length) {
        return String.format(Locale.ROOT, MethodBody.SUBSTR, column.getName(), start, length);
    }

    @NonNull
    public static String appendTable(Query query, Tables table) {
        return query.getQuery().replaceFirst(TABLE_COMODIN, table.getName());
    }

    @NonNull
    public static String joinColumns(List<String> columns) {
        return Utilities.getJoinedText(columns);
    }

    public static String getQuery(Query query, final Tables table, List<String> columns) {
        String list_columns = DBMethods.joinColumns(columns);
        return String.format(DBMethods.appendTable(query, table), list_columns);
    }

    public static String getSingleQuery(Query query, Tables table, List<String> columnsList,
                                  String whereField) {
        String joinedColumns = DBMethods.joinColumns(columnsList);
        return String.format(DBMethods.appendTable(query, table), joinedColumns, whereField);
    }

    public static String getQuery(Query query, final Tables table, List<String> columns, String values) {
        String list_columns = DBMethods.joinColumns(columns);
        return String.format(DBMethods.appendTable(query, table), list_columns, values);
    }

}
