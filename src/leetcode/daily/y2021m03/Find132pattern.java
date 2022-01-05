package leetcode.daily.y2021m03;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 456. 132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * <p>
 * 注意：n 的值小于15000。
 * <p>
 * 示例1:
 * <p>
 * 输入: [1, 2, 3, 4]
 * <p>
 * 输出: False
 * <p>
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 * <p>
 * 输入: [3, 1, 4, 2]
 * <p>
 * 输出: True
 * <p>
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * 示例 3:
 * <p>
 * 输入: [-1, 3, 2, 0]
 * <p>
 * 输出: True
 * <p>
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 *
 * @date 03/24/21 8:48
 */
public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        if (null == nums || nums.length < 3) {
            return false;
        }

        boolean flag12 = false;
        Integer value1 = nums[0];
        Integer value2 = null;
        List<Integer> value1Queue = new ArrayList<>();
        List<Integer> value2Queue = new ArrayList<>();
        value1Queue.add(Integer.MAX_VALUE);
        value2Queue.add(Integer.MIN_VALUE);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < value1Queue.size(); j++) {
                if (nums[i] < value2Queue.get(j) && nums[i] > value1Queue.get(j)) {
                    return true;
                }
            }

            if (flag12) {
                if (nums[i] > value2) {
                    value2 = nums[i];
                } else if (nums[i] < value2 && nums[i] > value1) {
                    return true;
                } else if (nums[i] < value1) {
                    if (value1 < value1Queue.get(value1Queue.size() - 1) && value2 > value2Queue.get(value2Queue.size() - 1)) {
                        value1Queue.remove(value1Queue.size() - 1);
                        value2Queue.remove(value2Queue.size() - 1);
                    }
                    value1Queue.add(value1);
                    value2Queue.add(value2);
                    value1 = nums[i];
                    value2 = null;
                    flag12 = false;
                }

            } else {
                if (nums[i] < value1) {
                    value1 = nums[i];
                } else if (nums[i] > value1) {
                    value2 = nums[i];
                    flag12 = true;
                }
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(find132pattern(new int[] {-1, 3, 2, 0}));
        Assert.assertTrue(find132pattern(new int[] {1, 4, 2}));
        Assert.assertFalse(find132pattern(new int[] {1, 2, 3, 4}));
    }

    @Test
    public void errorCase() {
        Assert.assertTrue(find132pattern(new int[] {3, 5, 0, 3, 4}));
        Assert.assertTrue(find132pattern(new int[] {5, 3, 8, 2, 9, 7}));
        Assert.assertFalse(find132pattern(new int[] {5, 4, 5, 3, 6, 2, 7}));
        Assert.assertTrue(find132pattern(new int[] {10, 12, 6, 8, 3, 11}));
    }
}
