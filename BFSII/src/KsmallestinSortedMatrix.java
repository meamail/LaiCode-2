/**
 * Given a matrix of size M X N. For each row the elements are sorted in ascending order, so as for each coloumn
 * Find the kth smallest number in it
 * Assumption: Matrix != null, M > 0 && N >0
 * k >=0 && k < M*N
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Sample Solution : minHeap + boolean Matrix
 * Can use Dijkstra because of ascending order <-> cost of each step > 0
 * It is even easier than Dijkstra because you already have Cell value as total distance
 * In essence, Dijkstra is BFS I using minHeap
 * Time : O(klogk) pop + O(2klogk) offer = O(klogk)
 * Space : O(m*n) matrix + O(k) minHeap
 */
public class KsmallestinSortedMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 3, 5}, {2, 4, 7}, {3, 8, 10}};
        KsmallestinSortedMatrix ks = new KsmallestinSortedMatrix();
        System.out.print(ks.kthSmallest(matrix, 8));

    }

    static class Cell {

        int row;
        int col;
        int value;

        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }


    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Best First Search, need a min heap on the value of each cells
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, (Cell c1, Cell c2) ->
        {
            if (c1.value == c2.value) return 0;
            return c1.value < c2.value ? -1 : 1;
        }
        );
        // all the generated cells will be marked true to avoid multiple generation
        boolean[][] visited = new boolean[rows][cols];
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;

        // iterate k-1 rounds and Best First Search k-1 cells
        for (int i = 0; i < k - 1; i++) {
            Cell curr = minHeap.poll();// O(logk)
            //the neighbor cell will be inserted back to the minHeap only if
            // 1. it is not out of the boundary
            // 2. it has not been generated before
            // because for each cell it could be generated twice
            if (curr.row + 1 < rows && !visited[curr.row + 1][curr.col]) {

                minHeap.offer(new Cell(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]));
                visited[curr.row + 1][curr.col] = true;

            }

            if (curr.col + 1 < cols && !visited[curr.row][curr.col + 1]) {

                minHeap.offer(new Cell(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]));
                visited[curr.row][curr.col + 1] = true;
            }

        }

        // this is the kth smallest because k-1 has been popped in above for-loop
        return minHeap.peek().value;


    }


}
