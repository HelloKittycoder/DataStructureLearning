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
public class Solution1 implements Solution {

    // 采用迭代法
    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 == null) {
                return l2;
            } else {
                return l1;
            }
        } else {
            ListNode iterNode1 = l1;
            ListNode iterNode2 = l2;
            ListNode result = new ListNode();
            ListNode resultIterNode = result;

            /**
             * 思路：
             * 1.链表l1和链表l2同时循环
             * 2.每次循环让resultIterNode的next指向两个链表里元素的较小值
             * 并且让当前较小值所在的链表next向后移一位
             * 3.如果l1或l2其中一个已经提前结束了，则把另外一个链表未循环完成的元素追加到result链表的末尾
             * 4.返回result链表head节点的下一个节点
             */
            while (iterNode1 != null && iterNode2 != null) {
                if (iterNode1.val <= iterNode2.val) {
                    resultIterNode.next = new ListNode(iterNode1.val);
                    iterNode1 = iterNode1.next;
                } else {
                    resultIterNode.next = new ListNode(iterNode2.val);
                    iterNode2 = iterNode2.next;
                }
                resultIterNode = resultIterNode.next;
            }

            if (iterNode1 != null) {
                while (iterNode1 != null) {
                    resultIterNode.next = new ListNode(iterNode1.val);
                    iterNode1 = iterNode1.next;
                    resultIterNode = resultIterNode.next;
                }
            } else if (iterNode2 != null) {
                while (iterNode2 != null) {
                    resultIterNode.next = new ListNode(iterNode2.val);
                    iterNode2 = iterNode2.next;
                    resultIterNode = resultIterNode.next;
                }
            }

            return result.next;
        }
    }
}
