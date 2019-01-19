/**
 * 对于拥有size个node的满二叉树而言，index > size/2 - 1 的全部是叶节点
 * 0 ~ size/2 -1 all have children
 */


public class MinHeap {

    private int[] array;
    private int size;
    private int loadingFactor;

    // constructor


    public MinHeap(int cap) {

        if (cap <= 0) {
            throw new IllegalArgumentException("capacity cannot be <=0");
        }
        this.array = new int[cap];
        size = cap;

    }

    public MinHeap(int[] array) {

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array cannot be null or empty");
        }
        this.array = array;
        this.size = array.length;
        heapify();

    }

    //O(n)
    private void heapify() {

        for (int index = size / 2 - 1; index >= 0; index--) {// IMPORTANT

            percolateDown(index);

        }


    }


    private void percolateUp(int index) {

        while (index >= 0) {

            int parent = (index - 1) / 2;
            if (array[index] < array[parent]) {
                swap(array, index, parent);
                index = parent;
            } else {
                break;
            }
        }

    }

    private void percolateDown(int index) {

        while (index <= size / 2 - 1) {// guarantee index has child (at least left child)

            int lchild = 2 * index + 1;
            int rchild = 2 * index + 2;
            int child = lchild;

            if (rchild <= size - 1 && array[rchild] < array[lchild]) { // guarantee index has right child

                child = rchild;

            }

            if (array[child] < array[index]) {

                swap(array, child, index);
                index = child;


            } else {

                break;
            }

        }

    }

    private void swap(int[] array, int i, int j) {


        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    //O(1)
    public int peek() {

        if (size == 0) {
            throw new IllegalArgumentException("heap is empty");
        }

        return array[0];
    }

    // O(logn)
    public int poll() {

        if (size == 0) {

            throw new IllegalArgumentException("heap is empty");
        }
        int res = array[0];
        array[0] = array[--size];
        percolateDown(0);
        return res;
    }

    //O(logn)
    public void offer(int value) {

        //resizing
        if (this.isFull()) {

            int[] newArray = new int[size * loadingFactor];
            for (int i = 0; i < size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;

        }

        array[size] = value;
        percolateUp(size++);

    }

    //O(logn)
    public int update(int index, int value) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index out of bound");

        }

        int res = array[index];
        array[index] = value;

        if (value < res) percolateUp(index);
        else if (value > res) percolateDown(index);
        return res;

    }

}
