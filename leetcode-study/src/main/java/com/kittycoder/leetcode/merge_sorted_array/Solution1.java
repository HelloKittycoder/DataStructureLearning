/**
 * Leetcode - merge_sorted_array
 */
package com.kittycoder.leetcode.merge_sorted_array;

import java.util.Arrays;

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
     * 自己想的求解思路：
     * 以
     * int[] nums1 = {1,2,3,0,0,0};
     * int m = 3;
     * int[] nums2 = {2,5,6};
     * int n = 3;
     * 为例，说明思路：
     * 主要是以nums2为主来做循环，
     * （1）j=0，nums2取到2，2和nums1中的{1,2,3}（i∈[0,2]）做比较，发现2<=2（此时i=1）
     * 首先将nums1中的{2,3}向后挪1位，变成{1,2,2,3,0,0,0}，然后把nums2中的2放到nums1中i=1的位置，这时nums1变成{1,2,2,3,0,0,0}
     * nums1中的3不需要比较了，因为nums2中的2已经找到要放到nums1中的位置了
     * （2）j=1，nums2取到5，5和nums1中的{3}（i=3）做比较，发现5>3，因为nums2中待放置的第1个数5，比nums2中剩余的所有数都要大，所以这次循环可以直接跳出
     * （3）把nums2中的所有元素放到nums1中，从nums1中剩余的所有数的后一个位置（也就是索引4）开始放置
     *
     * 以
     * int[] nums1 = {4,5,6,0,0,0};
     * int m = 3;
     * int[] nums2 = {1,2,3};
     * int n = 3;
     * 为例，说明思路：
     * 主要是以nums2为主来做循环，
     * （1）j=0，nums2取到1，1和nums1中的{4,5,6}（i∈[0,2]）做比较，发现1<=4（此时i=0）
     * 首先将nums1中的{4,5,6}向后挪1位，变成{4,4,5,6,0,0}，然后把nums2中的1放到nums1中i=0的位置，这时nums1变成{1,4,5,6,0,0}
     * nums1中的5、6不需要比较了，因为nums2中的1已经找到要放到nums1中的位置了
     * （2）j=1，nums2取到2，2和nums1中的{4,5,6}（i∈[1,3]）做比较，发现2<=4（此时i=1）
     * 首先将nums1中的{4,5,6}向后挪1位，变成{1,4,4,5,6,0}，然后把nums2中的1放到nums1中i=0的位置，这时nums1变成{1,2,4,5,6,0}
     * nums1中的5、6不需要比较了，因为nums2中的1已经找到要放到nums1中的位置了
     * （3）j=1，nums2取到3，2和nums1中的{4,5,6}（i∈[1,3]）做比较，发现3<=4（此时i=2）
     * 首先将nums1中的{4,5,6}向后挪1位，变成{1,2,4,4,5,6}，然后把nums2中的1放到nums1中i=2的位置，这时nums1变成{1,2,3,4,5,6}
     * 此时发现nums1中的已有数字已经挪到最边上了，把所有位置都占了，算法直接结束
     */
    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int startPos = 0;
        int endPos = m - 1;
        int j, i;
        boolean hasFinished = false;
        // 循环nums2
        out:
        for (j = 0; j < n; j++) {
            boolean hasHandle = false;
            // 循环nums1
            for (i = startPos; i <= endPos; i++) {
                /**
                 * 思路：nums2中的第j个数字刚好比nums1中的第i个数字小，
                 * 所以把nums1中第i个数字及以后的所有数字向后移动1位，同时把nums2中的第j个数字放到nums1的索引i的位置上
                 */
                if (nums2[j] <= nums1[i]) {
                    hasHandle = true;
                    // nums1中从索引i开始至(endPos-1)的所有元素后移1位
                    for (int k = endPos + 1; k > i; k--) {
                        nums1[k] = nums1[k - 1];
                    }
                    // 把nums2中索引为j的数字放到nums1的索引i处
                    nums1[i] = nums2[j];
                    // 下一次num2应该和num1中的第(i+1)个元素进行比较，但是num1中从索引i开始的元素都向后移动了1位
                    startPos = i + 1;
                    // 因为向后移动了1位，nums1中截止要比较的索引应该是endPos+1
                    endPos = endPos + 1;
                    if (endPos == m + n - 1) {
                        // nums1的位置已经用完了（此时nums2也没元素要放了），此时整个计算可以直接结束，然后返回结果
                        hasFinished = true;
                        break out;
                    } else {
                        // 因为已经找到了nums2中索引j的数字应该放到nums1的索引i的位置上，所以本次for i循环可以结束调
                        break;
                    }
                }/* else {
                    // 不用做什么，只要进入下1次for i循环，继续找使得 nums2[j] <= nums1[i]的那个索引i
                }*/
            }
            // 说明num2的第j个及之后的所有元素都比nums1的第startPos至endPos的所有元素大
            // 这样就直接跳出for j的循环
            if (!hasHandle) {
                break out;
            }
        }

        // 将nums1中的第j个及之后的所有元素，放到nums1中，从索引endPos+1开始放
        while (!hasFinished && j < n) {
            nums1[endPos + 1] = nums2[j];
            endPos++;
            j++;
        }
        /*System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(j + "--" + i);*/
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        /*int[] nums1 = {1};
        int m = 1;
        int[] nums2 = {};
        int n = 0;*/

        /*int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;*/

        /*int[] nums1 = {2, 0};
        int m = 1;
        int[] nums2 = {1};
        int n = 1;*/

        /*int[] nums1 = {4,5,6,0,0,0};
        int m = 3;
        int[] nums2 = {1,2,3};
        int n = 3;*/

        Solution solution = new Solution1();
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
