package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 392. 判断子序列  简单
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * 返回 true.
 * <p>
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * 返回 false.
 * <p>
 * 后续挑战 :
 * <p>
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/27/20 9:05
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int tLength = t.length();
        int tIndex = 0;
        for (int sIndex = 0; sIndex < s.length(); sIndex++) {
            while (tIndex < tLength && s.charAt(sIndex) != t.charAt(tIndex)) {
                tIndex++;
            }
            tIndex++;
        }
        return tIndex <= tLength;
    }

    @Test
    public void test() {
        Assert.assertTrue(isSubsequence("abc", "ahbgdc"));
        Assert.assertFalse(isSubsequence("axc", "ahbgdc"));
    }
}
