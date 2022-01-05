package leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/06/20 9:10
 */
public class PalindromePairs {
    // 暴力解法  超时
    // public List<List<Integer>> palindromePairs(String[] words) {
    //     List<List<Integer>> ans = new ArrayList<>();
    //     for (int i = 0; i < words.length; i++) {
    //         for (int j = 0; j < words.length; j++) {
    //             if (i != j && isPalindrome(words[i] + words[j])) {
    //                 ans.add(Arrays.asList(i, j));
    //             }
    //         }
    //     }
    //     return ans;
    // }
    //
    // private boolean isPalindrome(String word) {
    //     for (int start = 0, end = word.length() - 1; start < end; start++, end--) {
    //         if (word.charAt(start) != word.charAt(end)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    //字典树
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie =new Trie();
        return null;
    }


    private class Trie {

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (!node.containsKey(c)) {
                    node.put(c, new Node());
                }
                node = node.get(c);
            }
            node.setEnd();
        }


        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (!node.containsKey(c)) {
                    return false;
                }
                node = node.get(c);
            }
            return node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                if (!node.containsKey(c)) {
                    return false;
                }
                node = node.get(c);
            }
            return true;
        }

        public Node prefixNode(String prefix) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                if (!node.containsKey(c)) {
                    return null;
                }
                node = node.get(c);
            }
            return node;
        }


    }

    private class Node {
        // R links to node children
        private Node[] children;

        private final int R = 26;

        private boolean isEnd;


        public Node() {
            children = new Node[R];
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }

        public Node get(char ch) {
            return children[ch - 'a'];
        }

        public void put(char ch, Node node) {
            children[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }

    @Test
    public void test() {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(0, 1));
        ans.add(Arrays.asList(1, 0));
        ans.add(Arrays.asList(3, 2));
        ans.add(Arrays.asList(2, 4));
        Assert.assertEquals(palindromePairs(new String[] {"abcd", "dcba", "lls", "s", "sssll"}), ans);
    }
}
