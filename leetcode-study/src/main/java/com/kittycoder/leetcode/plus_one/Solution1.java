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
public class Solution1 implements Solution {

    @Override
    public int[] plusOne(int[] digits) {
        /*int len = digits.length;
        // 如果最后1位加1不会产生进位，则直接做加法即可
        if (digits[len - 1] < 9) {
            digits[len - 1]++;
            return digits;
        } else {
            // flag为true，表示需要做进位操作
            digits[len - 1] = 0;
            boolean flag = true;
            int i = len - 2;

            // 从倒数第2位开始操作
            while (flag && i >= 0) {
                if (digits[i] < 9) {
                    // 如果中途遇到一个数字小于9，则对该数字进行自增，并且后续没有进位操作
                    digits[i]++;
                    flag = false;
                } else {
                    // 如果中途遇到一个数字等于9，则对该数字置为0，后续还可能有进位操作
                    digits[i] = 0;
                }
                i--;
            }

            // 此时flag为true，表示目前所有位都进位了，但是还缺了最高位
            if (flag) {
                int[] resultArr = new int[len + 1];
                resultArr[0] = 1;
                return resultArr;
            }
        }
        return digits;*/

        // 上面的代码可以优化成如下形式：
        // https://leetcode-cn.com/problems/plus-one/solution/java-shu-xue-jie-ti-by-yhhzw/461904
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }

        }
        //如果所有位都是进位，则长度+1
        digits= new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
