package leetcode.weekcompetition.week240;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5750. 人口最多的年份 显示英文描述
 * 题目难度Easy
 * 给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
 * <p>
 * 年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
 * <p>
 * 返回 人口最多 且 最早 的年份。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：logs = [[1993,1999],[2000,2010]]
 * 输出：1993
 * 解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
 * 示例 2：
 * <p>
 * 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
 * 输出：1960
 * 解释：
 * 人口最多为 2 ，分别出现在 1960 和 1970 。
 * 其中最早年份是 1960 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 *
 * @date 2021/5/9 10:30
 */
public class MaximumPopulation {
    public int maximumPopulation(int[][] logs) {
        int ans = 0;
        int nums = 0;
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int[] log : logs) {
            if (log[1] > log[0]) {
                while (!priorityQueue.isEmpty() && log[0] >= priorityQueue.peek()) {
                    priorityQueue.poll();
                }
                priorityQueue.offer(log[1]);
            }
            if (priorityQueue.size() > nums) {
                ans = log[0];
                nums = priorityQueue.size();
            }
        }
        return ans;
    }


    @Test
    public void test() {
        Assert.assertEquals(1993, maximumPopulation(new int[][] {{1993, 1999}, {2000, 2010}}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(1960, maximumPopulation(new int[][] {{1950, 1961}, {1960, 1971}, {1970, 1981}}));
    }
}
