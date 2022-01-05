package leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sam
 * @date 11/08/19 13:49
 */
public class WordBreak {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(wordBreak("leetcode", list));
        List<String> list1 = new ArrayList<>();
        list1.add("cats");
        list1.add("dog");
        list1.add("sand");
        list1.add("and");
        list1.add("cat");
        System.out.println(wordBreak("catsanddog", list1));
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("aa");
        list2.add("aaa");
        list2.add("aaaaa");
        list2.add("aaaaaaaa");
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", list2));


        for (String s : wordBreak2("catsanddog", list1)) {
            System.out.println(s);
        }
    }

    /**
     * 暴力
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) {
            return true;
        }
        for (int index = 0; index < s.length(); index++) {
            if (wordDict.contains(s.substring(0, index + 1)) && wordBreak(s.substring(index + 1), wordDict)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 记忆回溯
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak1(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);

    }

    public static boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
     */


    public static List<String> wordBreak2(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<Integer> lengthSet = new HashSet<>();
        for (String s1 : wordDict) {
            maxLength = maxLength > s1.length() ? maxLength : s1.length();
            lengthSet.add(s1.length());
        }
        Boolean[][] dp = new Boolean[s.length() + 1][maxLength + 1];
        List<String> ans = new ArrayList<>();
        word_break2(s, 0, lengthSet, dp, new HashSet<>(wordDict), ans, new ArrayList<>());
        return ans;

    }

    private static Boolean word_break2(String s, int start, Set<Integer> lengthSet, Boolean[][] dp,
                                       HashSet<String> wordDict, List<String> ans, List<String> temp) {
        if (start == s.length()) {
            ans.add(generate(temp));
            return true;
        }
        Boolean flag = false;
        for (Integer length : lengthSet) {
            if (start + length <= s.length()) {
                String subString = s.substring(start, start + length);
                if (wordDict.contains(subString) && (null == dp[start][length] || dp[start][length])) {
                    temp.add(subString);
                    dp[start][length] = word_break2(s, start + length, lengthSet, dp, wordDict, ans, temp);
                    if (dp[start][length]) {
                        flag = true;
                    }
                    temp.remove(temp.size() - 1);
                }
            }
        }
        return flag;
    }

    private static String generate(List<String> temp) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String s : temp) {
            if (first) {
                first = false;
                sb.append(s);
            } else {
                sb.append(" ").append(s);
            }
        }
        return sb.toString();
    }


}