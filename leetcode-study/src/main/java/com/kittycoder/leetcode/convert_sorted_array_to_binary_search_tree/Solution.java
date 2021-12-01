/**
 * Leetcode - convert_sorted_array_to_binary_search_tree
 */
package com.kittycoder.leetcode.convert_sorted_array_to_binary_search_tree;

import com.kittycoder.leetcode.util.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    TreeNode sortedArrayToBST(int[] nums);
}
