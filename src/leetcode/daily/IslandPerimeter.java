package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 463. 岛屿的周长 简单
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/30/20 8:46
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;
                    //top
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        ans += -1;
                    }
                    //down
                    if (i + 1 <= grid.length - 1 && grid[i + 1][j] == 1) {
                        ans += -1;
                    }
                    //left
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        ans += -1;
                    }
                    //right
                    if (j + 1 <= grid[i].length - 1 && grid[i][j + 1] == 1) {
                        ans += -1;
                    }
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(16, islandPerimeter(new int[][] {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}));
        Assert.assertEquals(0,islandPerimeter(null));
        Assert.assertEquals(6,islandPerimeter(new int[][]{{0, 1, 1, 0}}));
    }

}
