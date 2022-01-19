package com.kittycoder.leetcode.valid_palindrome;

/**
 * Created by shucheng on 2022/1/18 22:44
 */
public class Solution2 implements Solution {

    /**
     * 解法2：在原字符串上直接判断
     *
     * 时间复杂度：O(|s|)，其中|s|是字符串s的长度
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    @Override
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            /**
             * leetcode下面这个判断是为了提升效率用的，可以少做一次下面的操作，并提前结束循环；
             * 如果不加的话，其实也不会影响最终的判断结果
             */
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
