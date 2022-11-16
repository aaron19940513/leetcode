package leetcode.daily.y2022m10;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class SumSubarrayMins {

    public static int mod = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        int[] left = new int[arr.length];
        Stack<Integer> leftStack = new Stack<>();

        int[] right = new int[arr.length];
        Stack<Integer> rightStack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!leftStack.isEmpty() && arr[i] <= arr[leftStack.peek()]) {
                leftStack.pop();
            }

            left[i] = leftStack.isEmpty() ? -1 : leftStack.peek();
            leftStack.push(i);
        }



        return 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(17, sumSubarrayMins(new int[]{3, 1, 2, 4}));
        Assert.assertEquals(444, sumSubarrayMins(new int[]{11, 81, 94, 43, 3}));
    }
}
