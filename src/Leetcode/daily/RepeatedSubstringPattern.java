package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 459. 重复的子字符串 简单
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * <p>
 * 输入: "aba"
 * <p>
 * 输出: False
 * 示例 3:
 * <p>
 * 输入: "abcabcabcabc"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/24/20 8:47
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        char endChar = s.charAt(s.length() - 1);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.length() % (i + 1) == 0 && s.charAt(i) == endChar && s.substring(0, i + 1).equals(s.substring(s.length() - i - 1))) {
                String repeatString = s.substring(0, i + 1);
                boolean repeat = true;
                for (int j = 1; j < s.length() / (i + 1) - 1; j++) {
                    if (!repeatString.equals(s.substring(j * (i + 1), (j + 1) * (i + 1)))) {
                        repeat = false;
                        break;
                    }
                }
                if (repeat) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(repeatedSubstringPattern("abcabc"));
        Assert.assertFalse(repeatedSubstringPattern("aaaaabaaaa"));
        Assert.assertFalse(repeatedSubstringPattern("aba"));
        Assert.assertTrue(repeatedSubstringPattern("aaabaaaaaabaaa"));
    }

    @Test
    public void test1() {
        Assert.assertTrue(repeatedSubstringPattern("ababababababaababababababaababababababa"));
    }
}
