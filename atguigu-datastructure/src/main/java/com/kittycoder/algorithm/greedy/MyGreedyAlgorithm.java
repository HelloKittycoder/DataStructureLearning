package com.kittycoder.algorithm.greedy;

import java.util.*;

/**
 * 贪心算法解决集合覆盖问题（结合课程思路自己写的）
 * Created by shucheng on 2021/7/18 21:52
 */
public class MyGreedyAlgorithm {

    public static void main(String[] args) {
        List<String> k1 = Arrays.asList("北京", "上海", "天津");
        List<String> k2 = Arrays.asList("广州", "北京", "深圳");
        List<String> k3 = Arrays.asList("成都", "上海", "杭州");
        List<String> k4 = Arrays.asList("上海", "天津");
        List<String> k5 = Arrays.asList("杭州", "大连");
        List<List<String>> list = new ArrayList<>();
        list.add(k1);
        list.add(k2);
        list.add(k3);
        list.add(k4);
        list.add(k5);

        Set<String> allArea = new HashSet<>();
        allArea.addAll(k1);
        allArea.addAll(k2);
        allArea.addAll(k3);
        allArea.addAll(k4);
        allArea.addAll(k5);

        List<String> selected = new ArrayList<>();

        greedyCalculate(list, allArea, selected);
        System.out.println(selected);
    }

    // 课程里的写法更简单（直接写第2、3步了）
    public static void greedyCalculate(List<List<String>> list, Set<String> allArea, List<String> selected) {
        while (allArea.size() > 0) {
            // 1.统计每个站点的覆盖情况
            Map<String, Integer> map = new HashMap<>();
            for (int i = 1; i <= 5; i++) {
                List<String> tempList = list.get(i - 1);
                int overrideNum = 0;
                for (String str : tempList) {
                    if (allArea.contains(str)) {
                        overrideNum++;
                    }
                }
                map.put("k" + i, overrideNum);
            }

            // 2.找出覆盖数最大的站点maxKey，并存放到list中
            int maxOverrideNum = 0;
            String maxKey = "";
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (value > maxOverrideNum) {
                    maxOverrideNum = value;
                    maxKey = key;
                }
            }

            selected.add(maxKey);

            // 3.从allArea中移除maxKey覆盖的站点
            int index = Integer.parseInt(maxKey.split("k")[1]) - 1;
            List<String> needRemoveElements = list.get(index);
            for (String str : needRemoveElements) {
                allArea.remove(str);
            }
        }
    }
}
