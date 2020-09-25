package Leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sam Gao
 * @date 09/24/20 8:46
 */
public class FindMode {
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
            if(i == arr.size()-1){
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
}
