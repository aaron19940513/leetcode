package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


import org.junit.Test;

/**
 * 47. 全排列 II 中等
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/18/20 8:52
 */
public class PermuteUnique {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            linkedList.add(nums[i]);
        }
        permute(new ArrayList<>(), linkedList);
        return ans;
    }


    private void permute(List<Integer> arr, LinkedList<Integer> linkedList) {
        if (linkedList.size() == 1) {
            List<Integer> item = new ArrayList<>(arr);
            item.add(linkedList.get(0));
            ans.add(item);
            return;
        }
        Integer pre = null;
        for (int i = 0; i < linkedList.size(); i++) {
            if (pre == null || !pre.equals(linkedList.get(i))) {
                arr.add(linkedList.get(i));
                pre = linkedList.get(i);
                Integer remove = linkedList.remove(i);
                permute(arr, linkedList);
                arr.remove(arr.size() - 1);
                linkedList.add(i, remove);
            }
        }
    }


    @Test
    public void test() {
        List<List<Integer>> permute = permuteUnique(new int[] {1, 2, 2, 3});
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> permute = permuteUnique(new int[] {1, 1, 2});
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }
}
