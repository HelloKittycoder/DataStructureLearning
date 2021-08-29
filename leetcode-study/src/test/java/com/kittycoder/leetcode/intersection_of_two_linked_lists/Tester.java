/**
 * Leetcode - intersection_of_two_linked_lists
 */
package com.kittycoder.leetcode.intersection_of_two_linked_lists;

import com.kittycoder.leetcode.util.ListNode;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
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
        ListNode[] testCase1 = buildIntersectionListNode(new int[]{4, 1}, new int[]{5, 0, 1}, new int[]{8, 4, 5});
        ListNode[] testCase2 = buildIntersectionListNode(new int[]{0, 9, 1}, new int[]{3}, new int[]{2, 4});
        ListNode[] testCase3 = buildIntersectionListNode(new int[]{2, 6, 4}, new int[]{1, 5}, null);

        return Arrays.asList(new Object[][]{
                {testCase1[0], testCase1[1], ListNode.buildListNode(8, 4, 5)},     // test case 1 (init parameters below: {para1, para2, expected})
                {testCase2[0], testCase2[1], ListNode.buildListNode(2, 4)},     // test case 2 (init parameters below: {para1, para2, expected})
                {testCase3[0], testCase3[1], null}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    // 构建存在相交部分的两个链表
    private static ListNode[] buildIntersectionListNode(int[] part1, int[] part2, int[] commonPart) {
        ListNode listNode1 = ListNode.buildListNode(part1);
        ListNode listNode2 = ListNode.buildListNode(part2);
        if (commonPart != null) {
            ListNode commonPartListNode = ListNode.buildListNode(commonPart);
            findLastListNode(listNode1).next = commonPartListNode;
            findLastListNode(listNode2).next = commonPartListNode;
        }
        return new ListNode[]{listNode1, listNode2};
    }

    // 找到链表的倒数第1个节点
    private static ListNode findLastListNode(ListNode head) {
        ListNode iterNode = head;
        if (iterNode != null) {
            while (iterNode != null && iterNode.next != null) {
                iterNode = iterNode.next;
            }
        }
        return iterNode;
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private ListNode para1;                       // parameter 1
    private ListNode para2;                       // parameter 2
    private ListNode expected;                  // parameter 3 (expected answer)

    /** This constructor must be provided to run parameterized test. */
    public Tester(ListNode para1, ListNode para2, ListNode expected) {
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
        ListNode actual = solution.getIntersectionNode(para1, para2);
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
