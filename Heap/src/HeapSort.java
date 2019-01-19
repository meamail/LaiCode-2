/**
 * Heap sort of an array of integers
 */

public class HeapSort {


    public static void main (String args[]){
        HeapSort hps = new HeapSort();
        int [] arr = {8,6,4,5,7,9,2};
        hps.heapSort(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }


    // in-place heap sort
    //Time:  O(n) heapify + O(nlogn) percolateDown
    // Space : O(1)
    public void heapSort(int[] arr) {

        if (arr == null || arr.length == 0) return;
        heapify(arr);
        heapSort(arr, 0, arr.length - 1);

    }

    private void heapSort(int[] arr, int left, int right) {

        if (left == right) return;

        swap(arr, left, right);// left is the largest ele, swap it to the right-most
        percolateDown(arr, left, right - 1);
        heapSort(arr, left, right - 1);


    }

    private void heapify(int[] arr) {

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            percolateDown(arr, i, arr.length - 1);
        }


    }

    private void percolateDown(int[] arr, int index, int right) {

        while (index <= (right + 1) / 2 - 1) {

            int lchild = 2 * index + 1;
            int rchild = 2 * index + 2;
            int child = lchild;
            if (rchild <= right && arr[lchild] < arr[rchild]) {

                child = rchild;

            }

            if (arr[index] < arr[child]) {

                swap(arr, index, child);
                index = child;
            } else {
                break;
            }

        }


    }

    private void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
