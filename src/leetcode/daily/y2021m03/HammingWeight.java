package leetcode.daily.y2021m03;
import org.junit.Assert;
import org.junit.Test;

/**
 * 191. 位1的个数 easy
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * <p>
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入必须是长度为 32 的 二进制串 。
 *
 * @date 03/22/21 8:44
 */
public class HammingWeight {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            if (n % 2 != 0) {
                ans++;
            }
            n = n >>> 1;
        }
        return ans;
    }


    @Test
    public void test() {
        Assert.assertEquals(3, hammingWeight(11));
        Assert.assertEquals(1, hammingWeight(128));
        Assert.assertEquals(31, hammingWeight(-3));
    }


}
