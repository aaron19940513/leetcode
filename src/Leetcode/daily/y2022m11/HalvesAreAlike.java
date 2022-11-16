package leetcode.daily.y2022m11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class HalvesAreAlike {

    public static Character[] characters = new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    public static Set<Character> set = new HashSet<>();

    static {
        Collections.addAll(set, characters);
    }

    public boolean halvesAreAlike(String s) {
        int result = 0;
        for (int i = 0; i < s.length() / 2; i++) {
            if (set.contains(s.charAt(i))) {
                result++;
            }
        }
        for (int i = s.length() - 1; i >= s.length() / 2; i--) {
            if (set.contains(s.charAt(i))) {
                result--;
            }
        }
        return result == 0;
    }

    @Test
    public void test() {
        Assert.assertTrue(halvesAreAlike("book"));
        Assert.assertFalse(halvesAreAlike("textbook"));
    }

}
