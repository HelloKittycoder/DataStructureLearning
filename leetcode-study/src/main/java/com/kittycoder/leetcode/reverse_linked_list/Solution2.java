/**
 * Leetcode - reverse_linked_list
 */
package com.kittycoder.leetcode.reverse_linked_list;

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
class Solution2 implements Solution {

    // 递归法求解
    @Override
    public ListNode reverseList(ListNode head) {
        // 找到链表的最后一个节点
        if (head == null || head.next == null) {
            return head;
        }
        /**
         * 如果是递归的倒数第2层，newHead是链表的最后一个节点，head.next也是最后一个节点，
         * head是倒数第二个节点
         *
         * 将后面的节点指向前面的节点
         * 移除前面节点指向后面节点的指针
         */
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
