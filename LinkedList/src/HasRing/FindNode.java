package HasRing;


//Check if a given linked list has a cycle.
// Return the node where the cycle starts. Return null if there is no cycle.



public class FindNode {


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
    // find the start node
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


}
