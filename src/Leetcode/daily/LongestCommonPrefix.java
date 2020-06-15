package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sam Gao
 * @date 06/15/20 16:54
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int minLength = 0;
        String prefix = "";
        for (String str : strs) {
            if (str.length() > minLength) {
                minLength = str.length();
            }
        }

        for (int index = 0; index < minLength; index++) {
            int temp = strs[0].charAt(index);
            for (int j = 1; j < strs.length; j++) {
                if ((temp ^ strs[j].charAt(index)) != 0) {
                    return prefix;
                }
            }
            prefix = prefix + strs[0].charAt(index);
        }
        return prefix;

    }

    @Test
    public void test() {
        Assert.assertEquals("fl", longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
    }
}
