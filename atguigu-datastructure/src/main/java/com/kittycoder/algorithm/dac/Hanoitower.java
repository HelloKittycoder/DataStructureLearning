package com.kittycoder.algorithm.dac;

/**
 * 课程里的实现（感觉写的非常简洁）
 * Created by shucheng on 2021/7/8 0:00
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 使用分治算法移动汉诺塔的圆盘
     * @param num 需要移动的圆盘数目
     * @param a 原位置
     * @param b 临时位置
     * @param c 目标位置
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果我们有n>=2的情况，我们总是可以看作是两个盘 1.最下面的一个盘 2.上面的所有盘
            // 1.先把最上面的所有盘 A->B，移动过程中会用到c
            hanoiTower(num - 1, a, c, b);
            // 2.把最下面的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3.把B塔的所有盘 从 B->C，移动过程中用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
