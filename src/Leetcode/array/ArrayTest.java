package Leetcode.array;

import org.junit.Test;

import java.util.Random;

public class ArrayTest {
    @Test
    public void testRandom() {
        Random random = new Random();
        int index = 0;
        while (index < 100) {
            index++;
            System.out.println(random.nextInt(11));
        }
    }

    @Test
    public void test() {
        DisruptArrays obj = new DisruptArrays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        int[] param_2 = obj.shuffle();
        for (int i : param_2) {
            System.out.print(i);
        }
        System.out.println("--------------------------");
        int[] param_1 = obj.reset();
        for (int i : param_1) {
            System.out.print(i);
        }
    }
}
