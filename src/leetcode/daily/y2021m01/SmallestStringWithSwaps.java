package leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1202. 交换字符串中的元素  mid
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * <p>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * <p>
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 * <p>
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 * <p>
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 * @date 01/12/21 16:11
 */
public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] father = new int[s.length()];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        for (int i = 0; i < pairs.size(); i++) {
            List<Integer> pair = pairs.get(i);
            union(pair.get(0), pair.get(1), father);
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            PriorityQueue<Character> priorityQueue = map.get(findParent(i, father));
            if (priorityQueue == null) {
                priorityQueue = new PriorityQueue<>();
                map.put(i, priorityQueue);
            }
            priorityQueue.offer(chars[i]);
        }
        for (int i = 0; i < chars.length; i++) {
            chars[i] = map.get(findParent(i, father)).poll();
        }
        return String.valueOf(chars);
    }


    private void union(int f, int t, int[] father) {
        int a = findParent(f, father);
        int b = findParent(t, father);
        if (a == b) {
            return;
        }
        if (a > b) {
            father[a] = b;
        } else {
            father[b] = a;
        }
    }

    private int findParent(int f, int[] father) {
        while (father[f] != f) {
            f = father[f];
        }
        father[f] = f;
        return f;
    }


    @Test
    public void test(){
        List<List<Integer>> items = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(0);
        item.add(3);
        items.add(item);
        List<Integer> item1 = new ArrayList<>();
        item1.add(1);
        item1.add(2);
        items.add(item1);
        Assert.assertEquals("bacd",smallestStringWithSwaps("dcab",items));
    }

    @Test
    public void test1(){
        List<List<Integer>> items = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(0);
        item.add(3);
        items.add(item);
        List<Integer> item1 = new ArrayList<>();
        item1.add(1);
        item1.add(2);
        items.add(item1);
        List<Integer> item2 = new ArrayList<>();
        item2.add(0);
        item2.add(2);
        items.add(item2);
        Assert.assertEquals("abcd",smallestStringWithSwaps("dcab",items));
    }

    @Test
    public void test2(){
        List<List<Integer>> items = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(0);
        item.add(1);
        items.add(item);
        List<Integer> item1 = new ArrayList<>();
        item1.add(1);
        item1.add(2);
        items.add(item1);

        Assert.assertEquals("abc",smallestStringWithSwaps("cba",items));
    }
}
