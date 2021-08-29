/**
 * Leetcode - intersection_of_two_linked_lists
 */
package com.kittycoder.leetcode.intersection_of_two_linked_lists;

import com.kittycoder.leetcode.util.ListNode;

import java.util.Objects;

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
     * 双指针
     * 官方提供的这种思路确实没想到，仔细研究发现，思路很巧妙，可以多品味下
     * 和“哈希集合”的方式比，时间复杂度相同 都是 o(m+n)，其中m和n分别是链表headA和headB的长度
     * 但是空间复杂度降低了，由o(m)变成o(1)了
     */
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            /*
            // 这种写法，相交的情况下不会有问题，如果两个链表不相交，会出现死循环，因为pA和pB同时到达next为null时，
            // pA对应了headB，pB对应了headA
            // （pA、pB分别对应headA、headB时，会循环到出现pA、pB分别对应headB、headA）
            // （pA、pB分别对应headB、headA时，也会循环到出现pA、pB分别对应headA、headB），这样循环永远不会结束
            pA = pA.next == null ? headB : pA.next;
            pB = pB.next == null ? headA : pB.next;*/

            // 同时循环到pA和pB的next都为null的前一个节点时，再循环1次，pA=null，pB=null，
            // 再循环1次，发现pA=pB，结束循环
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
