package com.pyredevelopment.data;

public interface DataObject {

    /**
     * Drops specified indices from rows or columns
     *
     * Remove rows or columns by specifying either row or columns and
     * then providing a list of indexes you would like to remove.
     *
     * @param rowOrCol 0 for rows or 1 for columns.
     * @param indexes A list of indexes you would like removed or dropped.
     */
    void drop(int rowOrCol, int... indexes);

    /**
     * Drop rows specified by index
     *
     * This method will remove/drop row(s) from the data structure based
     * on the provided index(es).
     * @param indexes The index of the rows you want dropped.
     */
    void dropRows(int ... indexes);

    /**
     * Drop columns specified by index
     *
     * This method will remove/drop columns(s) from the data structure based
     * on the provided index(es).
     * @param indexes The index of the columns you want dropped.
     */
    void dropColumns(int ... indexes);

    /**
     * Drop columns specified by header label
     *
     * This method will remove/drop columns(s) from the data structure based
     * on the provided header label(s).
     * @param labels The label(s) of the columns you want dropped.
     */
    void dropColumns(String ... labels);

    /**
     * Concatenates an additional DataObject onto the caller.
     *
     * Based on the rowOrCol variable this method will append another DataObject,
     * either onto the right or the bottom of the current(caller) DataObject.
     * Note that this requires either both DataObjects to be the same number of rows
     * (if concatenating columns) or the same number of columns (if concatenating rows)
     *
     * @param data The DataObject you want to append to the current one
     * @param rowOrCol 0 if concatenating rows (append to bottom) or 1 is concatenating columns (append to right)
     */
    void concat(DataObject data, int rowOrCol);

    /**
     * Concatenates an additional DataObject onto the bottom of the caller.
     *
     * This method will concatenate two DataObjects by appending the second to the bottom of the caller.
     * Note that this requires both DataObjects to have the same number of columns/features.
     *
     * @param data The data object that you would like to append to the caller.
     */
    void concatRows(DataObject data);

    /**
     * Concatenates an additional DataObject onto the right of the caller.
     *
     * This method will concatenate two DataObjects by appending the second to the right of the caller.
     * Note that this requires both DataObjects to have the same number of rows/entries.
     *
     * @param data The data object that you would like to append to the caller.
     */
    void concatColumns(DataObject data);

    /**
     * Returns a 2 entry integer array indicating the shape of the of DataObject
     * in the form of [Rows, Columns]
     *
     * @return An integer array representing the dimensionality of the DataObject
     */
    String shape();

    String info();

    String head();

    String head(int numberRows);

    String tail();

    String tail(int numberRows);

}
