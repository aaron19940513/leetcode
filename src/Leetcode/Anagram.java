package Leetcode;
import org.junit.Test;

/**
 * @author sam
 * @date 11/14/19 16:14
 */
public class Anagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];
        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            array[c - 'a']--;
            if (array[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        Anagram anagram = new Anagram();
        System.out.println(anagram.isAnagram("anagram", "nagaram"));
        System.out.println(anagram.isAnagram("rat", "car"));
    }
}
