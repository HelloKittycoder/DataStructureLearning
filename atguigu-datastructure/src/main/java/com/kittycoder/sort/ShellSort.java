package com.kittycoder.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/20 17:31
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 8, 3, 1, 2, 4, 6};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testShellSort() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000 + 1);
        }

        long start = System.currentTimeMillis();
        shellSort(arr); // 此时注释掉方法内部的打印语句
        long end = System.currentTimeMillis();
        System.out.printf("排序所需时间为：%ds\n", (end - start));
    }

    // 希尔排序（参考https://blog.csdn.net/qq_39207948/article/details/80006224后，对插入排序稍作改进写的）
    // 这个用的就是移位法
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            insertSort(arr, gap);
        }
    }

    // 插入排序（从索引k开始找）
    public static void insertSort(int[] arr, int gap) {
        int insertValue, insertIndex;
        for (int i = gap; i <= arr.length - 1; i++) {
            // 定义待插入的数
            insertValue = arr[i];
            insertIndex = i - gap; // 即arr[i]的前面这个数的下标

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                /*System.out.printf("比较(insertValue)arr[%d]=%d和arr[%d]=%d\n",
                        i, insertValue, insertIndex, arr[insertIndex]);*/
                arr[insertIndex + gap] = arr[insertIndex];
                insertIndex = insertIndex - gap;
            }
            // (index+1)就是要操作的位置
            arr[insertIndex + gap] = insertValue;
            // System.out.printf("第%d轮排序后的结果：%s\n\n", i, Arrays.toString(arr));
        }
    }

    @Test
    public void testShellShort2() {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // int[] arr = {5, 1, 9, 3, 7, 4, 8, 6, 2};
        shellSort2(arr);
    }

    @Test
    public void testSwapElements() {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        swapElements(arr, 1);
        System.out.println(Arrays.toString(arr));
    }

    // 采用交换法（课程里的写法）
    public static void shellSort2(int[] arr) {

        // int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // count++;
            // 希尔排序的第1轮排序
            // System.out.printf("希尔排序%d轮开始：gap=%d\n", count, gap);
            swapElements(arr, gap);
            // System.out.printf("希尔排序%d轮后：%s\n\n", count, Arrays.toString(arr));
        }

        /*int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            count++;
            // 希尔排序的第1轮排序
            System.out.printf("希尔排序%d轮开始：gap=%d\n", count, gap);
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中的所有的元素（共5组，每组有两个元素），步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于步长后的那个元素，说明交换
                    System.out.printf("i=%d, j=%d, arr[j]=arr[%d], arr[j+gap]=arr[%d], 数组：%s\n", i, j,
                            j, j + gap, ColorStringUtil.indexColorString(arr, j, j + gap));
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.printf("希尔排序%d轮后：%s\n\n", count, Arrays.toString(arr));
        }*/

        /*int temp = 0;
        // 希尔排序的第1轮排序
        System.out.println("希尔排序1轮开始：");
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中的所有的元素（共5组，每组有两个元素），步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于步长后的那个元素，说明交换
                System.out.printf("数组：%s\n", ColorStringUtil.indexColorString(arr, j, j + 5));
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.printf("希尔排序1轮后：%s\n\n", Arrays.toString(arr));

        // 希尔排序的第2轮排序
        // 因为第2轮排序，是将10个数据分成了5/2=2组
        System.out.println("希尔排序2轮开始：");
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素
            for (int j = i - 2; j >= 0; j -= 2) {
                System.out.printf("i=%d, j=%d, arr[%d], arr[%d], 数组：%s\n", i, j,
                        j, j + 2, ColorStringUtil.indexColorString(arr, j, j + 2));
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.printf("希尔排序2轮后：%s\n\n", Arrays.toString(arr));

        // 希尔排序的第3轮排序
        // 因为第2轮排序，是将10个数据分成了2/2=1组
        System.out.println("希尔排序3轮开始：");
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素
            for (int j = i - 1; j >= 0; j -= 2) {
                System.out.printf("数组：%s\n", ColorStringUtil.indexColorString(arr, j, j + 1));
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.printf("希尔排序3轮后：%s\n\n", Arrays.toString(arr));*/
    }

    // 优化：减少比较次数（对课程的写法做了优化，加了一个flag）
    public static void swapElements(int[] arr, int gap) {
        int temp;
        boolean flag;
        for (int i = gap; i < arr.length; i++) {
            flag = true;
            // 遍历各组中的所有的元素（共5组，每组有两个元素），步长5
            for (int j = i - gap; j >= 0; j -= gap) {
                // 如果当前元素大于步长后的那个元素，说明交换
                /*System.out.printf("i=%d, j=%d, arr[j]=arr[%d], arr[j+gap]=arr[%d], 数组：%s\n", i, j,
                        j, j + gap, ColorStringUtil.indexColorString(arr, j, j + gap));*/
                if (arr[j] > arr[j + gap]) {
                    temp = arr[j];
                    arr[j] = arr[j + gap];
                    arr[j + gap] = temp;
                } else {
                    flag = false;
                    // System.out.println("不需要交换");
                }

                if (!flag) {
                    break;
                } else {
                    flag = true;
                }
            }
        }
    }

    // 使用移位法对希尔排序进行优化（课程里的写法，这个和shellSort其实是一样的）
    public static void shellSort3(int[] arr) {
        // 增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    // 移动
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // 当退出while后，就给temp找到插入的位置
                arr[j] = temp;
            }
        }
    }
}
