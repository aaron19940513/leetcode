package Leetcode.daily;
import Leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 106. 从中序与后序遍历序列构造二叉树 中等
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 09/25/20 9:17
 */
public class BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart == inEnd) {
            return new TreeNode(inorder[inStart]);
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inMid = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inMid = i;
            }
        }
        int leftLength = inMid - inStart;
        int rightLength = inEnd - inMid;
        if (leftLength != 0) {
            root.left = buildTree(inorder, postorder, inStart, inMid - 1, postStart, postStart + leftLength - 1);
        }
        if (rightLength != 0) {
            root.right = buildTree(inorder, postorder, inMid + 1, inEnd, postStart + leftLength, postEnd - 1);
        }
        return root;
    }

    @Test
    public void test() {
        TreeNode treeNode = buildTree(new int[] {9, 3, 15, 20, 7}, new int[] {9, 15, 7, 20, 3});
    }
}
