package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 231
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TranslateNum {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] N = new int[s.length()];
        for (int index = 0; index < s.length(); index++) {
            if (index == 0) {
                N[index] = 1;
            } else if (index == 1) {
                String pre = s.substring(index - 1, index + 1);
                if (pre.compareTo("25") > 0 || pre.compareTo("10") < 0) {
                    N[index] = N[index - 1];
                } else {
                    N[index] = N[index - 1] + 1;
                }
            } else {
                String pre = s.substring(index - 1, index + 1);
                if (pre.compareTo("25") > 0 || pre.compareTo("10") < 0) {
                    N[index] = N[index - 1];
                } else {
                    N[index] = N[index - 1] + N[index - 2];
                }
            }
        }
        return N[s.length() - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(translateNum(1402), 2);

    }
}