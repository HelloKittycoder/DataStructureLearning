/**
 * Leetcode - maximum_subarray
 */
package com.kittycoder.leetcode.maximum_subarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    int maxSubArray(int[] nums);
}
