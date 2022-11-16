package com.flintcore.my_notes_android.database.columns;

import java.util.List;
import java.util.stream.Collectors;

public class Column {

    private final int id;
    private final Columns name;
    private final ColumnType columnType;
    private final List<ColumnConfig> configs;

    public Column(int id, Columns name, ColumnType columnTypes, List<ColumnConfig> configs) {
        this.id = id;
        this.name = name;
        this.columnType = columnTypes;
        this.configs = configs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public String getColumnType() {
        return columnType.getName();
    }

    public List<String> getConfigs() {
        return this.configs.stream()
                .map(ColumnConfig::getName)
                .collect(Collectors.toList());
    }
}
