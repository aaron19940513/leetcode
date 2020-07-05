package Leetcode.weekcompetition.week194;

import org.junit.Assert;
import org.junit.Test;

public class XorOperation {

    public int xorOperation(int n, int start) {
        int ans = start;
        for (int i = 1; i < n; i++) {
            ans ^= start + (2 * i);
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(xorOperation(5, 0), 8);
        Assert.assertEquals(xorOperation(4, 3), 8);
    }
}
