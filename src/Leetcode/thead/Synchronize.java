package Leetcode.thead;
/**
 * @author Sam Gao
 * @date 08/13/20 11:09
 */
public class Synchronize {
    public static void main(String[] args) {
        synchronized (Synchronize.class) {
            System.out.println("Synchronize");
            System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
            System.out.println(Integer.toBinaryString(-1));

            System.out.println(Integer.toBinaryString(-1<<(Integer.SIZE - 3)));
        }
    }
}
