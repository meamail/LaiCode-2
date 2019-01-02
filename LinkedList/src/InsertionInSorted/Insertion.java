package InsertionInSorted;

class ListNode {
	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
}

public class Insertion {

	public static void main(String args[]) {

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = null;

		Insertion ins = new Insertion();
		ListNode newHead = ins.insert(l1, 3);
		ListNode curr = newHead;
		while (curr != null) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
	}

	
	
	public ListNode insert(ListNode head, int value) {
		ListNode target = new ListNode(value);
		// corner case
		if (head == null)
			return target;
		ListNode curr = head;
		ListNode next = curr.next;
		
		
		// insert in the head
		// Time O(1)
		if (value < curr.value) {
			target.next = curr;
			return target;
		}
		
		
		while (curr.value <= value && next != null) {

			
			//insert in the middle
			//Time O(n)
			if (next.value >= value) {
				target.next = next;
				curr.next = target;
				return head;

			}

			next = next.next;
			curr = curr.next;

		}
		
		// insert in the last
		// Time O(n)
		curr.next = target;
		return head;

	}
}
