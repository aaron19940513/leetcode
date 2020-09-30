package Leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 501. 二叉搜索树中的众数 简单
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * @date 09/24/20 8:46
 */
public class FindMode {
    List<Integer> ans = new ArrayList<>();
    int sentry, maxCount, count;

    // public int[] findMode1(TreeNode root) {
    //     TreeNode cur = root;
    //     while(cur!=null){
    //         if(cur.left==null){
    //             updateAns(cur.val);
    //             cur=cur.right;
    //
    //         }
    //     }
    // }


    /**
     * 通过中序遍历将树转成一个升序的队列
     * 很差的算法
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> arr = new ArrayList<>();
        findMode(arr, root);
        List<Integer> ans = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        int sentry = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            int item = arr.get(i);
            if (sentry == item) {
                count++;
            } else {
                if (maxCount < count) {
                    maxCount = count;
                    ans.clear();
                    ans.add(sentry);
                } else if (maxCount == count) {
                    ans.add(sentry);
                }
                count = 1;
                sentry = item;
            }
            if (i == arr.size() - 1) {
                if (maxCount < count) {
                    maxCount = count;
                    ans.clear();
                    ans.add(sentry);
                } else if (maxCount == count) {
                    ans.add(sentry);
                }
            }

        }
        return ans.stream().mapToInt(Number::intValue).toArray();
    }

    private void findMode(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        findMode(list, node.left);
        list.add(node.val);
        findMode(list, node.right);
    }

    /**
     * 中序遍历，在遍历的过程中更新结果
     */
    public int[] findMode1(TreeNode root) {
        if (root == null) {
            return null;
        }
        traversal(root);
        if (maxCount < count) {
            maxCount = count;
            ans.clear();
            ans.add(sentry);
        } else if (maxCount == count) {
            ans.add(sentry);
        }
        int[] ans = new int[this.ans.size()];
        for (int i = 0; i < this.ans.size(); i++) {
            ans[i] = this.ans.get(i);
        }
        return ans;
    }

    private void traversal(TreeNode node) {
        traversal(node.left);
        updateAns(node.val);
        traversal(node.right);
    }

    private void updateAns(int value) {
        if (sentry == value) {
            count++;
        } else {
            if (maxCount < count) {
                maxCount = count;
                ans.clear();
                ans.add(sentry);
            } else if (maxCount == count) {
                ans.add(sentry);
            }
            count = 1;
            sentry = value;
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode(serialize.deserialize("2,1,3,1,1,3,3")), new int[] {1, 3});
    }

    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode(serialize.deserialize("1,null,2")), new int[] {1, 2});
    }

    @Test
    public void test2() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode(serialize.deserialize("1,null,2,2")), new int[] {2});
    }

    @Test
    public void test3() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode1(serialize.deserialize("2,1,3,1,1,3,3")), new int[] {1, 3});
    }

    @Test
    public void test4() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode1(serialize.deserialize("1,null,2")), new int[] {1, 2});
    }

    @Test
    public void test5() {
        Serialize serialize = new Serialize();
        Assert.assertArrayEquals(findMode1(serialize.deserialize("1,null,2,2")), new int[] {2});
    }
}
