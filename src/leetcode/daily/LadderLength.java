package leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 127. 单词接龙  中等
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 11/05/20 9:14
 */
public class LadderLength {
    int minLength = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        bfs(Collections.singletonList(beginWord), endWord, set, 1);

        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    public void bfs(List<String> beginWords, String endWord, HashSet<String> wordList,  int distance) {
        List<String> distanceOneList = new ArrayList<>();
        for (String beginWord : beginWords) {
            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (distanceOne(beginWord, word)) {
                    iterator.remove();
                    distanceOneList.add(word);
                }
            }
        }
        if (distanceOneList.isEmpty()) {
            return;
        }
        if (distanceOneList.contains(endWord)) {
            minLength = Math.min(distance + 1, minLength);
            return;
        }
        bfs(distanceOneList, endWord, wordList, distance + 1);
    }

    private boolean distanceOne(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }


    @Test
    public void test() {
        Assert.assertEquals(5, ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

    }

    @Test
    public void test1() {

        Assert.assertEquals(0, ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, ladderLength("hit", "cog", Arrays.asList("hot", "dog", "log", "cog")));
    }

    @Test
    public void test3() {
        Assert.assertEquals(3, ladderLength("hot", "dog", Arrays.asList("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot")));
    }
}
