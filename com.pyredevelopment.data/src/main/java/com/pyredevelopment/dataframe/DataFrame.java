package com.pyredevelopment.dataframe;

/**
 * A data structure that supports the machine learning algorithms in this library.
 *
 * A <code> DataFrame </code> is a Linked-list/graph based data structure that can be read into memory
 * from non-volatile storage. The best way to create a DataFrame from a file is to use one of the static <code>DataFrame.read*</code>
 * methods on a file. (i.e. <code>DataFrame data = DataFrame.readCSV(filepath)</code>
 * <br>
 * <br>
 * Note: Most libraries use an array based approach to this data structure, in this case I've used a Linked approach for
 * two primary reasons.
 * <ol>
 * <li>It makes modifying the DataFrame easier, you can remove rows/columns and add them very easily.</li>
 * <li>In machine learning, the list is almost always accessed in it's entirely, and often in a sequential fashion.</li>
 * </ol>
 */
public class DataFrame {




}
