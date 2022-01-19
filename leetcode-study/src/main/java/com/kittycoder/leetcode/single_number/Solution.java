/**
 * Leetcode - single_number
 */
package com.kittycoder.leetcode.single_number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shucheng on 2022/1/19 12:06
 */
public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    int singleNumber(int[] nums);
}
