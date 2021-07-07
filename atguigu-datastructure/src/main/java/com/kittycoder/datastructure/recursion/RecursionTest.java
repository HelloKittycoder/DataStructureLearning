package com.kittycoder.datastructure.recursion;

import org.junit.Test;

/**
 * Created by shucheng on 2020/1/5 21:45
 * 递归测试
 */
public class RecursionTest {

    @Test
    public void test1() {
        // test(4, 0);
        System.out.println(factorial(3));
    }

    // 原本的test递归方法
    // 注意加else和不加else的区别
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } // else {
            System.out.println("n=" + n);
        // }
    }

    // 测试test递归方法，加了generateDepthString
    public static void test(int n, int depth) {
        System.out.println(RecursionUtil.generateDepthString(depth));
        if (n > 2) {
            test(n - 1, depth + 1);
        }
        System.out.println("n=" + n);
        System.out.println(RecursionUtil.generateDepthString(depth));
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
