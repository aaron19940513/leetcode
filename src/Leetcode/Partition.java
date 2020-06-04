package Leetcode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sam
 * @date 11/08/19 9:09
 */
public class Partition {
    public static void main(String[] args) {
        List<List<String>> result = solution("aabbaa");
        for (List<String> strings : result) {
            System.out.println(strings.toString());
        }
        List<List<String>> result1 = solution("aab");
        for (List<String> strings : result1) {
            System.out.println(strings.toString());
        }
        partition1("aabbaa");
    }

    public static List<List<String>> solution(String s) {
        List<List<String>> result = new ArrayList<>();
        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            String part = s.substring(0, endIndex);
            if (isPalindrome(part)) {
                if(endIndex == s.length()){
                    List<String> one = new ArrayList<>();
                    one.add(part);
                    result.add(one);
                }else{
                    List<List<String>> temp = solution(s.substring(endIndex));
                    for (List<String> strings : temp) {
                        strings.add(0, part);
                    }
                    result.addAll(temp);
                }
            }
        }
        return result;
    }

    public static boolean isPalindrome(String a) {
        char[] charArray = a.toCharArray();
        int start = 0;
        int end = charArray.length - 1;
        while (start < end) {
            if (charArray[start] != charArray[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static List<List<String>> partition1(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1) && (len < 3 || dp[i + 1][i + len - 2]);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        partitionHelper(s, 0, dp, new ArrayList<>(), ans);
        return ans;
    }

    private static void partitionHelper(String s, int start, boolean[][] dp, List<String> temp, List<List<String>> res) {
        //到了空串就加到最终的结果中
        if (start == s.length()) {
            res.add(new ArrayList<>(temp));
        }
        //在不同位置切割
        for (int i = start; i < s.length(); i++) {
            //如果是回文串就加到结果中
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                temp.add(left);
                partitionHelper(s, i + 1, dp, temp, res);
                temp.remove(temp.size() - 1);
            }

        }
    }

}
