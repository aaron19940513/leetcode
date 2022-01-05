package leetcode.daily.y2021m04;
import org.junit.Assert;
import org.junit.Test;

/**
 * 91. 解码方法 mid
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * <p>
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * <p>
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 * <p>
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 *
 * @date 04/21/21 13:54
 */
public class NumDecodings {
    public int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        if (s.length() >= 2 && (s.substring(s.length() - 2).equals("00"))) {
            return 0;
        }
        int[][] dp = new int[s.length()][2];
        for (int i = 0; i < dp.length; i++) {
            if(s.charAt(i)=='0'){
                dp[i][0] = 0;
                dp[i][1] = 0;
            }else{
                dp[i][0] = -1;
                dp[i][1] = -1;
            }

            if (i == dp.length - 1) {
                dp[i][0] = s.charAt(dp.length - 1) == '0' ? 0 : 1;
                dp[i][1] = 0;
            }
        }
        return dfs(s, dp, 0);
    }

    private int dfs(String s, int[][] dp, int startIndex) {
        if (startIndex == s.length()) {
            return 1;
        }
        if (dp[startIndex][0] != -1) {
            return dp[startIndex][0] + dp[startIndex][1];
        }
        if (s.charAt(startIndex) != '0') {
            dp[startIndex][0] = dfs(s, dp, startIndex + 1);

            if (startIndex < s.length() - 1 && Integer.parseInt(s.substring(startIndex, startIndex + 2)) <= 26) {
                dp[startIndex][1] = dfs(s, dp, startIndex + 2);
            }else{
                dp[startIndex][1] =0;
            }
        }
        return dp[startIndex][0] + dp[startIndex][1];
    }

    @Test
    public void test() {
        Assert.assertEquals(1, numDecodings("10"));
        Assert.assertEquals(2, numDecodings("11106"));
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(0, numDecodings("0"));
        Assert.assertEquals(0, numDecodings("06"));
    }

    @Test
    public void errorCase(){
        Assert.assertEquals(1, numDecodings("27"));
        Assert.assertEquals(3, numDecodings("226"));
    }
}
