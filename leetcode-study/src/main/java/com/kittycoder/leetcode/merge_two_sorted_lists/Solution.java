/**
 * Leetcode - merge_two_sorted_lists
 */
package com.kittycoder.leetcode.merge_two_sorted_lists;

import com.kittycoder.leetcode.util.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    ListNode mergeTwoLists(ListNode l1, ListNode l2);
}
