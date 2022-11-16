package com.flintcore.my_notes_android.database.utils;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Utilities {

    /**Return a immutable list with given objects.*/
    public static <T> List<T> getListOf(T... items) {
        return Arrays.asList(items);
    }

    @NonNull
    public static String wrapString(String wrapper, String value){
        return wrapper.concat(value).concat(wrapper);
    }

    @NonNull
    public static String getJoinedText(String... columns) {
        return String.join(", ", columns);
    }

    public static String getJoinedText(Iterable<String> columns) {
        return String.join(", ", columns);
    }

    @NonNull
    public static String getJoinedColumnsValues(String... columns) {
        StringJoiner joiner = new StringJoiner("','", "'", "'");

        Arrays.asList(columns)
                .stream().map(String::trim)
                .forEach(joiner::add);

        return joiner.toString();
    }
}
