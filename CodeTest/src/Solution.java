

public class Solution {


    public static void main(String[] args) {


        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l3;


        Solution rbs = new Solution();

        ListNode node = rbs.cycleNode(l1);

        System.out.print(node.value);




    }


    // Time complexity O(n) Space complexity O(1)
    // n1-> n2 -> n3 -> n4 -> nn to n1 -> nn -> n2 -> nn-1 -> n3 -> nn-2
    // eg. 1 -> 2 -> 3 -> 4 -> 5 to 1 -> 5 -> 2 -> 4 -> 3

    public ListNode reorder(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        //find mid
        ListNode mid = findMid(head);

        //break into two LinkedList
        ListNode secList = mid.next;
        mid.next = null;// break

        //reverse the second LinkedList
        ListNode secHead = reverse(secList);

        //merge two LinkedLists by linking elements in turn
        return merge(head, secHead);


    }

    // Time complexity O(n) Space Complexity O(1)
    // find the middle node in a linked list, if the number of nodes is even, return the first one
    // eg. Node 1 -> Node 2  return Node 1
    public ListNode findMid(ListNode head) {

        ListNode f = head;
        ListNode s = head;

        while (f.next != null && f.next.next != null) {

            f = f.next.next;
            s = s.next;

        }
        //the mid position is s
        return s;

    }

    // Time complexity O(n) Space Complexity O(1)
    // reverse a linked list
    public ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }

    // Time complexity O(n) Space Complexity O(1)
    // merge two linkedlists one by one in turn
    // eg. 1 -> 2 -> 3 and 4-> 5 to 1 -> 4 -> 2 -> 5 -> 3
    public ListNode merge(ListNode head1, ListNode head2){

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        ListNode curr1 = head1;
        ListNode curr2 = head2;

        while(curr1 != null && curr2 != null){

            curr.next = curr1;
            curr = curr.next;
            curr1 = curr1.next;

            curr.next = curr2;
            curr = curr.next;
            curr2 = curr2.next;

        }

        // if orginal list with odd number of listnodes, the mid element remains as curr1
        if(curr1 != null) {
            curr.next = curr1;
        }

        return dummyHead.next;

    }


    // Time complexity O(1) Space complexity: O(1)
    // Insertion in a sorted LinkedList
    public ListNode insert(ListNode head, int target) {


        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode l = dummyHead;
        ListNode r = head;

        // finding the list WCS O(n)
        while (r != null && r.value < target) {// r != null must be in front of r.value

            r = r.next;
            l = l.next;


        }

        // insertion O(1)
        ListNode t = new ListNode(target);
        t.next = r;
        l.next = t;
        return dummyHead.next;

    }

    //Check if a given linked list has a cycle.
    // Return the node where the cycle starts. Return null if there is no cycle.
    // time complexity BCS : O(n - ring) WCS: O(n + k*ring)
    public ListNode cycleNode(ListNode head) {

        // corner case
        if(head == null || head.next == null) return null;

        // check if there is a ring
        // and count the length of the cycle if there is a ring
        ListNode s = head;// slow
        ListNode f = head;// fast
        int kcycleLength = 0;// k times the cycleLength

        while(f.next != null && f.next.next != null){

            s = s.next;
            f = f.next.next;
            kcycleLength++;

            if(s == f){
                return findNode(head,kcycleLength);
            }


        }
        // no cycle
        return null;
    }

    // Time complexity O(n-ring)
    // find the start node of a cycle in a linkedList, k times of the cycle length is given
    public ListNode findNode(ListNode head, int kcycleLength){
        ListNode left = head;
        ListNode right = head;

        // step right forward by k times the cycle
        for(int i = 0; i < kcycleLength; i++){
            right = right.next;
        }

        while(right != null){

            if(left == right) return left;
            left = left.next;
            right = right.next;
        }

        // this line will not be reached
        return null;

    }


    // Delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
    // in-place solution; eg. 1->2->3->3->4->4->5 to 1->2->5
    // time complexity: O(n) space complexity : O(1)
    public ListNode removeDup_inplace(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode curr = head;
        ListNode next = head.next;

        //outer loop: traverse the list
        while (next != null) {
            boolean enterWhile = false;

            // check and delete duplicates
            // eg. dummy->1->1->1->2->3 to dummy->1->2->3
            while (next != null && curr.value == next.value) {
                enterWhile = true;
                // perform deletion
                curr.next = next.next;
                next = next.next;
            }

            if (enterWhile) {
                //delete the remaining 1 duplicate
                // eg. dummy->1->1->1->2->3 to dummy->1->2->3 to dummy->2->3
                prev.next = next;// i.e. prev.next = prev.next.next

                // check if reaching the end of the list
                if (next != null) {
                    // step forward
                    curr = next;
                    next = next.next;
                }
                // else: the removal is completed, jump out of the outer while loop
            } else {
                // did not enter while, simply step forward
                prev = curr;
                curr = next;
                next = next.next;
            }
        }
        return dummyHead.next;
    }

    //not in-place solution
    public ListNode removeDup(ListNode head) {

        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        ListNode l = dummyHead;
        ListNode curr = head;
        ListNode next = head.next;
        boolean enterWhile = false;

        // outer loop: traverse
        while (next != null) {
            // find duplicates and step next forward
            while (next != null && curr.value == next.value) {
                enterWhile = true;
                next = next.next;
            }

            //step forward after the while loop
            if (enterWhile && next != null) {
                curr = next;
                next = next.next;
            } else if (enterWhile && next == null) {
                curr = next;//only step curr forward to the last node
            }

            // did not enter the while loop, link curr to l.next and step forward
            if (!enterWhile) {
                l.next = curr;
                curr = next;
                next = next.next;
                l = l.next;
            }
        }
        // link the last node to l
        l.next = curr;
        return dummyHead.next;

    }


    //Remove the nth node from end: Given linked list: 1->2->3->4->5, and n = 2
    //After removing the second node from the end, the linked list becomes 1->2->3->5.
    // use two ListNodes, the right one is n steps forward than the left one
    // Time complexity O(n) Space complexity O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {


        // corner case and condition checking
        if (n <= 0 || head == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode left = dummyHead;
        ListNode right = dummyHead;

        // check if n is valid
        for (int i = 0; i < n; i++) {
            if (right.next != null) right = right.next;
            else return head;
        }

        // step forward
        while (right.next != null) {

            right = right.next;
            left = left.next;

        }

        //now left is the prev node of the node to be deleted
        // deletion

        left.next = left.next.next;

        return dummyHead.next;
    }

}



class ListNode {

    int value;
    ListNode next;

    public ListNode(int value) {

        this.value = value;


    }




}
