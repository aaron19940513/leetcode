package leetcode.daily.y2020m12;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 103. 二叉树的锯齿形层序遍历 mid
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/22/20 8:44
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> leftTreeNodes = new Stack<>();
        Stack<TreeNode> rightTreeNodes = new Stack<>();

        leftTreeNodes.push(root);
        List<Integer> rootList = new ArrayList<>();
        rootList.add(root.val);
        ans.add(rootList);

        List<Integer> row;
        TreeNode temp;
        while (!leftTreeNodes.isEmpty() || !rightTreeNodes.isEmpty()) {
            row = new ArrayList<>();
            if (leftTreeNodes.isEmpty()) {
                while (!rightTreeNodes.isEmpty()) {
                    temp = rightTreeNodes.pop();
                    if (temp.left != null) {
                        leftTreeNodes.push(temp.left);
                        row.add(temp.left.val);
                    }
                    if (temp.right != null) {
                        leftTreeNodes.push(temp.right);
                        row.add(temp.right.val);
                    }
                }
            } else {
                while (!leftTreeNodes.isEmpty()) {
                    temp = leftTreeNodes.pop();
                    if (temp.right != null) {
                        rightTreeNodes.push(temp.right);
                        row.add(temp.right.val);
                    }
                    if (temp.left != null) {
                        rightTreeNodes.push(temp.left);
                        row.add(temp.left.val);
                    }
                }
            }
            if (!row.isEmpty()) {
                ans.add(row);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode deserialize = serialize.deserialize("3,9,20,null,null,15,7");
        for (List<Integer> integers : zigzagLevelOrder(deserialize)) {
            System.out.println(integers);
        }
    }
}
