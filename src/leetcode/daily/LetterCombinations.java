package leetcode.daily;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sam Gao
 * @date 08/26/20 8:40
 */
public class LetterCombinations {
    String[] optionalChars = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        String[][] dp = new String[digits.length()][3];
        for (int i = 0; i < digits.toCharArray().length; i++) {

        }
        return null;
    }

    // 回溯
    // 执行用时:7 ms   在所有 Java 提交中击败了33.06% 的用户
    // 内存消耗：40.4 MB, 在所有 Java 提交中击败了5.05%的用户
    public List<String> letterCombinations1(String digits) {
        if (Objects.isNull(digits) || digits.isEmpty()) {
            return new ArrayList<>();
        }
        return back(digits.toCharArray(), 0);
    }

    // dfs
    // 执行用时：7 ms, 在所有 Java 提交中击败了33.06%的用户
    // 内存消耗：40.2 MB, 在所有 Java 提交中击败了13.46%的用户
    public List<String> letterCombinations2(String digits) {
        if (Objects.isNull(digits) || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        dfs(digits.toCharArray(), 0, ans);
        return ans;
    }

    //dfs
    public void dfs(char[] digits, int index, List<String> ans) {
        if (index == 0) {
            for (char c : optionalChars[digits[index] - '0'].toCharArray()) {
                ans.add(String.valueOf(c));
            }
            dfs(digits, index + 1, ans);
        } else if (index == digits.length) {
            return;
        } else {
            List<String> temp = new ArrayList<>();
            for (char c : optionalChars[digits[index] - '0'].toCharArray()) {
                for (String s : ans) {
                    temp.add(s + c);
                }
            }
            ans.clear();
            ans.addAll(temp);
            dfs(digits, index + 1, ans);
        }
    }

    //回溯
    public List<String> back(char[] digits, int index) {
        List<String> stringList = new ArrayList<>();
        if (index == digits.length - 1) {
            for (char c : optionalChars[digits[index] - '0'].toCharArray()) {
                stringList.add(String.valueOf(c));
            }
        } else {
            List<String> temp = back(digits, index + 1);
            for (char c : optionalChars[digits[index] - '0'].toCharArray()) {
                for (String s : temp) {
                    stringList.add(c + s);
                }
            }
        }

        return stringList;
    }

    @Test
    public void test() {
        List<String> strings = letterCombinations1("2345");
        System.out.println(strings.size());
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void test1() {
        Assert.assertEquals(letterCombinations1(""), new ArrayList<>());
    }

    @Test
    public void test2() {
        List<String> strings = letterCombinations2("2");
        System.out.println(strings.size());
        for (String string : strings) {
            System.out.println(string);
        }

        List<String> strings1 = letterCombinations2("2345");
        System.out.println(strings1.size());
        for (String string : strings1) {
            System.out.println(string);
        }
    }
}
