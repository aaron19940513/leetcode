package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 647. 回文子串 中等
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的字符串长度不会超过 1000 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/19/20 8:42
 */
public class CountSubstrings {
    //暴力解法 以s中的每个字符为中心扩展点
    //考虑回文字符串长度为奇数和偶数的情况 s长度为n,一共有2n-1种中心点的情况
    public int countSubstrings(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        int left;
        int right;
        for (int i = 0; i < s.length() * 2 - 1; i++) {
            left = i / 2;
            right = i % 2 == 0 ? left : left + 1;
            while (left >= 0 && right < s.length() && chars[left] == chars[right]) {
                left--;
                right++;
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertEquals(countSubstrings("aaa"),6);
        Assert.assertEquals(countSubstrings("abc"),3);
        Assert.assertEquals(countSubstrings("abcddcba"),12);
        Assert.assertEquals(countSubstrings("abccba"),9);
    }
}
