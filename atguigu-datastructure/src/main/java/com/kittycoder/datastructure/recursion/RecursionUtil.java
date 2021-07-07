package com.kittycoder.datastructure.recursion;

/**
 * Created by shucheng on 2020/1/5 22:04
 */
public class RecursionUtil {

    /**
     * 生成递归调用深度的字符串（从mooc里的玩转数据结构里拿的）
     * @param depth
     * @return
     */
    public static String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return depth + res.toString();
    }
}
