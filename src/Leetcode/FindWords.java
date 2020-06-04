package Leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author sam
 * @date 11/13/19 16:36
 */
public class FindWords {
    public List<String> solution(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> ans = new HashSet<>();
        int x_len = board.length;
        int y_len = board[0].length;
        find(board, trie, ans, x_len, y_len);
        return new ArrayList<>(ans);
    }

    private void find(char[][] board, Trie trie, Set<String> ans, int x_len, int y_len) {
        for (int x = 0; x < x_len; x++) {
            for (int y = 0; y < y_len; y++) {
                find(board, x, y, ans, x_len, y_len, trie.root);
            }
        }
    }

    private void find(char[][] board, int x, int y, Set<String> ans, int x_len, int y_len, Node node) {
        if (x >= 0 && y >= 0 && x < x_len && y < y_len && board[x][y] != '#' && node.containsKey(board[x][y])) {
            char origin = board[x][y];
            board[x][y] = '#';
            node = node.get(origin);
            if (node.isEnd()) {
                ans.add(node.word);
            }
            find(board, x - 1, y, ans, x_len, y_len, node);
            find(board, x + 1, y, ans, x_len, y_len, node);
            find(board, x, y - 1, ans, x_len, y_len, node);
            find(board, x, y + 1, ans, x_len, y_len, node);

            board[x][y] = origin;

        }

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
            node.setWord(word);

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

        private String word;

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

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }


}
