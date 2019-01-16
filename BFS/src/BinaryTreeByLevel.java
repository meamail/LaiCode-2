import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 Step 1: put the root in queue, size = 1
 Step 2:
 Case 1: if queue is empty, end
 Case 2: if queue is not empty,
 expand: add the first element's children to queue, (update size)
 generate: append the first element to list size th times.
 append the list to result

 **/

public class BinaryTreeByLevel {


    // Time Complexity : O(n)
    //Space Complexity : O(logn) queue has logn elements at most
    public List<List<Integer>> layerByLayer(TreeNode root) {


        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;// WRONG: SHOULD INCLUDE THIS LINE TO AVOID NPE

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {// *each condition checking maps a level

            // list of elements in each level
            List<Integer> level = new ArrayList<>();
            int size = queue.size();// *This is enough to guarantee by-level

            for (int i = 0; i < size; i++) {

                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);


                }
                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);

                }
                level.add(queue.pop().key);
            }

            res.add(level);
        }

        return res;

    }


}
