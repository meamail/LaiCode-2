
public class RecursiveBinarySearch {

	public static void main(String args[]) {

		int[] arr = { 1, 3, 4, 8, 9 };
		RecursiveBinarySearch bs = new RecursiveBinarySearch();
		System.out.print(bs.search(arr, 1));

	}

	// no duplicate elements
	// time complexity O(logn) space complexity O(logn)
	public int search(int[] arr, int target) {

		// corner case
		if (arr == null || arr.length == 0)
			return -1;

		return search(arr, 0, (arr.length - 1) / 2, arr.length - 1, target);

	}

	public int search(int[] arr, int left, int mid, int right, int target) {

		// base case
		if (arr[mid] == target)
			return mid;
		if (left == right)
			return -1;

		if (arr[mid] < target)
			left = mid + 1;
		else
			right = mid - 1;
		return search(arr, left, left + (right - left) / 2, right, target);

	}

}
