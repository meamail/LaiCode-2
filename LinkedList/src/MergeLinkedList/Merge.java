package MergeLinkedList;

class ListNode {
	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
}

public class Merge {

	public static void main(String args[]) {

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(6);
		l1.next = l3;
		l3.next = l6;
		l6.next = null;

		l2.next = l4;
		l4.next = l5;
		l5.next = null;

		Merge mg = new Merge();
		ListNode newHead = mg.merge(l1, l2);
		ListNode curr = newHead;
		while (curr != null) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
	}

	// Time Complexity : O(min(n1, n2)) Space O(1)
	public ListNode merge(ListNode l1, ListNode l2) {
		// corner case
		if (l1 == null && l2 == null)
			return null;
		if (l1 == null && l2 != null)
			return l2;
		if (l1 != null && l2 == null)
			return l1;

		ListNode curr1 = l1;
		ListNode curr2 = l2;

		// 假头
		ListNode dummyhead = new ListNode(0);
		ListNode curr = dummyhead;

		while (curr1 != null && curr2 != null) {

			// pick curr1
			if (curr1.value <= curr2.value) {
				curr.next = curr1;
				curr1 = curr1.next;
			} else {
				// pick curr2
				curr.next = curr2;
				curr2 = curr2.next;
			}
			curr = curr.next;
		}

		curr.next = curr1 != null ? curr1 : curr2;// 接上l1 or l2的残余尾巴
		// 去掉假头
		return dummyhead.next;
	}
}
