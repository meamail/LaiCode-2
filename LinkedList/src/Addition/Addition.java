package Addition;

class ListNode {

	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
	
	
//	public void addNext(int first) {
//		
//		this.next = new ListNode(first);
//		
//		
//	}

}

public class Addition {

	// Time complexity O(n) Space complexity O(n)
	// 1->3->4->6->8 add 3 to each element, return a new linkedList
	public ListNode add(ListNode head, int k) {

		// corner case
		if (head == null)
			return null;

		ListNode newHead = new ListNode(head.value + k);

		// traverse listNode
		ListNode curr = head;
		ListNode newCurr = newHead;

		while (curr != null) {

			newCurr.next = new ListNode(curr.next.value + k);
			curr = curr.next;// step forward
			newCurr = newCurr.next;

		}

		return newHead;

	}

	// 1->3->5->7 substract 1 from each element : 0->2->4->6, in-place
	// Time complexity O(n) Space complexity O(1)

	public ListNode substract(ListNode head, int k) {

		// corner case
		if (head == null)
			return head;

		ListNode curr = head;
		while (curr != null) {

			curr.value -= k;
			curr = curr.next;

		}

		return head;

	}

}
