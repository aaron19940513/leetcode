package leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1002. 查找常用字符 简单
 *
 * @date 10/14/20 8:48
 */
public class CommonChars {
    public List<String> commonChars(String[] A) {
        if (A == null) {
            return null;
        }
        int[] base = new int[26];
        for (char c : A[0].toCharArray()) {
            base[c - 'a']++;
        }
        int[] temp = new int[26];
        for (int i = 1; i < A.length; i++) {
            String s = A[i];
            Arrays.fill(temp, 0);
            for (char c : s.toCharArray()) {
                temp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                base[j] = Math.min(base[j], temp[j]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < base.length; i++) {
            for (int j = base[i]; j > 0; j--) {
                ans.add(String.valueOf((char) ('a' + i)));
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(commonChars(new String[] {"bella", "label", "roller"}), Arrays.asList("e", "l", "l"));
    }

    @Test
    public void test1() {
        Assert.assertEquals(commonChars(new String[] {"cool", "lock", "cook"}), Arrays.asList("c", "o"));
    }
}
