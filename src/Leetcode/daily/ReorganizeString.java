package Leetcode.daily;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 11/30/20 9:18
 */
public class ReorganizeString {
    public String reorganizeString(String S) {
        int[] countArr = new int[26];
        int length = S.length();
        for (int i = 0; i < S.length(); i++) {
            countArr[S.charAt(i) - 'a']++;
        }
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return countArr[o2 - 'a'] - countArr[o1 - 'a'];
            }
        });
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] != 0) {
                if (countArr[i] > (length + 1) / 2) {
                    return "";
                }
                priorityQueue.offer((char) ('a' + i));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Character first = priorityQueue.poll();
            if (first != null) {
                stringBuilder.append(first);
                countArr[first - 'a']--;
            }
            Character second = priorityQueue.poll();
            if (second != null) {
                stringBuilder.append(second);
                countArr[second - 'a']--;
            }
            if (first != null && countArr[first - 'a'] != 0) {
                priorityQueue.add(first);
            }
            if (second != null && countArr[second - 'a'] != 0) {
                priorityQueue.add(second);
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        System.out.println(reorganizeString("aaaabbcc"));
        System.out.println(reorganizeString("bbcc"));
        System.out.println(reorganizeString("aaab"));
    }

}
