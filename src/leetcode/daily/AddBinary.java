package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * @date 06/23/20 13:43
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int maxLength = Math.max(a.length(), b.length());
        StringBuilder zeroPrefix = new StringBuilder();
        for (int i = 0; i < Math.abs(a.length() - b.length()); i++) {
            zeroPrefix.append('0');
        }

        if (a.length() < maxLength) {
            a = zeroPrefix.append(a).toString();
        } else {
            b = zeroPrefix.append(b).toString();
        }
        int advance = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = maxLength - 1; i >= 0; i--) {
            int temp = a.charAt(i) + b.charAt(i) + advance - 96;
            sb.insert(0, temp % 2);
            if (temp >= 2) {
                advance = 1;
            } else {
                advance = 0;
            }
        }
        if (advance != 0) {
            sb.insert(0, advance);
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(addBinary("11", "1"), "100");
        Assert.assertEquals(addBinary("1010", "1011"), "10101");
    }

}
