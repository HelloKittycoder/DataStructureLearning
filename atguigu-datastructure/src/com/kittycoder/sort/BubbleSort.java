package com.kittycoder.sort;

import java.util.Arrays;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by shucheng on 2020/1/8 21:24
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        // 不会提前退出冒泡排序的情况
        /*int[] a = {3, 9, -1, 10, -2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));*/

        // 会提前退出冒泡排序的情况
        // int[] a = {3, 9, -1, 10, 20};
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(a));
        sort2(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 根据课程思路写的冒泡排序
     * 说明：这里不返回数组的原因是，数组本身是引用类型；而调用该方法的地方肯定持有该数组的引用，
     * 所以没必要返回数组
     */
    public static void sort(int[] arr) {
        int temp;
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            // length - 1 - i，这里减去1就是把上一趟得到的最大数排除掉，那个数不需要再参与排序了
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.printf("i=%d j=%d 排序结果：%s\n", i, j,
                        Arrays.toString(arr));
            }
            /*System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, Arrays.toString(arr));*/
            System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, colorString(arr, length - 1 -i));
        }
    }

    // 优化：如果某趟排序中，没有发生一次交换，可以提前结束冒泡排序
    public static void sort2(int[] arr) {
        int temp;
        int length = arr.length;
        boolean flag = false;

        for (int i = 0; i < length - 1; i++) {
            // length - 1 - i，这里减去1就是把上一趟得到的最大数排除掉，那个数不需要再参与排序了
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
                System.out.printf("i=%d j=%d 排序结果：%s\n", i, j,
                        Arrays.toString(arr));
            }
            /*System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, Arrays.toString(arr));*/
            System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, colorString(arr, length - 1 -i));

            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag = false; // 重置flag，进行下次判断
            }
        }
    }

    /**
     * 打印数组，并将从索引fromInde开始的元素打印成红色加粗的
     * 使用示例：colorString(a, 4);
     *          colorString(a, -1);
     * @param arr
     * @param fromIndex
     */
    public static String colorString(int[] arr, int fromIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            // 追加颜色的条件：假设有5个元素，比如2表示，从第3个元素及以后的所有元素都使用red,bold
            // -1表示，从第（5-1）=4个元素及以后的所有元素都使用red,bold
            boolean isAppendColor = (fromIndex >= 0 && i >= fromIndex) ||
                    (fromIndex < 0 && i >= length + fromIndex);
            if (isAppendColor) {
                // 带逗号的来分隔属性的使用规则，官方文档上没写，这里是跟踪源码看出来的
                sb.append("@|red,bold " + arr[i] + "|@");
            } else {
                sb.append(arr[i]);
            }
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        // jansi官方示例：
        // System.out.println( ansi().eraseScreen().render("hello @|red,bold Hello|@ @|green World|@") );
        return ansi().eraseScreen().render(sb.toString()).toString();
    }
}
