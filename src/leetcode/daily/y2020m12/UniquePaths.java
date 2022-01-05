package leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/09/20 8:52
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            cache[i][n] = 1;
        }
        for (int i = 1; i <= n; i++) {
            cache[m][i] = 1;
        }
        return dfs(1, 1, m, n, cache);
    }

    private int dfs(int x, int y, int xLength, int yLength, int[][] cache) {
        if (cache[x][y] != 0) {
            return cache[x][y];
        }
        int paths = dfs(x + 1, y, xLength, yLength, cache) + dfs(x, y + 1, xLength, yLength, cache);
        cache[x][y] = paths;
        return paths;
    }

    @Test
    public void test() {
        Assert.assertEquals(uniquePaths(1, 1), 1);
        Assert.assertEquals(uniquePaths(3, 2), 3);
        Assert.assertEquals(uniquePaths(7, 3), 28);
    }
}
