package leetcode.array;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author sam
 * @date 11/14/19 16:47
 */
public class MaxProduct {
    public int solution(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        LinkedList<Integer> l = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int temp = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == 0) {
                max = Math.max(0, Math.max(max, findMax(l.toArray(new Integer[l.size()]), temp)));
                l.clear();
                temp = 0;
            } else {
                if (temp == 0) {
                    temp = nums[index];
                } else {
                    temp *= nums[index];
                }
                l.addLast(nums[index]);
            }
        }
        if (l.size() > 0) {
            max = Math.max(max, findMax(l.toArray(new Integer[l.size()]), temp));
        }
        return max;
    }

    private int findMax(Integer[] a, int temp) {
        if (temp >= 0 || a.length == 1) {
            return temp;
        }
        int left = temp;
        int right = temp;
        int leftIndex = 0;
        int rightIndex = a.length - 1;
        while (left < 0 && leftIndex < a.length) {
            left = left / a[leftIndex];
            leftIndex++;
        }
        while (right < 0 && rightIndex > 0) {
            right = right / a[rightIndex];
            rightIndex--;
        }
        return Math.max(left, right);
    }

    /**
     * 标签：动态规划
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)O(n)
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    @Test
    public void test() {
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.solution(new int[]{0, -3, 1, 1}));
        //System.out.println(maxProduct.solution(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct.solution(new int[]{-2, 0, -1}));
        System.out.println(maxProduct.solution(new int[]{0, 2}));
        System.out.println(maxProduct.solution(new int[]{2, 0, 0, 3}));
    }
}
