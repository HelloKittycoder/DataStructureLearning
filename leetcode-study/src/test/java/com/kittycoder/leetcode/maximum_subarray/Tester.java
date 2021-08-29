/**
 * Leetcode - maximum_subarray
 */
package com.kittycoder.leetcode.maximum_subarray;

import com.kittycoder.leetcode.util.FileUtil;
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
        // solution = new Solution1(); // 134376 ms
        // solution = new Solution2(); // 34ms
        // solution = new Solution3(); // 1ms
        // solution = new Solution4(); // 2ms
        solution = new Solution5(); // 0ms
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        int[] largeArray = prepareLargeArray();
        return Arrays.asList(new Object[][]{
            {null, 0},     // test case 1 (init parameters below: {para1, para2, expected})
            {new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6},     // test case 2 (init parameters below: {para1, para2, expected})
            {new int[]{1}, 1},      // test case 3 (init parameters below: {para1, para2, expected})
            {new int[]{0}, 0},
            {new int[]{1,2,3,4}, 10},
            {new int[]{-10000}, -10000},
            {largeArray, 11081}
        });
    }

    private static int[] prepareLargeArray() {
        String s = FileUtil.loadFiletoString("largeArray.txt");
        String[] arr = s.split(",");
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = Integer.parseInt(arr[i]);
        }
        return newArr;
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] para1;                       // parameter 1
    // private Object para2;                       // parameter 2
    private int expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] para1, /*ListNode para2, */int expected) {
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
        long start = System.currentTimeMillis();
        int actual = solution.maxSubArray(para1);
        LOGGER.info("elased {} ms", System.currentTimeMillis() - start);
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
