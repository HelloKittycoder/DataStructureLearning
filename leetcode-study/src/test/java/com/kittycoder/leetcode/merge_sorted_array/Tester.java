/**
 * Leetcode - merge_sorted_array
 */
package com.kittycoder.leetcode.merge_sorted_array;

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
        // solution = new Solution2();
        // solution = new Solution3();
        solution = new Solution4();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3, new int[]{1,2,2,3,5,6}},     // test case 1 (init parameters below: {para1, para2, expected})
                {new int[]{1}, 1, new int[]{}, 0, new int[]{1}},     // test case 2 (init parameters below: {para1, para2, expected})
                {new int[]{0}, 0, new int[]{1}, 1, new int[]{1}},      // test case 3 (init parameters below: {para1, para2, expected})
                {new int[]{2,0}, 1, new int[]{1}, 1, new int[]{1,2}},
                {new int[]{4,5,6,0,0,0}, 3, new int[]{1,2,3}, 3, new int[]{1,2,3,4,5,6}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums1;                       // parameter 1
    private int m;                       // parameter 2
    private int[] nums2;                  // parameter 3
    private int n;                       // parameter 4
    private int[] expected;                  // parameter 5 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] para1, int para2, int[] para3, int para4, int[] expected) {
        // initialize test parameters
        this.nums1 = para1;
        this.m = para2;
        this.nums2 = para3;
        this.n = para4;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        //
        solution.merge(nums1, m, nums2, n);
        //
        assertThat(nums1, is(equalTo(expected)));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("your-method() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
