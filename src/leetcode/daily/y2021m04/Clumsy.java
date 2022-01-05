package leetcode.daily.y2021m04;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1006. 笨阶乘 mid
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * <p>
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 * <p>
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * <p>
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * <p>
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：4
 * 输出：7
 * 解释：7 = 4 * 3 / 2 + 1
 * 示例 2：
 * <p>
 * 输入：10
 * 输出：12
 * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10000
 * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 *
 * @date 04/01/21 9:45
 */
public class Clumsy {
    private int clumsy4 = 4 * 3 / 2;
    private int clumsy3 = 3 * 2 / 1;
    private int clumsy2 = 2 * 1;
    private int clumsy1 = 1;

    public int clumsy(int N) {
        int ans = clumsySimple(N);
        if (N - 3 > 0) {
            ans += N - 3;
        }
        N -= 4;
        for (; N > 0; N -= 4) {
            ans -= clumsySimple(N);
            if (N - 3 > 0) {
                ans += N - 3;
            }
        }
        return ans;
    }

    private int clumsySimple(int N) {
        if (N > 4) {
            return N + 1;
        }
        switch (N) {
            case 1:
                return clumsy1;
            case 2:
                return clumsy2;
            case 3:
                return clumsy3;
            case 4:
                return clumsy4;
            default:
                return 0;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(7, clumsy(4));
        Assert.assertEquals(12, clumsy(10));
    }
}
