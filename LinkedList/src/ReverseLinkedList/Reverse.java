package ReverseLinkedList;

class ListNode {

	public int value;
	public ListNode next;

	public ListNode(int value) {

		this.value = value;

	}

}

public class Reverse {

	public static void main(String args[]) {

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = null;

		Reverse rvs = new Reverse();
		ListNode newHead = rvs.reverse(l1);
		ListNode curr = newHead;
		while (curr != null) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}

	}

	// Time complexity : O(n) Space complexity : O(1)
	// current.next 可以为null， current.next.next 可能会报npe
	public ListNode reverse(ListNode head) {

		// corner case
		if (head == null || head.next == null)
			return head;

		ListNode prev = null;
		ListNode curr = head;

		while (curr != null) {

			ListNode next = curr.next;// 先把curr.next 存起，不然下一行指向改了就找不到了。

			curr.next = prev;// 改指向

			prev = curr;// 前进
			curr = next;// 前进

		}

		return prev;

	}

}
