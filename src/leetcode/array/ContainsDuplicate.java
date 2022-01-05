package leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用 堆排序 的话，是 O(1)。
 */
public class ContainsDuplicate {

    public boolean solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
