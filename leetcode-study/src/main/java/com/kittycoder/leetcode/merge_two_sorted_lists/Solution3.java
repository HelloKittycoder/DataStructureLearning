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
public class Solution3 implements Solution {

    /**
     * leetcode提供的迭代法 时间复杂度 o(n+m)
     * 结合官方题解中给的动图，可以很快写出如下代码
     */
    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            /*if (l1.val < l2.val) {
                prev.next = l1;
                prev = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                prev = l2;
                l2 = l2.next;
            }*/
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        /*if (l1 == null) {
            prev.next = l2;
        } else if (l2 == null) {
            prev.next = l1;
        }*/
        // 合并后l1和l2最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return preHead.next;
    }
}
