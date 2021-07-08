package com.kittycoder.algorithm.dac;

import java.util.ArrayList;
import java.util.List;

/**
 * 汉诺塔问题（自己想的求解方案，同样实现了功能，不过有点麻烦）
 * Created by shucheng on 2021/7/6 23:15
 */
public class MyHanoitower {

    private int[] arr;
    private List<String> strList;
    private int no;

    public static void main(String[] args) {
        MyHanoitower hanoni = new MyHanoitower(3);
        /*System.out.println(Arrays.toString(hanoni.arr)); // 3,0,0
        hanoni.move(0, 2);
        System.out.println(Arrays.toString(hanoni.arr)); // 2,0,1*/
        hanoni.moveAll(0, 2);
    }

    public MyHanoitower(int n) {
        arr = new int[3];
        arr[0] = n;
        strList = new ArrayList<>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
    }

    /**
     * 将元素从srcIndex移动到destIndex
     * 这里srcIndex其实只能是0，因为这里用了数组，且初始化只向arr[0]里放了元素
     * @param srcIndex
     * @param destIndex
     */
    public void moveAll(int srcIndex, int destIndex) {
        moveAll(arr[0], srcIndex, destIndex);
    }

    // 从srcIndex移动num个元素到destIndex
    public void moveAll(int num, int srcIndex, int destIndex) {
        if (num >= 2) {
            moveAll(num - 1, srcIndex, getTempIndex(srcIndex, destIndex));
            move(srcIndex, destIndex);
            moveAll(num - 1, getTempIndex(srcIndex, destIndex), destIndex);
        } else {
            move(srcIndex, destIndex);
        }
    }

    // 获取要临时移动到的位置
    private int getTempIndex(int srcIndex, int destIndex) {
        for (int i = 0; i <= 2; i++) {
            if (i != srcIndex && i != destIndex) {
                return i;
            }
        }
        return -1;
    }

    // 从srcIndex向destIndex移动一个元素
    public void move(int srcIndex, int destIndex) {
        System.out.println((++no) + " " + strList.get(srcIndex) + "-->" + strList.get(destIndex));
        arr[srcIndex] = --arr[srcIndex];
        arr[destIndex] = ++arr[destIndex];
    }
}
