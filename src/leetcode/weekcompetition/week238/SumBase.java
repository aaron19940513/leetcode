package leetcode.weekcompetition.week238;
import org.junit.Assert;
import org.junit.Test;

/**
 * K 进制表示下的各位数字总和 显示英文描述
 * 题目难度Easy
 * 给你一个整数 n（10 进制）和一个基数 k ，请你将 n 从 10 进制表示转换为 k 进制表示，计算并返回转换后各位数字的 总和 。
 * <p>
 * 转换后，各位数字应当视作是 10 进制数字，且它们的总和也应当按 10 进制表示返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 34, k = 6
 * 输出：9
 * 解释：34 (10 进制) 在 6 进制下表示为 54 。5 + 4 = 9 。
 * 示例 2：
 * <p>
 * 输入：n = 10, k = 10
 * 输出：1
 * 解释：n 本身就是 10 进制。 1 + 0 = 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 2 <= k <= 10
 *
 * @date 2021/4/25 10:33
 */
public class SumBase {
    public int sumBase(int n, int k) {
        int ans = 0;
        while (n > 0) {
            ans += n % k;
            n = n / k;
        }
        return ans;
    }


    @Test
    public void test() {
        Assert.assertEquals(1, sumBase(10, 10));
        Assert.assertEquals(9, sumBase(34, 6));
        Assert.assertEquals(10, sumBase(340, 6));
    }
}
