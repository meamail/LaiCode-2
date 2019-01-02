package HasRing;

class ListNode {
	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
}

public class HasRing {
	ListNode F, S;

//Time Complexity << O(n) Space Complexity O(1)
	public boolean hasRing(ListNode head) {
		// corner case
		if (head == null || head.next == null)
			return false;
		F = S = head;
		while (F.next != null && F.next.next != null) {
			F = F.next.next;
			S = S.next;
			if (F == S)
				return true;
		}
		return false;
	}
}
