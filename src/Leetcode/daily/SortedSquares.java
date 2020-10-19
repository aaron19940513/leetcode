package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 977. 有序数组的平方 简单
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * @date 10/16/20 8:44
 */
public class SortedSquares {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int[] ans = new int[A.length];
        // 找到最小的非负数
        int start = 0;
        int end = A.length - 1;
        int half = 0;
        int minActiveIndex = 0;
        while (start < end) {
            half = (start + end) / 2;

            if (A[half] > 0) {
                end = half;
            } else if (A[half] == 0) {
                start = half;
                break;
            } else {
                start = half + 1;
            }
        }
        minActiveIndex = start;
        int firstIndex = minActiveIndex - 1;
        int secondIndex = minActiveIndex ;
        int count = 0;
        while (firstIndex > -1 || secondIndex < A.length) {
            if (firstIndex < 0) {
                ans[count++] = (int) Math.pow(A[secondIndex++], 2);
            } else if (secondIndex > A.length - 1) {
                ans[count++] = (int) Math.pow(A[firstIndex--], 2);
            } else {
                if (Math.abs(A[firstIndex]) > A[secondIndex]) {
                    ans[count++] = (int) Math.pow(A[secondIndex++], 2);
                } else {
                    ans[count++] = (int) Math.pow(A[firstIndex--], 2);
                }
            }

        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {0, 1, 9, 16, 100}, sortedSquares(new int[] {-4, -1, 0, 3, 10}));
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[] {4, 9, 9, 49, 121}, sortedSquares(new int[] {-7, -3, 2, 3, 11}));
    }

    @Test
    public void test2() {
        Assert.assertNull(sortedSquares(null));
        Assert.assertArrayEquals(new int[] {}, sortedSquares(new int[] {}));
    }

    @Test
    public void test3() {
        Assert.assertArrayEquals(new int[] {1}, sortedSquares(new int[] {1}));
        Assert.assertArrayEquals(new int[] {1, 1}, sortedSquares(new int[] {-1, 1}));
    }

    @Test
    public void test4() {
        Assert.assertArrayEquals(new int[] {1, 4, 9, 16}, sortedSquares(new int[] {1, 2, 3, 4}));
        Assert.assertArrayEquals(new int[] {1, 4, 9, 16}, sortedSquares(new int[] {-4, -3, -2, -1}));
    }

    @Test
    public void errorCase() {
        Assert.assertArrayEquals(new int[] {1}, sortedSquares(new int[] {-1}));
        Assert.assertArrayEquals(new int[] {1,4,4}, sortedSquares(new int[] {-1,2,2}));
    }
}
