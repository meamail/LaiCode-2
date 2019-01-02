package ReverseLinkedList;

public class ReverseWithRecursion {

	public static void main(String args[]) {

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = null;

		ReverseWithRecursion rvs = new ReverseWithRecursion();
		ListNode newHead = rvs.reverse(l1);
		ListNode curr = newHead;
		while (curr != null) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
	}

	// Time complexity: O(n) Space Complexity: O(n)
	public ListNode reverse(ListNode head) {

		// corner case
		if (head == null || head.next == null)
			return head;
		return standardReverse(head);
		// return reverse(head, null);
	}

	public ListNode reverse(ListNode head, ListNode prev) {
		// base case
		if (head.next == null)
			return head;
		ListNode curr = head;
		ListNode next = head.next;
		// subproblem
		ListNode subHead = reverse(next, curr);
		// recursive rule
		next.next = curr;// 上一个尾部指到current
		curr.next = null;// current指到null（完成本层reverse）
		return subHead;
	}

	// Sample Solution
	public ListNode standardReverse(ListNode head) {

		// base case
		if (head.next == null)
			return head;
		ListNode subHead = reverse(head.next);
		head.next.next = head;// 上一个尾部head.next指到current(head)
		head.next = null; // current指到null（完成本层reverse）
		return subHead;
	}

}
