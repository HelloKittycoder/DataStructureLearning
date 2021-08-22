/**
 * Leetcode - valid_parentheses
 */
package com.kittycoder.leetcode.valid_parentheses;

import java.util.Stack;

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
     * 核心思路是对的
     * 优化点：（1）有些地方可以提前退出
     * （2）右括号和左括号的对应关系可以用map装起来
     * （3）Stack是用数组实现的，正常不扩容的情况下，pop和push没问题，因为操作的是队尾的元素，
     * 不会引发元素的移动；但是要扩容的话，就会有性能问题，建议使用LinkedList
     *
     * @param s
     * @return
     */
    @Override
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                Character peek = stack.peek();
                if ((c == ')' && peek == '(')
                        || (c == ']' && peek == '[')
                        || (c == '}' && peek == '{')) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
