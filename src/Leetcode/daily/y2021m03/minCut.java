package Leetcode.daily.y2021m03;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 * @date 03/08/21 13:49
 */
public class minCut {
    public int minCut(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(dp[i], -1);
        }
        boolean[][] flag = initDP(s);
        dfs(flag, dp, 0, length - 1);

        return dp[0][length - 1] - 1;
    }

    private boolean[][] initDP(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int start;
        int end;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            //假设回文字符串长度为奇数，i是回文字符串的中心点
            start = i - 1;
            end = i + 1;
            while (start >= 0 && end < length && s.charAt(start) == s.charAt(end)) {
                dp[start][end] = true;
                start--;
                end++;
            }
            //假设回文字符串的长度为偶数，i是回文串的中点的左边点。
            start = i;
            end = i + 1;
            while (start >= 0 && end < length && s.charAt(start) == s.charAt(end)) {
                dp[start][end] = true;
                start--;
                end++;
            }

        }
        return dp;
    }

    private int dfs(boolean[][] flag, int[][] dp, int startIndex, int endIndex) {
        if (dp[startIndex][endIndex] != -1) {
            return dp[startIndex][endIndex];
        }
        if (flag[startIndex][endIndex]) {
            dp[startIndex][endIndex] = 1;
            return dp[startIndex][endIndex];
        }
        dp[startIndex][endIndex] = endIndex - startIndex + 1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (flag[startIndex][i]) {
                dp[startIndex][endIndex] = Math.min(dp[startIndex][endIndex], 1 + dfs(flag, dp, i + 1, endIndex));
            }
        }
        return dp[startIndex][endIndex];
    }

    @Test
    public void test() {
        Assert.assertEquals(minCut("aab"), 1);
        Assert.assertEquals(minCut("a"), 0);
        Assert.assertEquals(minCut("ab"), 1);
        Assert.assertEquals(minCut("aabba"), 1);
        Assert.assertEquals(minCut("aaaaaaaaaa"), 0);
    }
}
