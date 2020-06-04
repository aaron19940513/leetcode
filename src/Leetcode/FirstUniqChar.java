package Leetcode;
/**
 * @author sam
 * @date 11/14/19 16:28
 */
public class FirstUniqChar {
    public int Solution(String s) {
        int[] array = new int[26];
        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (array[c - 'a'] == 1) {
                return index;
            }
        }
        return -1;

    }

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        int min = s.length();
        for (int i = 'a'; i <= 'z'; i++) {
            int first = s.indexOf(i);
            if (first == -1) {
                continue;
            }
            if (first == s.lastIndexOf(i) && min > first) {
                min = first;
            }
        }
        return min == s.length() ? -1 : min;
    }
}
