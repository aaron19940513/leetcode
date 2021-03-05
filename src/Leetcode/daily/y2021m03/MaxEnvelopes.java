package Leetcode.daily.y2021m03;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 354. 俄罗斯套娃信封问题 hard
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * @date 03/04/21 9:22
 */
public class MaxEnvelopes {
    // dfs
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, -1);
        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            max = Math.max(max, dfs(envelopes, dp, i));
        }
        return max;
    }

    private int dfs(int[][] envelopes, int[] dp, int index) {
        if (dp[index] == -1) {
            int length = 1;
            for (int i = index + 1; i < envelopes.length; i++) {
                if (envelopes[i][0] < envelopes[index][0] && envelopes[i][1] < envelopes[index][1]) {
                    length = Math.max(length, dfs(envelopes, dp, i) + 1);
                }
            }
            dp[index] = length;
        }
        return dp[index];
    }

    //最长严格递增子序列  宽度递增排序，高度递减潘旭
    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, maxEnvelopes1(new int[][] {{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        Assert.assertEquals(1, maxEnvelopes1(new int[][] {{5, 4}}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(0, maxEnvelopes1(new int[][] {}));
        Assert.assertEquals(3, maxEnvelopes1(new int[][] {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}}));
    }
}
