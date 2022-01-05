package leetcode.weekcompetition.week227;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5673. 移除石子的最大得分 显示英文描述
 * 通过的用户数754
 * 尝试过的用户数869
 * 用户总通过次数755
 * 用户总提交次数1096
 * 题目难度Medium
 * 你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。
 * <p>
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
 * <p>
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 2, b = 4, c = 6
 * 输出：6
 * 解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是：
 * - 从第一和第三堆取，石子状态现在是 (1, 4, 5)
 * - 从第一和第三堆取，石子状态现在是 (0, 4, 4)
 * - 从第二和第三堆取，石子状态现在是 (0, 3, 3)
 * - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
 * - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
 * - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
 * 总分：6 分 。
 * 示例 2：
 * <p>
 * 输入：a = 4, b = 4, c = 6
 * 输出：7
 * 解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是：
 * - 从第一和第二堆取，石子状态现在是 (3, 3, 6)
 * - 从第一和第三堆取，石子状态现在是 (2, 3, 5)
 * - 从第一和第三堆取，石子状态现在是 (1, 3, 4)
 * - 从第一和第三堆取，石子状态现在是 (0, 3, 3)
 * - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
 * - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
 * - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
 * 总分：7 分 。
 * 示例 3：
 * <p>
 * 输入：a = 1, b = 8, c = 8
 * 输出：8
 * 解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
 * 注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
 * 提示：
 * <p>
 * 1 <= a, b, c <= 105
 *
 * @date 2021/2/7 10:58
 */
public class MaximumScore {
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        queue.offer(a);
        queue.offer(b);
        queue.offer(c);
        int ans = 0;
        while (queue.size() >= 2) {
            Integer poll = queue.poll();
            Integer poll1 = queue.poll();
            if (poll - 1 > 0) {
                queue.offer(poll - 1);
            }
            if (poll1 - 1 > 0) {
                queue.offer(poll1 - 1);
            }
            ans++;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(maximumScore(2, 4, 6), 6);
        Assert.assertEquals(maximumScore(1, 8, 8), 8);
        Assert.assertEquals(maximumScore(4, 4, 6), 7);
    }
}
