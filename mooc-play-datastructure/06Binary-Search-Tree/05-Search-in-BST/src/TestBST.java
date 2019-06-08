import org.junit.Test;

/**
 * Created by shucheng on 2019-6-8 下午 14:26
 */
public class TestBST {

    @Test
    public void test1() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }

        //////////////////////////
        //          5           //
        //        /  \          //
        //       3    6         //
        //     /  \    \        //
        //    2   4     8       //
        //////////////////////////
        bst.preOrder();
        System.out.println();

        System.out.println(bst);
    }
}
