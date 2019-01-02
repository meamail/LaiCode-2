package FindMid;


class ListNode {

	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
}



// Time complexity : O(n) Space Complexity: O(1)
public class FindMid {
	
	
	public static void main(String args[]) {

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = null;

		FindMid fm = new FindMid();

		System.out.print(fm.findMid(l1).value);



	}
	
	
	ListNode F;
	ListNode S;

	public ListNode findMid(ListNode head) {
		
		// corner case
		if (head == null || head.next == null)
			return head;
		
		F = S = head;
		while (F.next != null && F.next.next != null) {
			F = F.next.next;
			S = S.next;
		}
		return S;
	}
}
