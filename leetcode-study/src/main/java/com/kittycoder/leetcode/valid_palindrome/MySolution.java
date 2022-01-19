package com.kittycoder.leetcode.valid_palindrome;

/**
 * 自己想的解法（在原字符串上直接判断）
 * 官方也提供了这种解法，写得非常简洁，见Solution1
 * 时间复杂度：O(|s|)，其中|s|是字符串s的长度
 * 空间复杂度：O(1)
 * Created by shucheng on 2022/1/17 22:32
 */
public class MySolution implements Solution {

    @Override
    public boolean isPalindrome(String s) {
        // 如果是空白字符，直接返回true
        if (isBlank(s)) {
            return true;
        }

        for (int i = 0, j = s.length() - 1; i < s.length() && j > 0;) {
            if (j <= i) {
                break;
            }

            char iChar = s.charAt(i);
            while (!isAlphabetNumber(iChar)) {
                i++;
                if (i > s.length() - 1) {
                    iChar = ' ';
                    break;
                }
                iChar = s.charAt(i);
            }


            char jChar = s.charAt(j);
            while (!isAlphabetNumber(jChar)) {
                j--;
                if (j < 0) {
                    jChar = ' ';
                    break;
                }
                jChar = s.charAt(j);
            }

            String iStr = new String(new char[]{iChar});
            String jStr = new String(new char[]{jChar});
            if (!iStr.equalsIgnoreCase(jStr)) {
                // 遇到1个对不上的，直接返回false
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static boolean isBlank(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphabetNumber(char ch) {
        int i = ch;
        /**
         * char
         * 48-57 数字
         * 65-90 大写字母
         * 97-122 小写字母
         */
        return (i >= 48 && i <= 57) || (i >= 65 && i <= 90) || (i >= 97 && i <= 122);
    }
}
