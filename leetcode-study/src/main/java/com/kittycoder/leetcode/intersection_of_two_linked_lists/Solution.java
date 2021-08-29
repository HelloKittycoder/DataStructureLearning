/**
 * Leetcode - intersection_of_two_linked_lists
 */
package com.kittycoder.leetcode.intersection_of_two_linked_lists;

import com.kittycoder.leetcode.util.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(com.kittycoder.leetcode.maximum_subarray.Solution.class);

    ListNode getIntersectionNode(ListNode headA, ListNode headB);
}
