package Leetcode.daily.y2021m04;
import org.junit.Assert;
import org.junit.Test;

/**
 * 363. 矩形区域不超过 K 的最大数值和 hard
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * <p>
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * <p>
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 *
 * @date 04/22/21 10:45
 */
public class MaxSumSubmatrix {
    //枚举矩形的长度和宽度
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preM = new int[m + 1][n + 1];
        int[][] preN = new int[n + 1][m + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                preM[i][j] = preM[i][j - 1] + matrix[i - 1][j - 1];
                preN[j][i] = preN[j][i - 1] + matrix[i - 1][j - 1];
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int length = 1; length <= m; length++) {
            for (int width = 1; width <= n; width++) {
                for (int mIndex = 0; mIndex <= m - length; mIndex++) {
                    for(int nIndex =0;nIndex<=n-width; nIndex++){
                        preM[i][j] = preM[i][j - 1] + matrix[i - 1][j - 1];
                        preN[j][i] = preN[j][i - 1] + matrix[i - 1][j - 1];
                    }
                }
            }
        }


        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, maxSumSubmatrix(new int[][] {{1, 0, 1}, {0, -2, 3}}, 2));
        Assert.assertEquals(3, maxSumSubmatrix(new int[][] {{2, 2, -1}}, 3));
    }
}
