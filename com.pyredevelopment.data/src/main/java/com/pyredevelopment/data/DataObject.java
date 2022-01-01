package com.pyredevelopment.data;

public interface DataObject {

    /**
     * Access a single value for a row/column label pair.
     *
     * Imitation of Pandas.DataFrame.at() - See that documentation here:
     * <a href="https://pandas.pydata.org/docs/reference/api/pandas.DataFrame.at.html">Pandas.DataFrame.at</a>
     *
     * @param index Index of the value that you'd like (0-based)
     * @param title The title of the column you want the value from
     * @return The object at that location
     */
    public <T> T at(int index, String title);

}
