package Leetcode.daily;
import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 31. 下一个排列 中等
 * <p>
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @date 11/10/20 16:29
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        //step1 找到第一个降序的元素
        int flag = nums[nums.length - 1];
        int sentinel;
        for (sentinel = nums.length - 2; sentinel >= 0; sentinel--) {
            if (flag > nums[sentinel]) {
                flag = nums[sentinel];
                break;
            }
            flag = nums[sentinel];
        }
        //step2 找到最后一个比降序元素大的元素
        for (int k = nums.length - 1; k >= 0; k--) {
            if (flag < nums[k]) {
                nums[sentinel] = nums[k];
                nums[k] = flag;
                break;
            }
        }
        // step3 翻转数组
        int end = nums.length - 1;
        sentinel++;
        while (sentinel < end) {
            int swap = nums[sentinel];
            nums[sentinel++] = nums[end];
            nums[end--] = swap;
        }
    }

    @Test
    public void test() {
        int[] param = new int[] {1, 2, 3};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {1, 3, 2});
    }

    @Test
    public void test1() {
        int[] param = new int[] {3, 2, 1};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {1, 2, 3});
    }

    @Test
    public void test2() {
        int[] param = new int[] {1, 1, 5};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {1, 5, 1});
    }

    @Test
    public void test3() {
        int[] param = new int[] {1, 2, 3, 5, 9, 8, 6};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {1, 2, 3, 6, 5, 8, 9});
    }

    @Test
    public void test4() {
        int[] param = new int[] {1, 3, 2};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {2, 1, 3});
    }

    @Test
    public void test5() {
        int[] param = new int[] {5, 9, 8, 7, 6, 4, 3, 2, 1};
        nextPermutation(param);
        Assert.assertArrayEquals(param, new int[] {6, 1, 2, 3, 4, 5, 7, 8, 9});
    }
}
