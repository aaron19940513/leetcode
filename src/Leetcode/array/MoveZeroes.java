package Leetcode.array;

import org.junit.Test;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zeroNum = 0;
        for (int index = 0; index < nums.length - zeroNum; index++) {
            while (index < nums.length - zeroNum && nums[index + zeroNum] == 0) {
                zeroNum++;
            }
            nums[index] = index + zeroNum < nums.length ? nums[index + zeroNum] : 0;
        }
        while (zeroNum > 0) {
            nums[nums.length - zeroNum] = 0;
            zeroNum--;
        }
    }

    @Test
    public void test() {
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(new int[]{0, 0, 1, 0});
    }
}
