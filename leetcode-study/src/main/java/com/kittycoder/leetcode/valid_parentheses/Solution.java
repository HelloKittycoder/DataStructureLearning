/**
 * Leetcode - valid_parentheses
 */
package com.kittycoder.leetcode.valid_parentheses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    boolean isValid(String s);
}
