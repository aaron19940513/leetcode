package Leetcode.dynamic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/18/20 8:59
 */
public class Permute {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
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
        for (int i = 0; i < linkedList.size(); i++) {
            arr.add(linkedList.get(i));
            Integer remove = linkedList.remove(i);
            permute(arr, linkedList);
            arr.remove(arr.size() - 1);
            linkedList.add(i, remove);
        }
    }


    @Test
    public void test() {
        List<List<Integer>> permute = permute(new int[] {1, 2, 3});
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> permute = permute(new int[] {2, 3, 4, 1});
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }

}
