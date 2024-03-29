package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 785. 判断二分图  中
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * <p>
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * <p>
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * <p>
 * <p>
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * <p>
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * <p>
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/16/20 9:17
 */
public class IsBipartite {
    public boolean isBipartite(int[][] graph) {
        int length = graph.length;
        // index2 red(1) or blue(-1)
        int[] flag = new int[length];
        for (int index = 0; index < length; index++) {
            if (flag[index] == 0 && !dfs(graph, index, 1, flag)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int index, int color, int[] flag) {
        if (flag[index] == 0) {
            flag[index] = color;
        } else {
            return color == flag[index];
        }
        int[] points = graph[index];
        for (int point : points) {
            if (!dfs(graph, point, -color, flag)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isBipartite(new int[][] {{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        Assert.assertFalse(isBipartite(new int[][] {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        //error case
        Assert.assertFalse(isBipartite(
            new int[][] {{}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}}));
    }

}
