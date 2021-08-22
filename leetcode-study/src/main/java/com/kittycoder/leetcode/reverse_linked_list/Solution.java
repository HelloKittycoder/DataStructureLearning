/**
 * Leetcode - reverse_linked_list
 */
package com.kittycoder.leetcode.reverse_linked_list;

import com.kittycoder.leetcode.util.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    ListNode reverseList(ListNode head);
}
