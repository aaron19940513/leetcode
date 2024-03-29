package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * 示例 2：
 * <p>
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * 示例 3：
 * <p>
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EquationsPossible {

    public boolean equationsPossible(String[] equations) {
        char[] parent = new char[26];
        for (char i = 0; i < 26; i++) {
            parent[i] = (char) ('a' + i);
        }
        for (String equation : equations) {
            char symbol = equation.charAt(1);
            if (symbol != '!') {
                char char1 = equation.charAt(0);
                char char2 = equation.charAt(3);
                union(parent, char1, char2);
            }
        }
        for (String equation : equations) {
            char symbol = equation.charAt(1);
            if (symbol == '!') {
                char char1 = equation.charAt(0);
                char char2 = equation.charAt(3);
                if (findRoot(parent, char1) == findRoot(parent, char2)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void union(char[] parent, char char1, char char2) {
        parent[findRoot(parent, char1) - 'a'] = findRoot(parent, char2);
    }

    private char findRoot(char[] parent, char c) {
        char ans = c;
        while (parent[ans - 'a'] != ans) {
            parent[ans - 'a'] = parent[parent[ans - 'a'] - 'a'];
            ans = parent[ans - 'a'];
        }
        return ans;
    }


    @Test
    public void test() {
        Assert.assertTrue(equationsPossible(new String[] {"f==b", "c==b", "c==b", "e!=f"}));
        Assert.assertTrue(equationsPossible(new String[] {"f==b", "c==b", "c==b", "e!=f"}));
        Assert.assertTrue(equationsPossible(new String[] {"c==c", "b==d", "x!=z"}));
        Assert.assertTrue(equationsPossible(new String[] {"b==a", "a==b"}));
        Assert.assertTrue(equationsPossible(new String[] {"b==a", "a==b", "b==c"}));
        Assert.assertFalse(equationsPossible(new String[] {"a==b", "b!=c", "c==a"}));
        Assert.assertFalse(equationsPossible(new String[] {"a!=a"}));
    }
}
