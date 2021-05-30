package Leetcode.daily.y2021m01;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sam Gao
 * @date 2021/2/2 18:41
 */
public class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        int head = 0;
        int tail = 0;
        int maxNum = 0;
        while (tail < s.length()) {
            counts[s.charAt(tail) - 'A']++;
            maxNum = Math.max(counts[s.charAt(tail) - 'A'], maxNum);
            if (tail - head + 1 - maxNum > k) {
                counts[s.charAt(head) - 'A']--;
                head++;
            }
            tail++;
        }
        return tail - head;
    }


    @Test
    public void test() {
        Assert.assertEquals(characterReplacement("ABAB", 2), 4);
        Assert.assertEquals(characterReplacement("AABABBA", 1), 4);
    }
}

