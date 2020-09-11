package Leetcode.weekcompetition.week203;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 1560. 圆形赛道上经过次数最多的扇区 简单
 * <p>
 * 给你一个整数 n 和一个整数数组 rounds 。有一条圆形赛道由 n 个扇区组成，扇区编号从 1 到 n 。现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 m 个阶段组成。其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，到扇区 rounds[i] 结束。举例来说，第 1 阶段从
 * rounds[0] 开始，到 rounds[1] 结束。
 * <p>
 * 请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 升序 排列。
 *
 * @date 08/28/20 16:38
 */
public class MostVisited {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] dp = new int[n + 1];
        int pre = rounds[0];
        for (int i = 1; i < rounds.length; i++) {
            int next = rounds[i];
            if (next > pre) {
                for (int j = pre; j < next; j++) {
                    dp[j]++;
                }
            } else {
                for (int j = 0; j < next; j++) {
                    dp[j]++;
                }
                for (int j = pre; j < n; j++) {
                    dp[j]++;
                }
            }
            pre = next;
        }

        dp[pre]++;

        List<Integer> ans = new ArrayList<>();
        int maxValue = -1;
        for (int i = 1; i < dp.length; i++) {
            int temp = dp[i];
            if (temp > maxValue) {
                maxValue = temp;
                ans.clear();
                ans.add(i);
            } else if (temp == maxValue) {
                ans.add(i);
            }
        }

        return ans;
    }

    @Test
    public void test() {
        List<Integer> integers = mostVisited(4, new int[] {1, 3, 1, 2});
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void test1() {
        List<Integer> integers = mostVisited(2, new int[] {2, 1, 2, 1, 2, 1, 2, 1, 2});
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void test2() {
        List<Integer> integers = mostVisited(7, new int[] {1, 3, 5, 7});
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
