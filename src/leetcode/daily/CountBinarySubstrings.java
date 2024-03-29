package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 696. 计数二进制子串 简单
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * <p>
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * <p>
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/10/20 9:08
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        char ch = s.charAt(0);
        int preCount = 0;
        int count = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ch == s.charAt(i)) {
                count++;
            } else {
                ans += Math.min(preCount, count);
                ch = s.charAt(i);
                preCount = count;
                count = 1;
            }
        }
        ans += Math.min(preCount, count);
        return ans;
    }

    @Test
    public void test(){
        Assert.assertEquals(countBinarySubstrings("00110011"),6);
        Assert.assertEquals(countBinarySubstrings("10101"),4);
    }


    @Test
    public void test1(){
        Assert.assertEquals(countBinarySubstrings("0001111000011"),9);
    }
}
