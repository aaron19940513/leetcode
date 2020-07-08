package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b
 * "不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 * <p>
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 * <p>
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 * <p>
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 * <p>
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 06/22/20 14:52
 */
public class PatternMatching {
    public boolean patternMatching(String pattern, String value) {
        if (pattern.length() == 0 && value.length() == 0) {
            return true;
        }
        if (pattern.length() == 0) {
            return false;
        }
        int firstCharCount = 0;
        int secondCharStartIndex = 0;
        char first = pattern.charAt(0);
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == first) {
                firstCharCount++;
            } else if (secondCharStartIndex == 0) {
                secondCharStartIndex = i;
            }
        }
        int secondCharCount = pattern.length() - firstCharCount;

        String match1 = "";
        String match2 = "";
        if (secondCharCount == 0) {
            if (value.length() % firstCharCount != 0) {
                return false;
            }
            StringBuffer sb = new StringBuffer();
            match1 = value.substring(0, value.length() / firstCharCount);
            for (int i = 0; i < firstCharCount; i++) {
                sb.append(match1);
            }
            return sb.toString().equals(value);
        }

        for (int i = -1; i < value.length(); i++) {
            if (i != -1) {
                match1 = match1 + value.charAt(i);
            }
            if (match1.length() * firstCharCount > value.length()) {
                return false;
            }
            if ((value.length() - firstCharCount * match1.length()) % secondCharCount != 0) {
                continue;
            }
            int lengthOfValue2 = (value.length() - firstCharCount * match1.length()) / secondCharCount;
            match2 = value.substring(secondCharStartIndex * match1.length(), secondCharStartIndex * match1.length() + lengthOfValue2);
            if (match1.equals(match2)) {
                continue;
            }
            StringBuffer sb = new StringBuffer();
            boolean match = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) == first) {
                    sb.append(match1);
                } else {
                    sb.append(match2);
                }
                if (!value.startsWith(sb.toString())) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(patternMatching("abba", "dogcatcatdog"));
        Assert.assertFalse(patternMatching("abba", "dogcatcatfish"));
        Assert.assertFalse(patternMatching("aaaa", "dogcatcatdog"));
        Assert.assertTrue(patternMatching("abba", "dogdogdogdog"));
        Assert.assertTrue(patternMatching("a", ""));
        Assert.assertFalse(patternMatching("ab", ""));
        Assert.assertTrue(patternMatching("", ""));
        Assert.assertFalse(patternMatching("", "x"));
        Assert.assertTrue(patternMatching("bbb", "xxxxxx"));
        Assert.assertTrue(patternMatching("bbba", "xxxxxxy"));
        Assert.assertTrue(patternMatching("aaaaab", "xahnxdxyaahnxdxyaahnxdxyaahnxdxyaauxuhuo"));

    }

}
