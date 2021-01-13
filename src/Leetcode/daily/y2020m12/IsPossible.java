package Leetcode.daily.y2020m12;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 659. 分割数组为连续子序列  中等
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 * <p>
 * 示例 3：
 * <p>
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的数组长度范围为 [1, 10000]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/04/20 9:48
 */
public class IsPossible {
    public boolean isPossible(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int pre = nums[0];
        // key is maxValue
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        put(map, pre);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - pre <= 1) {
                put(map, nums[i]);
            } else if (isValidate(map)) {
                map = new HashMap<>();
                put(map, nums[i]);
            } else {
                return false;
            }
            pre = nums[i];
        }

        return isValidate(map);
    }

    private boolean isValidate(Map<Integer, PriorityQueue<Integer>> map) {
        for (PriorityQueue<Integer> value : map.values()) {
            for (Integer length : value) {
                if (length > 0 && length < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public void put(Map<Integer, PriorityQueue<Integer>> map, Integer maxValue) {
        PriorityQueue<Integer> queue = map.get(maxValue);
        if (queue == null) {
            queue = new PriorityQueue<>();
            map.put(maxValue, queue);
        }
        PriorityQueue<Integer> preQueue = map.get(maxValue - 1);
        if (preQueue != null) {
            Integer minLength = preQueue.poll();
            if (preQueue.isEmpty()) {
                map.remove(maxValue - 1);
            }
            queue.offer(minLength + 1);
        } else {
            queue.offer(1);
        }
    }

    @Test
    public void test() {
        Assert.assertFalse(isPossible(new int[] {1, 2}));
    }

    @Test
    public void test1() {
        Assert.assertTrue(isPossible(new int[] {1, 2, 3, 7, 8, 9}));
        Assert.assertTrue(isPossible(new int[] {1, 2, 3, 3, 4, 5}));
        Assert.assertTrue(isPossible(new int[] {1, 2, 3, 3, 4, 4, 5, 5}));
        Assert.assertFalse(isPossible(new int[] {1, 2, 3, 4, 4, 5}));
    }
}
