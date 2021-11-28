/**
 * Leetcode - valid_parentheses
 */
package com.kittycoder.leetcode.symmetric_tree;

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
        // solution = new Solution1();
        solution = new Solution2();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        /**
         *      1
         */
        TreeNode t1 = new TreeNode(1);

        /**
         *      1
         *    / \
         *   2   2
         *  / \ / \
         * 3  4 4  3
         */
        TreeNode t2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3), new TreeNode(4)),
                new TreeNode(2,
                        new TreeNode(4), new TreeNode(3))
        );

        /**
         *      1
         *    / \
         *   2   2
         *    \   \
         *    3    3
         */
        TreeNode t3 = new TreeNode(1,
                new TreeNode(2,
                        null, new TreeNode(3)),
                new TreeNode(2,
                        null, new TreeNode(3))
        );

        return Arrays.asList(new Object[][]{
            {t1, true},     // test case 1 (init parameters below: {para1, para2, expected})
            {t2, true},     // test case 2 (init parameters below: {para1, para2, expected})
            {t3, false},      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode para1;                       // parameter 1
    // private Object para2;                    // parameter 2
    private boolean expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode para1, /*Object para2, */boolean expected) {
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
        boolean actual = solution.isSymmetric(para1);
        //
        assertThat(actual, is(equalTo(expected)));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("your-method() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
