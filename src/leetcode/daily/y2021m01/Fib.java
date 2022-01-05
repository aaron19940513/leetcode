package leetcode.daily.y2021m01;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 * <p>
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 30
 *
 * @date 01/04/21 8:55
 */
public class Fib {
    //递归
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    //动态规划
    public int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        return fib1(n, dp);
    }

    public int fib1(int n, int[] dp) {
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = fib1(n - 1, dp) + fib1(n - 2, dp);

        return dp[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(fib(2), 1);
        Assert.assertEquals(fib(3), 2);
        Assert.assertEquals(fib(4), 3);
    }

    @Test
    public void test1() {
        Assert.assertEquals(fib1(2), 1);
        Assert.assertEquals(fib1(3), 2);
        Assert.assertEquals(fib1(4), 3);
    }
}
