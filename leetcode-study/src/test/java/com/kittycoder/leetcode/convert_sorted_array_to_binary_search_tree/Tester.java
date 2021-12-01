/**
 * Leetcode - valid_parentheses
 */
package com.kittycoder.leetcode.convert_sorted_array_to_binary_search_tree;

import com.kittycoder.leetcode.util.TreeNode;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== static for every test cases ============================== */

    // Solution instance to test
    private static Solution solution;
    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(Tester.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
         solution = new Solution1();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        /**
         * 用Solution1构建出来的平衡二叉树
         *      0
         *     / \
         *  -10  5
         *    \   \
         *   -3   9
         */
        int[] t = {-10,-3,0,5,9};

        return Arrays.asList(new Object[][]{
            {t, "-10,-3,0,5,9,"},     // test case 1 (init parameters below: {para1, para2, expected})
            // {t2, 3},     // test case 2 (init parameters below: {para1, para2, expected})
            // {t3, 3},      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] para1;                       // parameter 1
    // private Object para2;                    // parameter 2
    private String expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] para1, /*Object para2, */String expected) {
           // initialize test parameters
        this.para1 = para1;
        // this.para2 = para2;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        //
        TreeNode actual = solution.sortedArrayToBST(para1);
        // 比较构造出来的二叉树的中序遍历结果是否和预想的一致
        assertThat(actual.inOrderStr(), is(equalTo(expected)));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("your-method() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
