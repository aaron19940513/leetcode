package Leetcode.daily.y2020m12;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Sam Gao
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 12/14/20 9:09
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<List<String>>> map = new HashMap<>();
        Map<String, int[]> charCount = new HashMap<>();
        for (String str : strs) {
            Integer stringValue = getStringValue(str);
            List<List<String>> groupByValue = map.get(stringValue);
            if (groupByValue == null) {
                groupByValue = new ArrayList<>();
                map.put(stringValue, groupByValue);
                List<String> strings = new ArrayList<>();
                strings.add(str);
                groupByValue.add(strings);
            } else {
                boolean isNew = true;
                for (List<String> strings : groupByValue) {
                    String s1 = strings.get(0);
                    if (s1.length() == str.length() && stringValueEquals(str, s1, charCount)) {
                        strings.add(str);
                        isNew = false;
                        break;
                    }
                }
                if (isNew) {
                    List<String> strings = new ArrayList<>();
                    strings.add(str);
                    groupByValue.add(strings);
                }
            }
        }
        return map.values().stream().flatMap(t-> {
            return t.stream();
        }).collect(Collectors.toList());
    }

    private boolean stringValueEquals(String str, String s1, Map<String, int[]> charCount) {
        int[] counts = charCount.get(s1);
        if (counts == null) {
            counts = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                counts[s1.charAt(i) - 'a']++;
            }
            charCount.put(s1, counts);
        }
        int[] counts1 = new int[26];

        for (int i = 0; i < str.length(); i++) {
            counts1[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] != counts1[i]) {
                return false;
            }
        }
        return true;
    }

    private Integer getStringValue(String str) {
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            ans += str.charAt(i) - 'a';
        }
        return ans;
    }

    @Test
    public void test() {
        List<List<String>> ans = groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> an : ans) {
            System.out.println(an);
        }
    }

    @Test
    public void test1() {
        List<List<String>> ans = groupAnagrams(new String[] {"abcd", "acbd", "aadd", "ddaa"});
        for (List<String> an : ans) {
            System.out.println(an);
        }
    }
}
