import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


/**
 * Find the K smallest numbers in an unsorted integer array A.
 * The returned numbers should be in ascending order.
 * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 */

public class Ksmallest {


    /**
     * Solution 1 : Max Heap + n-k times offer On-line Solution
     * <p>
     * Time : O(k) + O(n-k logk) + O(klogk) = O(k + nlogk)
     * Space : O(k) to store array
     **/


    public int[] kSmallestOnline(int[] array, int k) {
        if (array == null) return null;
        if (k == 0) return new int[]{};

        //if(k > array.length) throw new Exception ("Too many elements required.");

        //no need to implement heapify yourself, use API
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            queue.offer(array[i]);
        }

        for (int i = k; i < array.length; i++) {
            if (array[i] < queue.peek()) { // WRONG : NPE when k = 0
                queue.poll();
                queue.offer(array[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 1; i <= k; i++) {
            res[k - i] = queue.poll();
        }
        return res;

    }

    /**
     * Solution 2 : Heapify and pop k elements
     * Time : O(n) + O(klogn)
     * Space : O(n)
     **/
    public int[] kSmallestOffline(int[] array, int k) {

        if (array == null) return null;
        // if(k > array.length) throw new Exception("To many elements required.");
        if (k == 0) return new int[]{};

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : array) {

            queue.offer(i);

        }

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    /**
     * Solution 3 recursive quickSort
     * <p>
     * xxxxxPivot yyyyyy
     * <p>
     * Step 1: Perform quicksort and return the pivot index idx
     * <p>
     * Step 2:
     * if k = idx + 1 ->return sorted x
     * if k < idx + 1 -> quickSort x to find kth smallest
     * if k > idx + 1 -> quickSort y to find kth smallest
     * <p>
     * do it in-place? No, does not comply with question
     * copy the array and do it in-place
     * <p>
     * Step 3: return the first k sorted elements in the copied array
     * (use Arrays.sort(array))
     * <p>
     * Time Complexity: WCS: O(max(k, n-k)) Amort: O(logn -logk)
     * Space Comlexity:O(n) - new array copy
     **/
    public int[] kSmallest(int[] array, int k) {

        if (array == null) return null;
        if (k == 0) return new int[]{};

        //copy an array to do in-place quickSort
        int[] arr = new int[array.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i];
        }

        quickSelect(arr, 0, arr.length - 1, k);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        Arrays.sort(res);
        return res;
    }

    private void quickSelect(int[] arr, int left, int right, int k) {

        //random a pivot and place it at the right most
        int idx = left + (int) (Math.random() * (right - left + 1));
        swap(arr, idx, right);

        // get the index of pivot
        int pivot = partition(arr, left, right);
        if (k == pivot + 1) return;
        if (k < pivot + 1) quickSelect(arr, left, pivot - 1, k);
        else quickSelect(arr, pivot + 1, right, k);// WRONG: WAS K - PIVOT - 1

    }

    private int partition(int[] arr, int left, int right) {

        // right is the pivot
        int l = left;
        int r = right - 1;

        while (l <= r) {
            if (arr[l] < arr[right]) l++;
            else if (arr[r] >= arr[right]) r--;
            else swap(arr, l++, r--);
        }
        swap(arr, l, right);
        return l;

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }


}
