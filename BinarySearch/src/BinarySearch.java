public class BinarySearch {

	public static void main(String args[]) {

		int[] arr = { 1, 1, 3, 3, 3, 5, 8, 9 };
		BinarySearch bs = new BinarySearch();
		System.out.print(bs.searchFirst(arr, 1));
		System.out.print(bs.searchLast(arr, 3));
		System.out.print(bs.searchClosest(arr, 6));

	}

	// return the index of the element
	// Time Complexity: O(logn) Space complexity: O(1)

	// arr has no duplicate elements

	public int search1(int[] arr, int target) {
		// corner case
		if (arr == null || arr.length == 0)
			return -1;
		int left = 0;
		int right = arr.length - 1;
		int mid = left + (right - left) / 2;
		while (left <= right) {

			if (arr[mid] == target)
				return mid;
			if (arr[mid] < target)
				left = mid - 1;
			else
				right = mid + 1;
			mid = left + (right - left) / 2;

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

}
