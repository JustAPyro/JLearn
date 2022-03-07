package com.pyredevelopment.data;

import java.util.*;

public class DataArray implements DataObject{

    HashMap<String, ArrayList<?>> mainData;
    ArrayList<ArrayList<?>> listsByIndex;

    public DataArray() {

        // Initialize the mainData set
        mainData = new LinkedHashMap<>();

    }

    public DataArray(Map<?, ? extends List<?>> hashMap) {

        // Initialize the mainData set
        mainData = new LinkedHashMap<>();

        // Iterate through the keys
        for (Object columns : hashMap.keySet()) {

            ArrayList<?> list = new ArrayList<Object>(hashMap.get(columns));

            // For each, insert it into mainData, creating a new ArrayList with target List
            mainData.put(columns.toString(), list);

            listsByIndex.add(list);

        }

    }

    public DataArray(FileType fileType, String fileLocation) {

    }

    /**
     * Drops specified indices from rows or columns
     * <p>
     * Remove rows or columns by specifying either row or columns and
     * then providing a list of indexes you would like to remove.
     *
     * @param rowOrCol 0 for rows or 1 for columns.
     * @param indexes  A list of indexes you would like removed or dropped.
     */
    @Override
    public void drop(int rowOrCol, int... indexes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Drop rows specified by index
     * <p>
     * This method will remove/drop row(s) from the data structure based
     * on the provided index(es).
     *
     * @param indexes The index of the rows you want dropped.
     */
    @Override
    public void dropRows(int... indexes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Drop columns specified by index
     * <p>
     * This method will remove/drop columns(s) from the data structure based
     * on the provided index(es).
     *
     * @param indexes The index of the columns you want dropped.
     */
    @Override
    public void dropColumns(int... indexes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Drop columns specified by header label
     * <p>
     * This method will remove/drop columns(s) from the data structure based
     * on the provided header label(s).
     *
     * @param labels The label(s) of the columns you want dropped.
     */
    @Override
    public void dropColumns(String... labels) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Concatenates an additional DataObject onto the caller.
     * <p>
     * Based on the rowOrCol variable this method will append another DataObject,
     * either onto the right or the bottom of the current(caller) DataObject.
     * Note that this requires either both DataObjects to be the same number of rows
     * (if concatenating columns) or the same number of columns (if concatenating rows)
     *
     * @param data     The DataObject you want to append to the current one
     * @param rowOrCol 0 if concatenating rows (append to bottom) or 1 is concatenating columns (append to right)
     */
    @Override
    public void concat(DataObject data, int rowOrCol) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Concatenates an additional DataObject onto the bottom of the caller.
     * <p>
     * This method will concatenate two DataObjects by appending the second to the bottom of the caller.
     * Note that this requires both DataObjects to have the same number of columns/features.
     *
     * @param data The data object that you would like to append to the caller.
     */
    @Override
    public void concatRows(DataObject data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Concatenates an additional DataObject onto the right of the caller.
     * <p>
     * This method will concatenate two DataObjects by appending the second to the right of the caller.
     * Note that this requires both DataObjects to have the same number of rows/entries.
     *
     * @param data The data object that you would like to append to the caller.
     */
    @Override
    public void concatColumns(DataObject data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getValue(int row, int col) {
        return (double) listsByIndex.get(col).get(row);
    }

    @Override
    public double getValue(int row, String col) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Returns an array contains the columns
     * <p>
     * This will return an array containing the column headers. This can be useful to either
     * quickly check the number of columns or to check what data a DataObject contains.
     *
     * @return Array of strings representing column headers.
     */
    @Override
    public ArrayList<String> getColumns() {

        // Return the key-set of mainData as an array
        return new ArrayList<>(mainData.keySet());

    }


    /**
     * Returns an indicator of the dimensions of DataObject.
     * <p>
     * Returns a 2 entry integer array indicating the shape of the of DataObject
     * in the form of [Rows, Columns]
     *
     * @return An integer array representing the dimensionality of the DataObject
     */
    @Override
    public int[] shape() {

        // Each column is defined by a key, so size() returns columns
        int columns = mainData.size();

        // If there are no keys/columns in the set
        if (columns == 0)
            // It must be an empty DataArray, so return 0, 0
            return new int[]{0, 0};

        // If it's not empty, calculate the rows by creating an iterator, and getting the size of first value
        int rows = mainData.entrySet().iterator().next().getValue().size();

        // Now return the results
        return new int[]{rows, columns};

    }

    /**
     * Print a concise summary of a DataFrame.
     * <p>
     * This method prints information about a DataFrame including the index data type and columns,
     * non-null values and memory usage.
     *
     * @return The string representation of the summary
     */
    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the first 5 rows
     * <p>
     * This function returns the first 5 rows for the object based on position.
     * It is useful for quickly testing if your object has the right type of data in it.
     *
     * @return The first 5 rows of the DataObject
     */
    @Override
    public DataObject head() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the first <code>numberRows</code> rows
     * <p>
     * Returns the first <code>numberRows</code> rows for the object based on position.
     * It is useful for quickly testing if your object has the right type of data in it.
     * For negative values of n, this function will return all rows <i>except</i> the
     * last <code>numberRows</code> rows, equivalent to the python <code>list[:-numberRows]</code>
     *
     * @param numberRows Number of rows to select
     * @return The first <code>numberRows</code> of the caller object.
     */
    @Override
    public DataObject head(int numberRows) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the last 5 rows.
     * <p>
     * This function returns the last 5 rows from the object based on position.
     * It is useful for quickly verifying data, for example, after sorting or appending rows.
     *
     * @return The last 5 rows of the caller object.
     */
    @Override
    public String tail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the last <code>numberRows</code> rows.
     * <p>
     * This function returns the last <code>numberRows</code> rows from the object based on position.
     * It is useful for quickly verifying data, for example after sorting or appending rows.
     * For negative values of <code>numberRows</code> this function returns all rows except the <i>first</i>
     * <code>numberRows</code>, equivalent to the python list[numberRows:]
     *
     * @param numberRows The number of rows to select
     * @return The last <code>numberRows</code> rows of the caller object.
     */
    @Override
    public String tail(int numberRows) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // - - - - - - - - - - Static Methods - - - - - - - - - -

    public static DataArray fromMap(Map<?, ? extends List<?>> hashMap) {

        return new DataArray(hashMap);

    }
}
