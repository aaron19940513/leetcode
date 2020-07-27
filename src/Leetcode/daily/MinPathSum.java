package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 64. 最小路径和 中等
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/23/20 9:49
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                dp[i][k] = Integer.MAX_VALUE;
            }
        }
        return minPath(m, n, 0, 0, grid, dp);
    }

    //永远先向下走，再向又走
    private int minPath(int rowLength, int colLength, int m, int n, int[][] grid, int[][] dp) {
        if (dp[m][n] != Integer.MAX_VALUE) {
            return dp[m][n];
        }
        if (m == rowLength - 1 && n == colLength - 1) {
            return dp[m][n] = grid[m][n];
        }
        if (m < rowLength - 1) {
            dp[m][n] = Math.min(minPath(rowLength, colLength, m + 1, n, grid, dp) + grid[m][n], dp[m][n]);
        }
        if (n < colLength - 1) {
            dp[m][n] = Math.min(minPath(rowLength, colLength, m, n + 1, grid, dp) + grid[m][n], dp[m][n]);
        }
        return dp[m][n];
    }

    @Test
    public void test() {
        Assert.assertEquals(minPathSum(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}), 7);
        //error case
        Assert.assertEquals(minPathSum(new int[][] {{1, 2,5}, {3,2,1}}), 6);
    }
}
