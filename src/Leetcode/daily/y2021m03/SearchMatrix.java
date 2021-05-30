package Leetcode.daily.y2021m03;
import org.junit.Assert;
import org.junit.Test;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * @date 03/30/21 15:21
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[rowLength - 1][colLength - 1]) {
            return false;
        }
        int rowStart = 0;
        int rowEnd = rowLength - 1;
        int row = 0;
        while (rowStart <= rowEnd) {
            row = (rowStart + rowEnd) / 2;
            if (target < matrix[row][0]) {
                rowEnd = row - 1;
            } else if (target > matrix[row][colLength - 1]) {
                rowStart = row + 1;
            } else {
                break;
            }
        }

        if (rowStart > rowEnd) {
            return false;
        }

        int colStart = 0;
        int colEnd = colLength - 1;
        int col = 0;
        boolean colMatch = false;
        while (colStart <= colEnd) {
            col = (colStart + colEnd) / 2;
            if (target < matrix[row][col]) {
                colEnd = col - 1;
            } else if (target > matrix[row][col]) {
                colStart = col + 1;
            } else {
                colMatch = true;
                break;
            }
        }
        return colMatch;
    }

    @Test
    public void test() {
        Assert.assertTrue(searchMatrix(new int[][] {{1}, {10}, {23}}, 23));
        Assert.assertTrue(searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 34));
        Assert.assertFalse(searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 2));
        Assert.assertFalse(searchMatrix(new int[][] {{1, 3, 5, 7}}, 2));
    }
}
