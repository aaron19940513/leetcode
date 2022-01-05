package leetcode.daily.y2021m04;
/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 * @date 04/14/21 9:32
 */
public class Trie {

    Node root;

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
            node = node.setCharIfAbsent(c);
        }
        node.setLeaf();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            node = node.getChar(c);
            if (node == null) {
                return false;
            }
        }
        return node.isLeaf();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChar(c);
            if (node == null) {
                return false;
            }
        }
        return true;
    }


    private class Node {
        Node[] nodes;

        int R = 26;

        boolean leaf;

        public Node() {
            nodes = new Node[R];
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf() {
            leaf = true;
        }

        public Node setCharIfAbsent(char a) {
            Node node = nodes[a - 'a'];
            if (node == null) {
                node = new Node();
                nodes[a - 'a'] = node;
            }
            return node;
        }

        public Node getChar(char a) {
            return nodes[a - 'a'];
        }
    }

    public static void main(String[] args) {

        Trie obj = new Trie();
        obj.insert("apple");
        boolean param_2 = obj.search("apple");
        boolean param_3 = obj.startsWith("app");
    }
}
