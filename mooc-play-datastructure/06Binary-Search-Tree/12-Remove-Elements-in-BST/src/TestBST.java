import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shucheng on 2019-6-8 下午 14:26
 */
public class TestBST {

    // 打乱数组顺序
    private static void shuffle(Object[] arr) {

        // 将位置为随机数字[0,i]区间上的一个数字的元素，和位置为i的元素进行调换
        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = (int) (Math.random() * (i+1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }

    @Test
    public void test1() {
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        for (int i = 0; i < n; i ++)
            bst.add(random.nextInt(n));

        // 注意，由于随机生成的数据有重复，所以bst中的数据数量大概率是小于n的

        // order数组中存放[0...n)的所有元素
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++)
            order[i] = i;
        // 打乱order数组的顺序
        shuffle(order);

        // 乱序删除[0...n)范围里的所有元素
        for (int i = 0; i < n; i++)
            if (bst.contains(order[i])) {
                bst.remove(order[i]);
                System.out.println("After remove " + order[i] + ",size = " + bst.size());
            }

        // 最终整个二分搜索树应该为空
        System.out.println(bst.size());
    }
}
