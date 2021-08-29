/**
 * Leetcode - intersection_of_two_linked_lists
 */
package com.kittycoder.leetcode.intersection_of_two_linked_lists;

import com.kittycoder.leetcode.util.ListNode;

import java.util.HashSet;
import java.util.Set;

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

    /**
     * 使用哈希集合 时间复杂度 o(m+n)，其中m和n分别是链表headA和headB的长度
     * 空间复杂度 o(m)，其中m是链表headA的长度，需要用哈希集合存储链表headA的全部节点
     */
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*Set<ListNode> set = new HashSet<>();
        // 把A链表中的所有节点加到set中
        ListNode iterNode = headA;
        while (iterNode != null) {
            set.add(iterNode);
            iterNode = iterNode.next;
        }

        // 依次判断B链表中是否有A链表中的元素，找到第一个，就直接返回
        ListNode bIterNode = headB;
        while (bIterNode != null) {
            if (set.contains(bIterNode)) {
                return bIterNode;
            }
            bIterNode = bIterNode.next;
        }
        return null;*/

        // leetcode官方提供的写法，和上面其实是一样的
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}
