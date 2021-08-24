/**
 * Leetcode - remove_duplicates_from_sorted_array
 */
package com.kittycoder.leetcode.remove_duplicates_from_sorted_array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    int removeDuplicates(int[] nums);
}
