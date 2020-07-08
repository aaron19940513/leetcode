package Leetcode.daily;
import java.util.Stack;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * <p>
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * <p>
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * <p>
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 * <p>
 * 提示：
 * <p>
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
        Stack<TreeNode> valueStack = new Stack<>();
        Stack<Integer> deepStack = new Stack<>();
        int deep = 0;

        for (int index = 0; index < S.length(); index++) {
            char c = S.charAt(index);
            if (c == '-') {
                for (; index < S.length() && S.charAt(index) == '-'; index++) {
                    deep++;
                }
                index--;
            } else {
                StringBuffer s = new StringBuffer();
                for (; index < S.length() && S.charAt(index) != '-'; index++) {
                    s.append(S.charAt(index));
                }
                index--;
                while (deepStack.size() > 0 && deep < deepStack.peek()) {
                    recoverNode(valueStack, deepStack);
                }
                deepStack.push(deep);
                valueStack.push(new TreeNode(Integer.parseInt(s.toString())));
                deep = 0;
            }
        }
        while (valueStack.size() > 1) {
            recoverNode(valueStack, deepStack);
        }
        return valueStack.pop();
    }

    private void recoverNode(Stack<TreeNode> valueStack, Stack<Integer> deepStack) {
        if (deepStack.pop().equals(deepStack.peek())) {
            deepStack.pop();
            TreeNode rChild = valueStack.pop();
            TreeNode lChild = valueStack.pop();
            TreeNode parent = valueStack.peek();
            parent.left = lChild;
            parent.right = rChild;

        } else {
            TreeNode node = valueStack.pop();
            TreeNode parent = valueStack.peek();
            if (parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        Assert.assertEquals(serialize.deserialize("10,7,8,6,null,7,6"), recoverFromPreorder("10-7--6-8--7--6"));
        Assert.assertEquals(serialize.deserialize("1,2,5,3,4,6,7"), recoverFromPreorder("1-2--3--4-5--6--7"));
        Assert.assertEquals(serialize.deserialize("1,401,null,349,88,90"), recoverFromPreorder("1-401--349---90--88"));
        Assert.assertEquals(serialize.deserialize("1,2,5,3,null,6,null,4,null,7"), recoverFromPreorder("1-2--3---4-5--6---7"));
    }
}
