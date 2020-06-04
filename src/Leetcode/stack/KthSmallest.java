package Leetcode.stack;
import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * <p>
 * 示例:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * 返回 13。
 */
public class KthSmallest {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int rowIndex = n;
        int colIndex = n;
        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < colIndex; j++) {
                int value = matrix[i][j];
                if (maxHeap.size() == k) {
                    if (value < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.add(matrix[i][j]);
                    } else {
                        colIndex = j;
                    }
                } else {
                    maxHeap.add(value);
                }
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        int value = kthSmallest.kthSmallest(new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 5);
        System.out.println(value);
    }

}
