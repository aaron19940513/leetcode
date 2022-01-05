package leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 144. 二叉树的前序遍历 中等
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/27/20 8:54
 */
public class PreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur =stack.pop();
            while (cur != null) {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                ans.add(cur.val);
                cur = cur.left;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();

        Assert.assertEquals(Arrays.asList(1, 2, 3), preorderTraversal(serialize.deserialize("1,null,2,3")));
        Assert.assertEquals(new ArrayList<>(), preorderTraversal(serialize.deserialize(null)));
        Assert.assertEquals(Arrays.asList(1), preorderTraversal(serialize.deserialize("1")));
        Assert.assertEquals(Arrays.asList(1, 2, 4, 5, 3, 6, 7), preorderTraversal(serialize.deserialize("1,2,3,4,5,6,7")));
    }


}
