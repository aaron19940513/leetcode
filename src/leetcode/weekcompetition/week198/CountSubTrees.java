package leetcode.weekcompetition.week198;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 5465. 子树中标签相同的节点数
 * 给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0  到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。树的根节点为节点 0 ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
 * <p>
 * 边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
 * <p>
 * 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
 * <p>
 * 树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
 */
public class CountSubTrees {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> lists = new ArrayList<>(n - 1);
        for (int i = 0; i < n; i++) {
            lists.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            lists.get(edge[0]).add(edge[1]);
            lists.get(edge[1]).add(edge[0]);
        }
        int[] ans = new int[n];
        dfs(0, lists, labels, ans);
        return ans;
    }

    private int[] dfs(Integer index, List<List<Integer>> lists, String labels, int[] ans) {
        int[] temp = new int[26];
        ans[index] = 1;
        temp[labels.charAt(index) - 'a'] = 1;
        for (Integer integer : lists.get(index)) {
            if (ans[integer] == 0) {
                int[] arr = dfs(integer, lists, labels, ans);
                for (int i = 0; i < 26; i++) {
                    temp[i] += arr[i];
                }
            }
        }
        ans[index] = temp[labels.charAt(index) - 'a'];
        return temp;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(countSubTrees(7, new int[][] {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd"), new int[] {2, 1, 1, 1, 1, 1, 1});
        Assert.assertArrayEquals(countSubTrees(4, new int[][] {{0, 1}, {1, 2}, {0, 3}}, "bbbb"), new int[] {4, 2, 1, 1});
        Assert.assertArrayEquals(countSubTrees(5, new int[][] {{0, 1}, {0, 2}, {1, 3}, {0, 4}}, "aabab"), new int[] {3, 2, 1, 1, 1});
        Assert.assertArrayEquals(countSubTrees(6, new int[][] {{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}}, "cbabaa"), new int[] {1, 2, 1, 1, 2, 1});
        Assert.assertArrayEquals(countSubTrees(7, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}}, "aaabaaa"), new int[] {6, 5, 4, 1, 3, 2, 1});
        //error case
        Assert.assertArrayEquals(countSubTrees(4, new int[][] {{0, 2}, {0, 3}, {1, 2}}, "abbe"), new int[] {1, 1, 2, 1});
    }
}
