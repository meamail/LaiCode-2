public class ReOrder {

    public static void main(String[] args) {
        ReOrder ro = new ReOrder();
        int[] arr = {1, 2, 3, 4};
        ro.reorder(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

    public int[] reorder(int[] array) {
        // Write your solution here
        if (array.length <= 3) return array;

/**
 * 保证进入的reorder 的array元素为偶数个
 * reorder的拆分方式保证了subproblem的array元素依然为偶数个
 * eg. 7 + 7 -> 6 + 8 -> 2 + 4 + 4 + 4 -> 2 + 2 + 2 + 2 + 2 + 2 + 2
 */
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    // Time: O(nlogn)
    // Space: O(1)

    private void reorder(int[] array, int left, int right) {


        // base case : right  = left + 2 (two elements)
        // or right == left (no elements) (如果 一开始没有check arr.length <= 3的话
        if (right == left + 1) return;

/**
 * 这类由于使用size
 * mid，leftMid, rightMid全部 向右 取整
 */
        int size = right - left + 1;
        int mid = left + size / 2;
        int leftMid = left + size / 4;
        int rightMid = left + size * 3 / 4;

        // I love Yahoo : swap leftMid ~ Mid-1 && Mid ~ rightMid
        reverse(array, leftMid, mid - 1);
        reverse(array, mid, rightMid - 1);
        reverse(array, leftMid, rightMid - 1);


/**
 * 这里不写 mid-1 是因为mid不是chunk交换后的正确分割点
 * mid是交换chunk之间的正确分割点，但不是 交换 后 的正确分割点
 * 比如一开始是一个 7 + 7 的array
 * 切分为 3 + 4 | 3 + 4  这里mid切开了 3 | 4
 * 交换后为 3 + 3 | 4 + 4 -> 6 + 8
 * 显然用mid仍然是 7 + 7，交换后的切割点为距离left 两倍 leffmid -left
 * 即为 left + 2*(leftMid - left) - 1 （leftMid向右取整，减）
 *
 */
        reorder(array, left, left + 2 * (leftMid - left) - 1);// why mid-1 won't work???
        reorder(array, left + 2 * (leftMid - left), right);

    }

    private void reverse(int[] array, int left, int right) {
        int i = left;
        int j = right;
        while (i < j) {
            swap(array, i++, j--);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
