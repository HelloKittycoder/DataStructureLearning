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

        bst.inOrder();
        System.out.println();

        bst.postOrder();
        System.out.println();
        // System.out.println(bst);
    }

    @Test
    public void test2() {
        BST<Integer> bst = new BST<>();
        int[] nums = {66, 33, 77, 22, 44, 70, 88, 11, 40};
        for (int num : nums) {
            bst.add(num);
        }

        bst.preOrder();
        System.out.println();

        bst.inOrder();
        System.out.println();

        bst.postOrder();
        System.out.println();
    }
}
