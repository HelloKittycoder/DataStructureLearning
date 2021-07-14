package com.kittycoder.algorithm.kmp;

/**
 * 暴力匹配算法解决字符串匹配问题
 * Created by shucheng on 2021/7/14 9:04
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        // str2->尚硅谷你尚硅你，index->15；str2->尚硅谷你尚硅你~，index->-1
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i索引指向s1
        int j = 0; // j索引指向s2
        while (i < s1Len && j < s2Len) { // 保证匹配时，不越界
            // System.out.printf("i=%d j=%d；", i, j);
            if (s1[i] == s2[j]) { // 匹配成功
                i++;
                j++;
            } else { // 匹配失败
                //如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。
                // System.out.println("重置下标");
                i -= j - 1;
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
