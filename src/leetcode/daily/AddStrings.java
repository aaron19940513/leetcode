package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/03/20 8:58
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        int first = 0;
        int second = 0;
        int added = 0;
        StringBuilder ans = new StringBuilder();
        int maxIndex = Math.max(num1.length(), num2.length());
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (maxIndex > 0) {
            first = i >= 0 ? num1.charAt(i) - 48 : 0;
            second = j >= 0 ? num2.charAt(j) - 48 : 0;
            ans.append((first + second + added) % 10);
            added = first + second+ added >= 10 ? 1 : 0;
            i--;
            j--;
            maxIndex--;
        }
        if (added > 0) {
            ans.append(added);
        }
        return ans.reverse().toString();
    }

    @Test
    public void main() {
        Assert.assertEquals(addStrings("2", "3"), "5");
        Assert.assertEquals(addStrings("567", "789"), "1356");
        Assert.assertEquals(addStrings("12345", "2356"), "14701");
    }
}
