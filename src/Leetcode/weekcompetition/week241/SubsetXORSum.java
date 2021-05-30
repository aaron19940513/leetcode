package Leetcode.weekcompetition.week241;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5759. 找出所有子集的异或总和再求和 显示英文描述
 * 通过的用户数2306
 * 尝试过的用户数2422
 * 用户总通过次数2329
 * 用户总提交次数2699
 * 题目难度Easy
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 * <p>
 * 例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 * <p>
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 * <p>
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3]
 * 输出：6
 * 解释：[1,3] 共有 4 个子集：
 * - 空子集的异或总和是 0 。
 * - [1] 的异或总和为 1 。
 * - [3] 的异或总和为 3 。
 * - [1,3] 的异或总和为 1 XOR 3 = 2 。
 * 0 + 1 + 3 + 2 = 6
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,6]
 * 输出：28
 * 解释：[5,1,6] 共有 8 个子集：
 * - 空子集的异或总和是 0 。
 * - [5] 的异或总和为 5 。
 * - [1] 的异或总和为 1 。
 * - [6] 的异或总和为 6 。
 * - [5,1] 的异或总和为 5 XOR 1 = 4 。
 * - [5,6] 的异或总和为 5 XOR 6 = 3 。
 * - [1,6] 的异或总和为 1 XOR 6 = 7 。
 * - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 * 示例 3：
 * <p>
 * 输入：nums = [3,4,5,6,7,8]
 * 输出：480
 * 解释：每个子集的全部异或总和值之和为 480 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 *
 * @date 2021/5/16 10:37
 */
public class SubsetXORSum {
    int ans = 0;

    public int subsetXORSum(int[] nums) {
        subsetXORSum(nums, 0, 0);
        return ans;
    }

    private void subsetXORSum(int[] nums, int index, int xor) {
        if (index == nums.length - 1) {
            ans += xor;
            ans += xor ^ nums[index];
        } else {
            subsetXORSum(nums, index + 1, xor);
            subsetXORSum(nums, index + 1, xor ^ nums[index]);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(6, subsetXORSum(new int[] {1, 3}));
        ans=0;
        Assert.assertEquals(28, subsetXORSum(new int[] {5, 1, 6}));
        ans=0;
        Assert.assertEquals(480, subsetXORSum(new int[] {3, 4, 5, 6, 7, 8}));
    }

}
