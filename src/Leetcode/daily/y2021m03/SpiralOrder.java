package Leetcode.daily.y2021m03;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * @date 2021/3/15 19:25
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowStart = 1;
        int rowEnd = matrix.length;
        int rowLength = rowEnd;
        int colStart = 1;
        int colEnd = matrix[0].length;
        int colLength = colEnd;
        List<Integer> ans = new ArrayList<>();
        int flag = 0;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            if (flag % 4 == 0) {
                for (int k = colStart; k <= colEnd; k++) {
                    ans.add(matrix[rowStart - 1][k - 1]);
                }
                rowStart++;
            } else if (flag % 4 == 1) {
                for (int k = rowStart; k <= rowEnd; k++) {
                    ans.add(matrix[(k - 1)][colEnd - 1]);
                }
                colEnd--;
            } else if (flag % 4 == 2) {
                for (int k = colEnd; k >= colStart; k--) {
                    ans.add(matrix[rowEnd - 1][k - 1]);
                }
                rowEnd--;
            } else {
                for (int k = rowEnd; k >= rowStart; k--) {
                    ans.add(matrix[(k - 1)][colStart - 1]);
                }
                colStart++;
            }
            flag++;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}), Lists.newArrayList(1, 2, 3, 6, 9, 8, 7, 4, 5));
        Assert.assertEquals(spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}), Lists.newArrayList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));
        Assert.assertEquals(spiralOrder(new int[][] {{1, 2, 3}}), Lists.newArrayList(1, 2, 3));
        Assert.assertEquals(spiralOrder(new int[][] {{1}, {2}, {3}}), Lists.newArrayList(1, 2, 3));
    }

}
