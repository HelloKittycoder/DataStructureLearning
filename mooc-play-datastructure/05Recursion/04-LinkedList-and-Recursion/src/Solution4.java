/// Leetcode 203. Remove Linked List Elements
/// https://leetcode.com/problems/remove-linked-list-elements/description/

// 使用递归方式来解决
class Solution4 {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null)
            return null;

        ListNode res = removeElements(head.next, val);
        if (head.val == val)
            // 如果head中的val是要被删除的值，就不在结果中拼接了
            return res;
        else {
            // 如果head中的val不是要被删除的值，就拼接到结果中
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution4()).removeElements(head, 6);
        System.out.println(res);
    }
}
