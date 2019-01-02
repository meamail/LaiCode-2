
public class BinarySearch2D {

	public static void main(String args[]) {

		int[][] matrix = new int[][] { { 1, 4, 6, 8 }, { 10, 13, 15, 17 }, { 20, 22, 22, 24 } };
		BinarySearch2D bs = new BinarySearch2D();
		System.out.print("{" + bs.searchMatrix(matrix, 22)[0] + ", " + bs.searchMatrix(matrix, 22)[1] + "}");

	}

	// array index -> matrix coordinates

	// first occurrence
	//Time complexity: O(log m*n) Space complexity: O(1)
	public int[] searchMatrix(int[][] arr, int target) {

		// corner case
		if (arr == null || arr[0].length == 0)
			return new int[] { -1, -1 };

		// get shape
		int m = arr.length;
		int n = arr[0].length;

		// 1-D indexing
		int left = 0;
		int right = m * n - 1;
		int mid = left + (right - left) / 2;

		while (left < right) {
			int row = mid / m; // convert 1-D to 2-D
			int col = mid % m;

			if (arr[row][col] < target)
				left = mid + 1;
			else
				right = mid;
			mid = left + (right - left) / 2;
		}
		// return the coordinate or -1,-1 of target not in matrix
		return arr[left / m][left % m] == target ? new int[] { left / m, left % m } : new int[] { -1, -1 };
	}

}
