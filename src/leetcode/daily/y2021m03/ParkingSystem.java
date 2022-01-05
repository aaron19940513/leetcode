package leetcode.daily.y2021m03;
import org.junit.Assert;

/**
 * @author Sam Gao
 * @date 03/19/21 8:45
 */
public class ParkingSystem {
    int[] parking = new int[4];

    public ParkingSystem(int big, int medium, int small) {
        parking[1] = big;
        parking[2] = medium;
        parking[3] = small;
    }

    public boolean addCar(int carType) {
        if (parking[carType] > 0) {
            parking[carType]--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ParkingSystem obj = new ParkingSystem(1, 1, 0);
        Assert.assertTrue(obj.addCar(1));
        Assert.assertTrue(obj.addCar(2));
        Assert.assertFalse(obj.addCar(3));
        Assert.assertFalse(obj.addCar(1));
    }
}
