package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t
 * boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * <p>
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 * <p>
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/re-space-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/09/20 9:15
 */
public class ReSpace {
    public int reSpace(String[] dictionary, String sentence) {
        Node root = buildDictionaryTree(dictionary);
        int length = sentence.length();
        int[] dp = new int[length + 1];
        dp[0] = 0;
        for (int i = 1; i <= sentence.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            Node node = root;
            for (int j = i; j >= 1; j--) {
                if (node.children[sentence.charAt(j - 1) - 'a'] != null) {
                    node = node.children[sentence.charAt(j - 1) - 'a'];
                    if (node.leaf) {
                        dp[i] = Math.min(dp[j-1], dp[i]);
                    }
                }else{
                    break;
                }
            }
        }

        return dp[length];
    }

    private Node buildDictionaryTree(String[] dictionary) {
        Node root = new Node();
        for (String string : dictionary) {
            buildOneWord(string, root);
        }
        return root;
    }

    private void buildOneWord(String string, Node node) {
        for (int i = string.length() - 1; i >= 0; i--) {
            node = getChild(string.charAt(i), node);
            if (i == 0) {
                node.leaf = true;
                node.word = string;
            }
        }
    }

    private Node getChild(char c, Node node) {
        if (node.children[c - 'a'] == null) {
            node.children[c - 'a'] = new Node();
        }
        return node.children[c - 'a'];
    }

    class Node {

        public Node[] children;

        public String word;

        public boolean leaf = false;

        public Node() {
            this.children = new Node[26];
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(reSpace(new String[] {"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother"), 7);
    }
}
