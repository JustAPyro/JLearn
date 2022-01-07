package com.pyredevelopment.data;

import java.util.ArrayList;

public interface DataObject extends Iterable<Object[]> {



    /**
     * Access a single value for a row/column label pair.
     * Note that this returns an Object type,
     *
     * Imitation of Pandas.DataFrame.at() - See that documentation here:
     * <a href="https://pandas.pydata.org/docs/reference/api/pandas.DataFrame.at.html">Pandas.DataFrame.at</a>
     *
     * @param index Index of the value that you'd like (0-based)
     * @param title The title of the column you want the value from
     * @return The object at that location
     */
    public Object at(int index, String title);

    /**
     * The column labels of the DataFrame.
     *
     * Imitation of Pandas.DataFrame.columns() - See that documentation here:
     * <a href="https://pandas.pydata.org/docs/reference/api/pandas.DataFrame.columns.html#pandas.DataFrame.columns">Pandas.DataFrame.columns</a>
     *
     * @return
     */
    public ArrayList<String> columns();

    public ArrayList<Object> getFeature(String feature);

    public DataObject getSubset(String[] features);

    public int features();

    public int instances();

}
