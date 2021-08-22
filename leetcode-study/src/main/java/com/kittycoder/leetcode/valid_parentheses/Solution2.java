/**
 * Leetcode - valid_parentheses
 */
package com.kittycoder.leetcode.valid_parentheses;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    // 引入了栈数据结构 时间复杂度o(n)，n是字符串s的长度
    @Override
    public boolean isValid(String s) {
        // 字符串长度为奇数，则直接退出
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> unordered_map = new HashMap<>();
        unordered_map.put(')', '(');
        unordered_map.put(']', '[');
        unordered_map.put('}', '{');

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (unordered_map.containsKey(c)) {
                /**
                 * 如果是右括号
                 * （1）此时栈为空
                 * （2）当前的右括号和栈顶的左括号不匹配
                 * 前两种情况直接退出方法，返回false
                 *
                 * （3）栈不为空，且当前右括号和栈顶的左括号匹配
                 * 则移除栈顶的元素
                 */
                if (stack.isEmpty() || !stack.peek().equals(unordered_map.get(c))) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                // 如果是左括号，则直接放到栈里
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
