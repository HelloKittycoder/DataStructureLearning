package com.kittycoder.leetcode.valid_palindrome;

/**
 * Created by shucheng on 2022/1/18 22:40
 */
public class Solution1 implements Solution {

    /**
     * 解法1：筛选+判断
     * 官方提供了两种写法：筛选都是一样的，主要是判断部分
     * 一种是直接利用java自带的api，另一种是利用双指针
     *
     * 无论用哪种，算法复杂度都是：
     * 时间复杂度：O(|s|)，其中|s|是字符串s的长度
     * 空间复杂度：O(|s|)，其中|s|是字符串s的长度
     * @param s
     * @return
     */
    @Override
    public boolean isPalindrome(String s) {
        // 写法1：
        /*StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }

        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());*/

        // 写法2：
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }

        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (sgood.charAt(left) != sgood.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
