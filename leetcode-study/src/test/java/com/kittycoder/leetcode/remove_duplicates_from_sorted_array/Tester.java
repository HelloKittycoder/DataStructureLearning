/**
 * Leetcode - remove_duplicates_from_sorted_array
 */
package com.kittycoder.leetcode.remove_duplicates_from_sorted_array;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

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
        return Arrays.asList(new Object[][]{
            {new int[]{0,0,1,1,1,2,2}, new int[]{0,1,2}},     // test case 1 (init parameters below: {para1, para2, expected})
            {new int[]{1,1,2}, new int[]{1,2}},     // test case 2 (init parameters below: {para1, para2, expected})
            {new int[]{0,0,1,1,1,2,2,3,3,4}, new int[]{0,1,2,3,4}}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] para1;                       // parameter 1
    // private ListNode para2;                       // parameter 2
    private int[] expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] para1, /*ListNode para2, */int[] expected) {
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
        int actual = solution.removeDuplicates(para1);
        //
        assertTrue(checkHasRemoveDuplicates(para1, actual, expected));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("your-method() pass unit test!");
        }
    }

    private boolean checkHasRemoveDuplicates(int[] para1, int actual, int[] expected) {
        if (actual != expected.length) {
            return false;
        }

        boolean result = true;
        for (int i = 0; i < actual; i++) {
            if (expected[i] != para1[i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
