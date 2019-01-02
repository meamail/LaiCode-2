package ListNode;

import java.util.List;

public class ListNode {
    public int value;
    public ListNode next;

    // non-arg constructor
    public ListNode() {

    }

    // constructor
    public ListNode(int value) {
        this.value = value;
    }

    // constructor with next ListNode
    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    //**** recursive get size O(n)/O(n)
    public int size() {
        // base case
        if (this.next == null) return 1;
        return 1 + this.next.size();
    }

    //**** recursive get i
    // return the value of the ith element O(i)/ O(i), index starts from 0
    public int get(int i) {
        // base case
        if (i == 0) return this.value;
        return this.next.get(i - 1);

    }

    // Time Complexity : O(n) Space Complexity: O(1)
    // 1 => 2 => 5 , add 6,  to 1 => 1 => 2 => 4 => 5 => 25 => 6
    public void addSquare(int value) {

        // square and add the elements
        ListNode curr = this;
        while (curr != null) {

            //instertion
            ListNode squareNode = new ListNode(curr.value * curr.value, curr.next);
            curr.next = squareNode;

            // add the new node at last, O(1)
            if (squareNode.next == null) {
                squareNode.next = new ListNode(value);
                break;
            }
            // step forward
            curr = curr.next.next;
        }
    }

    // Time Complexity : WCS: O(n^2) eg. 8->4->2->1->1
    // 1⇒1⇒2 ==>3 becomes 2⇒2⇒3 and becomes 4⇒3.
    public void addAdjacent() {
        //corner case
        if (this == null || this.next == null) return;

        boolean hasDuplicate = true;
        while (hasDuplicate) {

            ListNode curr = this;
            hasDuplicate = false;// reset to false

            // must add curr != null to avoid NPE, because in the loop's last execution, curr becomes null
            while (curr != null && curr.next != null) {


                if (curr.value == curr.next.value) {

                    hasDuplicate = true;// find duplicates, set to true
                    curr.value += curr.value; // add dupicates
                    curr.next = curr.next.next; // remove one of the duplicate
                }
                curr = curr.next;// step forward
            }
        }
    }

    public static void main(String args[]) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode (8, l1);
        ListNode l3 = new ListNode(4, l2);
        ListNode l4 = new ListNode(2, l3);
        ListNode l5 = new ListNode(2, l4);
        ListNode head = new ListNode(3, l5);

        for (int i = 0; i < head.size(); i++) {
            System.out.println(head.get(i));
        }

        head.addAdjacent();
        //head.addSquare(5);

        for (int i = 0; i < head.size(); i++) {
            System.out.println(head.get(i));
        }
    }

}