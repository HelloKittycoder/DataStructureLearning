package com.kittycoder.leetcode.util;

import static com.kittycoder.leetcode.util.FileUtil.loadFiletoString;

/**
 * Created by shucheng on 2021/8/29 10:28
 */
public class FileUtilTest {

    public static void main(String[] args) {
        // 当前是从test/java启动的，既加载了main/resources，也加载了test/resources
        // String s = loadFiletoString("largeArray.txt");
        String s = loadFiletoString("testFile.txt");
        System.out.println(s);
    }
}
