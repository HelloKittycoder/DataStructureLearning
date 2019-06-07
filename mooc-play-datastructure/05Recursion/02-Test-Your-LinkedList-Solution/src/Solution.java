/// Leetcode 203. Remove Linked List Elements
/// https://leetcode.com/problems/remove-linked-list-elements/description/

class Solution {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        // return head; 这个写法是错的，因为这样写[1] 1返回的是[1]，而实际期望的是[]
        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }
}
