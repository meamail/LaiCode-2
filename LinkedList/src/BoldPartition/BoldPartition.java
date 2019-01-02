package BoldPartition;

class ListNode {
	int value;
	ListNode next;

	public ListNode(int value) {

		this.value = value;
	}
}

//partition of a linkedlist

//before 1->6->10->2->5->2 value 4
//after 1->2->2->6->10->5

//Time Complexity : O(n) Space Complexity: O(1)

public class BoldPartition {
	
	
	public static void main(String args[]) {
		
		BoldPartition bp = new BoldPartition();
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(6);
		ListNode l3 = new ListNode(10);
		ListNode l4 = new ListNode(2);
		ListNode l5 = new ListNode(5);
		ListNode l6  = new ListNode(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		bp.partition(l1, 4);
		ListNode curr = l1;
		while(curr != null) {
			
			System.out.println(curr.value);
			curr = curr.next;
			
		}
		
		
		
	}
	
	
	public ListNode partition(ListNode head, int value) {
		// corner case
		if (head == null || head.next == null)
			return head;

		// two dummy head that initiates two LinkedList
		ListNode leftHead = new ListNode(0);// stores smaller values
		ListNode left = leftHead;// used for traverse
		ListNode rightHead = new ListNode(0);// stores larger values
		ListNode right = rightHead;

		ListNode curr = head;
		while (curr != null) {
			if (curr.value < value) {
				left.next = curr;// 添加
				left = left.next;// 前进
			} else {
				right.next = curr;
				right = right.next;
			}
			curr = curr.next;
		}
		
		//解决right的拖家带口问题，curr走完，right后面跟了2
		// right: 6->10->5 (->2) 
		right.next = null;
		
		// link right(cut dummy head) to left
		left.next = rightHead.next;
		return leftHead.next;
	}
}
