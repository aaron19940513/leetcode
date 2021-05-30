package Leetcode.weekcompetition.week243;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5773. 插入后的最大值 显示英文描述
 * 给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
 * <p>
 * 你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值 ​​​​​​。但 不能 在负号的左边插入 x 。
 * <p>
 * 例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
 * 如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。
 * 返回插入操作后，用字符串表示的 n 的最大值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = "99", x = 9
 * 输出："999"
 * 解释：不管在哪里插入 9 ，结果都是相同的。
 * 示例 2：
 * <p>
 * 输入：n = "-13", x = 2
 * 输出："-123"
 * 解释：向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
 *
 * @date 2021/5/30 10:50
 */
public class MaxValue {
    //如果是正数，遇到第一个比自己小数，插到前面
    //如果是负数，遇到第一个比自己大的数，插到前面
    public String maxValue(String n, int x) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        if (n.startsWith("-")) {
            stringBuilder.append('-');
            for (int i = 1; i < n.length(); i++) {
                if (flag && n.charAt(i) - '0' > x) {
                    stringBuilder.append(x);
                    flag = false;
                }
                stringBuilder.append(n.charAt(i));
            }
        } else {
            for (int i = 0; i < n.length(); i++) {
                if (flag && n.charAt(i) - '0' < x) {
                    stringBuilder.append(x);
                    flag = false;
                }
                stringBuilder.append(n.charAt(i));
            }
        }
        if (n.length() == stringBuilder.length()) {
            stringBuilder.append(x);
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("999", maxValue("99", 9));
        Assert.assertEquals("-123", maxValue("-13", 2));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals("828824579515", maxValue("28824579515", 8));
    }
}
