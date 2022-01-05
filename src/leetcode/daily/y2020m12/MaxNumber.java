package leetcode.daily.y2020m12;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

/**
 * 321. 拼接最大数 困难
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 * <p>
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 * <p>
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/02/20 13:31
 */
public class MaxNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0; i <= k; i++) {
            if (nums1.length >= i && nums2.length >= k - i) {
                Deque<Integer> stack1 = getStack(nums1, i);
                Deque<Integer> stack2 = getStack(nums2, k - i);
                ans = merger(ans, stack1, stack2);
            }
        }
        return ans;
    }

    private int[] merger(int[] ans, Deque<Integer> stack1, Deque<Integer> stack2) {
        int[] temp = new int[ans.length];
        for (int i = 0; i < temp.length; i++) {
            if (stack1.size() == 0) {
                temp[i] = stack2.pollFirst();
            } else if (stack2.size() == 0) {
                temp[i] = stack1.pollFirst();
            } else {
                if (stack1.getFirst() > stack2.getFirst()) {
                    temp[i] = stack1.pollFirst();
                } else if (stack1.getFirst() < stack2.getFirst()) {
                    temp[i] = stack2.pollFirst();
                } else {
                    if(stack1.size()==1){
                        temp[i] = stack2.pollFirst();
                    }else if(stack2.size()==1){
                        temp[i] = stack1.pollFirst();
                    }else{
                        Integer integer1 = stack1.pollFirst();
                        Integer integer2= stack2.pollFirst();
                        if(stack1.peek() > stack2.peek() ){
                            temp[i] = integer1;
                            stack2.addFirst(integer2);
                        }else{
                            temp[i] = integer2;
                            stack1.addFirst(integer1);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] > temp[i]) {
                return ans;
            } else if (ans[i] < temp[i]) {
                return temp;
            }
        }

        return ans;
    }

    private Deque<Integer> getStack(int[] nums, int length) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.size() == 0 || stack.size() + nums.length - i == length) {
                stack.addLast(nums[i]);
            } else {
                while (stack.size() > 0 && stack.getLast() < nums[i] && stack.size() + nums.length - i > length) {
                    stack.removeLast();
                }
                stack.addLast(nums[i]);
            }
        }
        return stack;
    }


    @Test
    public void test() {
        System.out.println(getStack(new int[] {9, 4, 3, 8, 5}, 3));
        System.out.println(getStack(new int[] {9, 4, 3, 8, 5}, 4));
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[] {9, 8, 6, 5, 3}, maxNumber(new int[] {3, 4, 6, 5}, new int[] {9, 1, 2, 5, 8, 3}, 5));
        Assert.assertArrayEquals(new int[] {6, 7, 6, 0, 4}, maxNumber(new int[] {6, 7}, new int[] {6, 0, 4}, 5));
        Assert.assertArrayEquals(new int[] {9, 8, 9}, maxNumber(new int[] {3, 9}, new int[] {8, 9}, 3));
    }

    @Test
    public void test3() {

        System.out.println(Arrays.toString(maxNumber(new int[] {2, 5, 6, 4, 4, 0}, new int[] {7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));

    }

}
