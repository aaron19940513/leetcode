package leetcode.daily.y2022m07;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;

/**
 * 919. 完全二叉树插入器 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构； CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点
 * TreeNode 的父节点的值； CBTInserter.get_root() 将返回树的头节点。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入 ["CBTInserter", "insert", "insert", "get_root"] [[[1, 2]], [3], [4], []] 输出 [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释 CBTInserter cBTInserter = new CBTInserter([1, 2]); cBTInserter.insert(3);  // 返回 1 cBTInserter.insert(4);  // 返回 2 cBTInserter.get_root(); // 返回
 * [1, 2, 3, 4]
 *
 *
 * 提示：
 *
 * 树中节点数量范围为 [1, 1000] 0 <= Node.val <= 5000 root 是完全二叉树 0 <= val <= 5000 每个测试用例最多调用 insert 和 get_root 操作 104 次
 */
public class CBTInserter {

    private Deque<TreeNode> treeNodes = new ArrayDeque<>();

    int count = 0;

    private TreeNode root = null;

    public CBTInserter(TreeNode root) {
        this.root = root;
        treeNodes.addLast(root);
        while (treeNodes.size() > 0) {
            TreeNode cur = treeNodes.getFirst();
            if (cur.left != null) {
                treeNodes.addLast(cur.left);
            } else {
                count = 0;
                break;
            }
            if (cur.right != null) {
                treeNodes.addLast(cur.right);
            } else {
                count = 1;
                break;
            }
            treeNodes.removeFirst();
        }

    }

    public int insert(int val) {
        TreeNode cur = treeNodes.getFirst();
        if (count == 0) {
            cur.left = new TreeNode(val);
            treeNodes.addLast(cur.left);
        } else if (count == 1) {
            cur.right = new TreeNode(val);
            treeNodes.addLast(cur.right);
            treeNodes.removeFirst();
        }

        count = (count + 1) % 2;
        return cur.val;
    }

    public TreeNode get_root() {
        return root;
    }


    public static void main(String[] args) {
        Serialize serialize = new Serialize();
//        TreeNode root = serialize.deserialize("1,2");
//        CBTInserter obj = new CBTInserter(root);
//        int param_1 = obj.insert(3);
//        int param_2 = obj.insert(4);
//        TreeNode param_3 = obj.get_root();

        TreeNode root = serialize.deserialize("1,2,3,4,5,6,7");
        CBTInserter obj = new CBTInserter(root);
        int param_1 = obj.insert(8);
        int param_2 = obj.insert(9);
        int param_3 = obj.insert(10);
        int param_4 = obj.insert(11);
        int param_5 = obj.insert(12);
        int param_6 = obj.insert(13);
        TreeNode param_7 = obj.get_root();
    }
}
