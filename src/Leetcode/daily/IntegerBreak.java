package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 343. 整数拆分  中等
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/30/20 9:05
 */
public class IntegerBreak {
    // 数学解法
    public int integerBreak(int n) {
        // n可以分成i个数
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            int divide = n / i;
            int mode = n % i;
            int t = (int) (Math.pow(divide, i - mode) * Math.pow(divide + 1, mode));
            if (t > ans) {
                ans = t;
            } else {
                return ans;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(integerBreak(2), 1);
        Assert.assertEquals(integerBreak(10), 36);
        //error case
        Assert.assertEquals(integerBreak(8), 18);
    }
}
