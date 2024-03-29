package leetcode.daily.y2020m12;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 316. 去除重复字母 mid
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * <p>
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/21/20 9:39
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Stack<Integer> minStack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int value;
        int peek = 0;
        for (int i = 0; i < s.length(); i++) {
            value = s.charAt(i) - 'a';
            count[value]--;
            if (set.contains(value)) {
                continue;
            }
            while (minStack.size() > 0 && (peek = minStack.peek()) > value && count[peek] > 0) {
                set.remove(peek);
                minStack.pop();
            }
            minStack.push(value);
            set.add(value);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : minStack) {
            sb.append((char) (integer + 'a'));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("abc", removeDuplicateLetters("bcabc"));
        Assert.assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    }

    @Test
    public void errorCase(){
        Assert.assertEquals("bac", removeDuplicateLetters("bbcaac"));
    }
}
