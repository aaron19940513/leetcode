package leetcode.weekcompetition.week227;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5672. 检查数组是否经排序和轮转得到 显示英文描述
 * 通过的用户数457
 * 尝试过的用户数607
 * 用户总通过次数457
 * 用户总提交次数685
 * 题目难度Easy
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 * <p>
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 * <p>
 * 源数组中可能存在 重复项 。
 * <p>
 * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,5,1,2]
 * 输出：true
 * 解释：[1,2,3,4,5] 为有序的源数组。
 * 可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,1,3,4]
 * 输出：false
 * 解释：源数组无法经轮转得到 nums 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：true
 * 解释：[1,2,3] 为有序的源数组。
 * 可以轮转 x = 0 个位置（即不轮转）得到 nums 。
 * 示例 4：
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：true
 * 解释：[1,1,1] 为有序的源数组。
 * 轮转任意个位置都可以得到 nums 。
 * 示例 5：
 * <p>
 * 输入：nums = [2,1]
 * 输出：true
 * 解释：[1,2] 为有序的源数组。
 * 可以轮转 x = 5 个位置，使新数组从值为 2 的元素开始：[2,1] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * @date 2021/2/7 10:37
 */
public class Check {
    public boolean check(int[] nums) {
        int flag = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 0 || flag >= nums[nums.length - 1];
    }

    @Test
    public void test(){
        Assert.assertTrue(check(new int[]{3,4,5,1,2}));
        Assert.assertTrue(check(new int[]{2,1}));
        Assert.assertTrue(check(new int[]{1,1,1}));
        Assert.assertTrue(check(new int[]{1,2,3}));
        Assert.assertFalse(check(new int[]{2,1,3,4}));
    }

    @Test
    public void errorCase(){
        Assert.assertTrue(check(new int[]{6,10,6}));
    }
}
