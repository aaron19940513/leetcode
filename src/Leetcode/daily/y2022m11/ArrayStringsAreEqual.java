package leetcode.daily.y2022m11;

import org.junit.Assert;
import org.junit.Test;


public class ArrayStringsAreEqual {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int w11 = 0;
        int w12 = 0;
        int w21 = 0;
        int w22 = 0;

        while (true) {
            if (word1.length == w11 || word2.length == w21) {
                return word1.length == w11 && word2.length == w21;
            }

            if (word1[w11].charAt(w12) != word2[w21].charAt(w22)) {
                return false;
            }
            w12++;
            w22++;

            if (word1[w11].length() == w12) {
                w12 = 0;
                w11++;
            }

            if (word2[w21].length() == w22) {
                w22 = 0;
                w21++;
            }
        }
    }

    @Test
    public void test() {
        Assert.assertTrue(arrayStringsAreEqual(new String[]{"ab", "c" }, new String[]{"a", "bc" }));
        Assert.assertFalse(arrayStringsAreEqual(new String[]{"a", "cb" }, new String[]{"ab", "c" }));
        Assert.assertTrue(arrayStringsAreEqual(new String[]{"abc", "d", "defg" }, new String[]{"abcddefg" }));

        Assert.assertFalse(arrayStringsAreEqual(new String[]{"ab", "c" }, new String[]{"a", "bc", "d" }));
    }
}
