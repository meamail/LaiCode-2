public class BinarySearch {

    public static void main(String args[]) {

        int arr[] = {4, 5, 6, 7, 8, 9, 1, 2, 3};
        BinarySearch bs = new BinarySearch();
        System.out.print(bs.searchShiftedRecursive(arr, -3));


    }

    // return the index of the element
    // Time Complexity: O(logn) Space complexity: O(1)
    // arr has no duplicate elements
    public int classicalSearch(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0)
            return -1;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid - 1;
            else
                right = mid + 1;
        }
        // target not in array
        return -1;
    }

    // first occurrence
    public int searchFirst(int[] arr, int target) {

        // corner case
        if (arr == null || arr.length == 0)
            return -1;

        int left = 0;
        int right = arr.length - 1;
        int mid = left + (right - left) / 2;

        // mid < target -> [mid + 1, R]
        // mid > target -> [L, mid]
        // mid = target -> [L, mid] continue to check left
        while (left < right) {
            // Important: stops at left = right (逐个考虑 left = right - 1 时的情况)

            if (arr[mid] < target)
                left = mid + 1;

            else
                right = mid; // mid >= target
            mid = left + (right - left) / 2;

        }
        // post-processing
        return arr[left] == target ? left : -1;

    }

    // last occurrence : counter-example of first occurrence
    public int searchLast(int[] arr, int target) {

        // corner case
        if (arr == null || arr.length == 0)
            return -1;

        int left = 0;
        int right = arr.length - 1;
        int mid = right - (right - left) / 2;// 向right取整最后才能stop at left = right

        // mid < target -> [mid, R]
        // mid > target -> [L, mid-1]
        // mid = target -> [mid, R] continue to check right
        while (left < right) {
            if (arr[mid] <= target)
                left = mid;
            else
                right = mid - 1;
            mid = right - (right - left) / 2;

        }
        // post-processing
        return arr[right] == target ? right : -1;
    }

    public int searchClosest(int[] arr, int target) {

        // corner case
        if (arr == null || arr.length == 0)
            return -1;

        int left = 0;
        int right = arr.length - 1;
        int mid = left + (right - left) / 2;

        // L Target R or Target L R or R L Target

        while (left < right - 1) {

            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid;
            else
                right = mid;
            mid = left + (right - left) / 2;

        }
        // return the closer one's index
        return Math.abs(arr[mid] - arr[left]) <= Math.abs(arr[mid] - arr[right]) ? left : right;

    }

    // finding target in no-duplicate, shifted array
    //eg. shift by 2 positions, A = {3, 4, 5, 1, 2}, T = 4, return 1;
    // Time complexity, assume shift m units: O(logn) + O(log(n-m)) / O(logm) ~= O(logn)
    // Space complexity: O(1)
    public int searchShifted(int[] array, int target) {

        //corner case
        if (array == null || array.length == 0) return -1;

        int left = 0;
        int right = array.length - 1;
        int smallidx = right;//index of the smallest element

        if (array[left] > array[right]) {//check if shifted

            // find min element index <-> first occurance of the index whose value < arr[0]
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (array[mid] >= array[0]) left = mid + 1;
                else right = mid;
            }
            smallidx = left;
        }

        // binary search only the possible range
        if (array[0] <= target)
            return binarySearch(array, 0, smallidx, target);// 这里写错了，以前写的是smallidx-1；当array没有shift时变为-1没有意义
        else return binarySearch(array, smallidx, array.length - 1, target);
    }


    private int binarySearch(int[] array, int left, int right, int target) {

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    //Improved solution using 1-pass binary search O(logn)
    public int searchShiftedRecursive(int [] arr, int target){

        // corner case
        if(arr == null || arr.length == 0) return -1;
        return searchShiftedRecursive(arr, 0, arr.length-1, target);

    }

    private int searchShiftedRecursive(int [] arr, int left, int right, int target){

        int mid = left + (right - left)/2;
        if (arr[mid] == target) return mid;
        if (left == right) return -1;


        if(arr[left] < arr[mid]){//check if left ~ mid is sorted
            /* As this subarray is sorted, we
			can quickly check if key lies in
			half left ~ mid */
            if(arr[left]< target && target < arr[mid]) return searchShiftedRecursive(arr,left, mid-1, target);
            return searchShiftedRecursive(arr,mid +1 ,right, target);
        }

        else {// mid+1 ~ right is sorted
            if(arr[mid+1] < target && target < arr[right]) return searchShiftedRecursive(arr,mid +1, right, target);
            return searchShiftedRecursive(arr,left, mid-1, target);
        }

    }


}
