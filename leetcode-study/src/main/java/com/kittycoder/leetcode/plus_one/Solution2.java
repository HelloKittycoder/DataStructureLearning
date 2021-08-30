/**
 * Leetcode - plus_one
 */
package com.kittycoder.leetcode.plus_one;

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

    @Override
    public int[] plusOne(int[] digits) {
        // https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/easy/66.plus-one
        /*int len = digits.length;
        int carry = 1;
        int sum;
        for (int i = len - 1; i > -1; i--) {
            if (carry == 1) {
                sum = digits[i] + carry;
                digits[i] = sum % 10;
                carry = sum > 9 ? 1 : 0;
            }
        }
        if (carry == 1) {
            int[] resultArr = new int[len + 1];
            resultArr[0] = carry;
            return resultArr;
        }
        return digits;*/

        // 上面的代码可以优化成：（这里直接利用digits[i]!=0来判断是否发生进位，很巧妙）
        // https://leetcode-cn.com/problems/plus-one/solution/java-shu-xue-jie-ti-by-yhhzw/
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
