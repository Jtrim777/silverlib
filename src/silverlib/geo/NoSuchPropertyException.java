package silverlib.geo;

import java.io.*;

import silverlib.log.*;

/**
 * Thrown when a property that a <code>Shape</code> does not possess is accessed
 *
 * @author Jake Trimble
 * @since 1.7.4
 */
public class NoSuchPropertyException extends Exception {
    String propName;

    /**
     * Initializes a <code>NoSuchPropertyException</code>
     *
     * @param sn The name of the property
     *
     * @since 1.7.4
     */
    NoSuchPropertyException(String sn) {
        propName = sn;
    }
} 
