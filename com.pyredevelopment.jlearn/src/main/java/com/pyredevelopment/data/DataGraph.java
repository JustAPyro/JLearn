package com.pyredevelopment.data;

/**
 * A Linked Implementation of a DataObject
 *
 * <h3>Overview:</h3>
 * This class is a Linked implementation of a <code>DataObject</code>, which is meant to
 * function similarly to a Pandas DataFrame. This is one of three options you have
 * to load your Data with before accessing any of the machine learning classes.
 * <br>
 * <br>
 * <h3>Usage:</h3>
 * The goal here is to make loading Data as easy as possible. The exact usage
 * depends on how your data is organized in non-volatile memory, but
 * generally the easiest method will be to use one of the static read
 * methods (i.e. <code>.readCSV(file)</code>).
 * <br>
 * <br>
 * <h3>Technical Information</h3>
 * This implementation is technically a graph, hence the name. Each node contains pointers to more instance/sample
 * data using left/right pointers, as well as a pointer to the next instance (down). This is not doubly linked.
 * The goal here is to provide advantages related to manipulating the data, specifically allowing you to add/remove columns
 * and rows as easily as possible, as well as allowing you to organize and shift them. If your data
 *
 */
public class DataGraph {

    /**
     * Defining the node unit in the Datagraph
     */
    class Node {
        Node next;
    }

}
