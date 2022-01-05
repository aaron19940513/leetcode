package leetcode.daily.y2021m01;
import org.junit.Assert;
import org.junit.Test;

/**
 * 189. 旋转数组 mid
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @date 01/08/21 10:29
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        //rotate1(nums, k);
        rotate2(nums, k);
    }

    //方法1,原地移位
    public void rotate1(int[] nums, int k) {
        if (nums.length == 0 || k % nums.length == 0) {
            return;
        }
        int n = nums.length;
        int t1 = nums[0];
        int t2;
        int index1 = 0;
        int index2;
        for (int i = 0; i < nums.length; i++) {
            index2 = (index1 + k) % n;
            t2 = nums[index2];
            nums[index2] = t1;
            index1 = index2;
        }
    }

    //方法2，翻转
    public void rotate2(int[] nums, int k) {
        if (nums.length < 2 || k % nums.length == 0) {
            return;
        }
        k = k % nums.length;
        flip(nums, 0, nums.length - k - 1);
        flip(nums, nums.length - k, nums.length - 1);
        flip(nums, 0, nums.length - 1);
    }

    private void flip(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    @Test
    public void test() {
        int[] a = new int[] {-1, -100, 3, 99};
        rotate(a, 2);
        Assert.assertArrayEquals(new int[] {3, 99, -1, -100}, a);
    }

    @Test
    public void test1() {
        int[] a = new int[] {1, 2, 3, 4, 5, 6, 7};
        rotate(a, 3);
        Assert.assertArrayEquals(new int[] {5, 6, 7, 1, 2, 3, 4}, a);
    }
}
