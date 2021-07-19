package com.kittycoder.algorithm.greedy;

import java.util.*;

/**
 * 贪心算法解决集合覆盖问题（课程里的实现）
 * Created by shucheng on 2021/7/19 8:57
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        // 创建广播电台，放入Map
        Map<String, Set<String>> broadcasts = new LinkedHashMap<>();
        // 创建一个hashSet，一个个加进去也ok，下面直接使用ArrayList批量添加
        Set<String> hashSet1 = new HashSet<>(Arrays.asList("北京", "上海", "天津"));
        Set<String> hashSet2 = new HashSet<>(Arrays.asList("广州", "北京", "深圳"));
        Set<String> hashSet3 = new HashSet<>(Arrays.asList("成都", "上海", "杭州"));
        Set<String> hashSet4 = new HashSet<>(Arrays.asList("上海", "天津"));
        Set<String> hashSet5 = new HashSet<>(Arrays.asList("杭州", "大连"));
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // allAreas 存放所有的地区
        Set<String> allAreas = new HashSet<>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        // 创建ArrayList，存放选择的广播台集合
        List<String> selects = new ArrayList<>();
        String maxKey;
        int maxNum = 0;

        while (allAreas.size() > 0) {
            // maxKey = null也要记得写
            maxKey = null;
            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                String key = entry.getKey();
                Set<String> areas = entry.getValue();

                // 计算每个广播电台覆盖地区与所有地区的交集部分
                /**
                 * lsc说明：课程里的tempSet，每次都会clear，我这里是每次都重新声明，这个和我这里的写法本质上是一样的
                 */
                Set<String> tempSet = new HashSet<>(areas);
                tempSet.retainAll(allAreas);

                // tempSet.size() > 0 是必须的
                /**
                 * lsc说明：课程里写的是tempSet.size() > broadcasts.get(maxKey).size()，我这里引入了变量maxNum，记录上一次得到的覆盖数
                 * 因为我认为课程里的写法有问题：broadcasts.get(maxKey).size()取的并不是公共元素，tempSet取的是公共元素，这两者其实是不能比较的（有时间可以举个例子）
                 */
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxNum)) {
                    maxKey = key;
                    maxNum = tempSet.size();
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                // 从allAreas中移除已经覆盖过的地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        // 打印最终的选择情况 [K1,K2,K3,K5]
        System.out.println(selects);
    }
}
