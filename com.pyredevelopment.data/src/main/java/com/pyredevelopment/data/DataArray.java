package com.pyredevelopment.data;

import java.util.HashMap;
import java.util.List;

/**
 * Array-List based DataObject
 *
 * <h3>Overview:</h3>
 * // TODO: Fill
 */
public class DataArray implements DataObject{

    public DataArray() {

    }

    /**
     * Allows the creation of DataArrays from a hashmap object that contains
     * List implementing objects, by default this uses the Keys as column headers.
     */
    public static DataArray fromHashMap(HashMap<?, ? extends List<?>> hashMap) {

        return new DataArray();
    }

}
