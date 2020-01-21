package com.kittycoder.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/20 22:20
 * 排序算法性能测试
 */
public class SpeedTest {

    // 测试冒泡排序
    @Test
    public void testBubbleSort() {
        testSort(new SortCallback("bubble") {
            @Override
            public void invoke(Object o) {
                BubbleSort.sort2((int[]) o);
            }
        });
    }

    // 简单测试下jdk的快速排序算法
    @Test
    public void testJdkPivotQuickSort() {
        testSort(new SortCallback("jdkPivotQuickSort") {
            @Override
            public void invoke(Object o) {
                Arrays.sort((int[]) o);
            }
        });
    }

    // 测试选择排序
    @Test
    public void testSelectSort() {
        testSort(new SortCallback("select") {
            @Override
            public void invoke(Object o) {
                SelectSort.sort((int[]) o);
            }
        });
    }

    // 测试插入排序
    @Test
    public void testInsertSort() {
        testSort(new SortCallback("insert") {
            @Override
            public void invoke(Object o) {
                InsertSort.sort2((int[]) o);
            }
        });
    }

    // 测试希尔排序（移位法比优化后的交换法稍快，速度相差不大；如果交换法不做优化，速度差异很大）
    @Test
    public void testShellSort() {
        // 交换法
        testSort(new SortCallback("swapShell") {
            @Override
            public void invoke(Object o) {
                ShellSort.shellSort2((int[]) o);
            }
        });

        // 移位法
        testSort(new SortCallback("moveShell") {
            @Override
            public void invoke(Object o) {
                ShellSort.shellSort3((int[]) o);
            }
        });
    }

    public int[] generateArray() {
        // 测试一下冒泡排序的速度
        // 生成一个80000个元素的随机数组，每个元素的取值范围为[1,80000]

        /**
         * 举例：生成[1,100]的随机整数，Math.random()*100+1
         * 推算过程：
         * [1,100]-1=[0,99]=[0,100)=[0,1)*100
         * =>[1,100]=[0,1)*100+1
         */
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000 + 1);
        }
        return arr;
    }

    // 模板测试方法
    public void testSort(SortCallback callback) {
        int[] arr = generateArray();
        long start = System.currentTimeMillis();
        // 这里调用具体的排序算法
        // sort2(arr);
        callback.invoke(arr); // 此时注释掉方法内部的打印语句
        long end = System.currentTimeMillis();
        System.out.printf("%s排序所需时间为：%d%s\n", callback.getSortName(),
                callback.getDuration(start, end), callback.getUnit());
    }

    public abstract class SortCallback {

        private String sortName = ""; // 排序算法名称
        private String unit = "ms"; // 计时单位

        public SortCallback() {
        }

        public SortCallback(String sortName) {
            this.sortName = sortName;
        }

        public SortCallback(String sortName, String unit) {
            this.sortName = sortName;
            this.unit = unit;
        }

        public String getSortName() {
            return sortName;
        }

        public String getUnit() {
            return unit;
        }

        // 返回用时（s或者ms，默认是ms）
        public long getDuration(long start, long end) {
            return "ms".equals(unit) ? (end - start) : (end - start) / 1000;
        }

        abstract void invoke(Object o);
    }
}
