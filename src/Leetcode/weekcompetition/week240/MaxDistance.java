package Leetcode.weekcompetition.week240;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5751. 下标对中的最大距离 显示英文描述
 * 通过的用户数1818
 * 尝试过的用户数2602
 * 用户总通过次数1846
 * 用户总提交次数5405
 * 题目难度Medium
 * 给你两个 非递增 的整数数组 nums1​​​​​​ 和 nums2​​​​​​ ，数组下标均 从 0 开始 计数。
 *
 * 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​
 *
 * 返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
 *
 * 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * 输出：2
 * 解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
 * 最大距离是 2 ，对应下标对 (2,4) 。
 * 示例 2：
 *
 * 输入：nums1 = [2,2,2], nums2 = [10,10,1]
 * 输出：1
 * 解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
 * 最大距离是 1 ，对应下标对 (0,1) 。
 * 示例 3：
 *
 * 输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * 输出：2
 * 解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
 * 最大距离是 2 ，对应下标对 (2,4) 。
 * 示例 4：
 *
 * 输入：nums1 = [5,4], nums2 = [3,2]
 * 输出：0
 * 解释：不存在有效下标对，所以返回 0 。
 * @date 2021/5/9 11:07
 */
public class MaxDistance {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int temp = -1+ans;
            for (int j = i + ans; j < nums2.length; j++) {
                if (nums2[j] >= nums1[i]) {
                    temp++;
                    continue;
                }
                break;
            }
            ans = Math.max(ans, temp );
            if (nums2.length - 1 - i <= ans) {
                return ans;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, maxDistance(new int[] {55, 30, 5, 4, 2}, new int[] {100, 20, 10, 10, 5}));
        Assert.assertEquals(1, maxDistance(new int[] {2,2,2}, new int[] {10, 10,1}));
        Assert.assertEquals(2, maxDistance(new int[] {30,29,19,5}, new int[] {25,25,25,25,25}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(10, maxDistance(new int[] {9820,8937,7936,4855,4830,4122,2327,1342,1167,815,414}, new int[] {9889,9817,9800,9777,9670,9646,9304,8977,8974,8802,8626,8622,8456}));
    }
}
