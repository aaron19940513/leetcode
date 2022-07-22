package leetcode.hessian;

public class LeakTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            try {
                //eetcode.hessian.User$DiscountMap
                Thread.sleep(10);
                Class<?> aClass = Class.forName("leetcode.hessian.User$DiscountMap", false, Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                //System.out.println(e.getMessage());
            }
        }
    }
}
