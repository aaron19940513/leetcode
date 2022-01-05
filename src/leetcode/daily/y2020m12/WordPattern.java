package leetcode.daily.y2020m12;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 290. 单词规律 easy
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/16/20 9:07
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] s1 = s.split(" ");
        if (pattern.length() != s1.length) {
            return false;
        }
        for (int i = 0; i < s1.length; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!Objects.equals(map.get(pattern.charAt(i)), s1[i])) {
                    return false;
                }
            } else {
                if (set.contains(s1[i])) {
                    return false;
                }
                set.add(s1[i]);
                map.put(pattern.charAt(i), s1[i]);
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(wordPattern("abba", "dog cat cat dog"));
        Assert.assertFalse(wordPattern("abba", "dog cat cat fish"));
        Assert.assertFalse(wordPattern("aaaa", "dog cat cat dog"));
        Assert.assertFalse(wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void errorTest() {


    }
}
