/**
 * Leetcode - merge_two_sorted_lists
 */
package com.kittycoder.leetcode.merge_two_sorted_lists;

import com.kittycoder.leetcode.util.ListNode;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
public class Solution2 implements Solution {

    /**
     * leetcode提供的递归法 时间复杂度 o(n+m)
     * 递归法需要好好理解下（开始没想出来）
     */
    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
