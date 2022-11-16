package com.flintcore.my_notes_android.database.crud;

import com.flintcore.my_notes_android.database.Query;

import java.util.List;

public interface SqlCRUD<ID, T> {

    String TABLE_COMODIN = "tb";
    String UPDATE_FORMAT = "%s = %s";

    List<T> getAll();

    T get(ID id);

    void create(T t);

    void update(T t);

    void delete(ID id);

    void commitRefresh();

    boolean wasChanged();

    /*Default queries*/

    /**
     * TABLE <br/>
     * COLUMN_NAMES <br/>
     * COLUMN_VALUES <br/>
     */
    Query insertQuery = () -> "insert into tb(%s) values (%s)";

    /**
     * TABLE <br/>
     * COLUMN_VALUES <br/>
     * WHERE_FIELD <br/>
     * WHERE_VALUE
     */
    Query updateQuery = () -> "update tb set %s where %s = ?";

    /**
     * 1: Table <br/>
     * 2: Column<br/>
     * 3: Column value
     */
    Query deleteQuery = () -> "Delete from tb where %s = ?";

    /**
     * 1: Column <br/>
     * 2: Table
     */
    Query getAll = () -> "select %s from tb";

    /**
     * 1: Column <br/>
     * 2: Table <br/>
     * 3: Column <br/>
     * 4: Column value
     */
    Query getById = () -> "select %s from tb where %s = ?";


}
