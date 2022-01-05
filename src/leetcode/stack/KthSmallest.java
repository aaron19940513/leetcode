package leetcode.stack;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

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


    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int left = matrix[0][0], right = matrix[n][n];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countNotMoreThanMid(matrix, mid, n);
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int countNotMoreThanMid(int[][] matrix, int mid, int n) {
        int count = 0;
        int x = 0, y = n;
        while (x <= n && y >= 0) {
            if (matrix[y][x] <= mid) {
                count += y + 1;
                x++;
            } else {
                y--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        int value = kthSmallest.kthSmallest(new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 5);
        System.out.println(value);
    }

    @Test
    public void test() {
        Assert.assertEquals(kthSmallest2(new int[][] {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}}, 5), 50);
        Assert.assertEquals(kthSmallest2(new int[][] {{123, 234, 345}, {456, 567, 679}, {789, 899, 999}}, 5), 567);
    }

}
