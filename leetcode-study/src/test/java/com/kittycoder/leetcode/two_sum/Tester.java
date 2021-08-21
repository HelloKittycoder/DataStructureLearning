/**
 * Leetcode - two_sum
 */
package com.kittycoder.leetcode.two_sum;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.kittycoder.leetcode.util.ArrayUtil;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        int[] a = new int[100000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        int[] b = {99998, 99999};

        return Arrays.asList(new Object[][]{
            {new int[]{2,7,11,15}, 9, new int[]{0,1}},     // test case 1 (init parameters below: {para1, para2, expected})
            {new int[]{3,2,4}, 6, new int[]{1,2}},     // test case 2 (init parameters below: {para1, para2, expected})
            {a, 99998 + 99999, b}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] para1;                       // parameter 1
    private int para2;                       // parameter 2
    private int[] expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] para1, int para2, int[] expected) {
           // initialize test parameters
        this.para1 = para1;
        this.para2 = para2;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        //
        long start = System.currentTimeMillis();
        int[] actual = solution.twoSum(para1, para2);
        LOGGER.info("elased {} ms", System.currentTimeMillis() - start);
        //
        assertTrue(ArrayUtil.contentEquals(actual, expected));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("your-method() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
