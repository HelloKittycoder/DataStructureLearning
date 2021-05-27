package com.kittycoder.util;

import org.apache.commons.lang3.ArrayUtils;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by shucheng on 2020/1/20 22:03
 * 打印带颜色的字符串
 */
public class ColorStringUtil {

    /**
     * 打印数组，并将从索引fromIndex开始的元素打印成红色加粗的
     * 使用示例：fromIndexColorString(a, 4);
     *          fromIndexColorString(a, -1);
     * @param arr
     * @param fromIndex
     */
    public static String fromIndexColorString(int[] arr, int fromIndex) {
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

    /**
     * 打印数组，并将从索引0直到索引为toIndex的元素打印成红色加粗的
     * 使用示例：toIndexColorString(a, 4);
     *          toIndexColorString(a, -1);
     * @param arr
     * @param toIndex
     */
    public static String toIndexColorString(int[] arr, int toIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            // 追加颜色的条件：假设有5个元素，比如2表示，索引为[0,2]的所有元素都使用red,bold
            // -1表示，索引为[0,5-1]即[0,4]的所有元素都使用red,bold
            boolean isAppendColor = (toIndex >= 0 && i <= toIndex) ||
                    (toIndex < 0 && i <= length + toIndex);
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

    /**
     * 打印数组，让指定索引的元素打印成红色加粗的
     * @param arr
     * @param index 可以指定多个索引位置
     * @return
     */
    public static String indexColorString(int[] arr, int... index) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (ArrayUtils.contains(index, i)) {
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
