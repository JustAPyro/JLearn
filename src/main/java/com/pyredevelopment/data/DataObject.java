package com.pyredevelopment.data;

import java.util.ArrayList;

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
     * Returns an array contains the columns
     *
     * This will return an array containing the column headers. This can be useful to either
     * quickly check the number of columns or to check what data a DataObject contains.
     *
     * @return Array of strings representing column headers.
     */
    ArrayList<String> getColumns();

    /**
     * Returns an indicator of the dimensions of DataObject.
     *
     * Returns a 2 entry integer array indicating the shape of the of DataObject
     * in the form of [Rows, Columns]
     *
     * @return An integer array representing the dimensionality of the DataObject
     */
    int[] shape();

    /**
     * Print a concise summary of a DataFrame.
     *
     * This method prints information about a DataFrame including the index data type and columns,
     * non-null values and memory usage.
     *
     * @return The string representation of the summary
     */
    String info();

    /**
     * Returns the first 5 rows
     *
     * This function returns the first 5 rows for the object based on position.
     * It is useful for quickly testing if your object has the right type of data in it.
     *
     * @return The first 5 rows of the DataObject
     */
    DataObject head();

    /**
     * Returns the first <code>numberRows</code> rows
     *
     * Returns the first <code>numberRows</code> rows for the object based on position.
     * It is useful for quickly testing if your object has the right type of data in it.
     * For negative values of n, this function will return all rows <i>except</i> the
     * last <code>numberRows</code> rows, equivalent to the python <code>list[:-numberRows]</code>
     *
     * @param numberRows Number of rows to select
     * @return The first <code>numberRows</code> of the caller object.
     */
    DataObject head(int numberRows);

    /**
     * Returns the last 5 rows.
     *
     * This function returns the last 5 rows from the object based on position.
     * It is useful for quickly verifying data, for example, after sorting or appending rows.
     *
     * @return The last 5 rows of the caller object.
     */
    String tail();

    /**
     * Returns the last <code>numberRows</code> rows.
     *
     * This function returns the last <code>numberRows</code> rows from the object based on position.
     * It is useful for quickly verifying data, for example after sorting or appending rows.
     * For negative values of <code>numberRows</code> this function returns all rows except the <i>first</i>
     * <code>numberRows</code>, equivalent to the python list[numberRows:]
     *
     * @param numberRows The number of rows to select
     * @return The last <code>numberRows</code> rows of the caller object.
     */
    String tail(int numberRows);

}
