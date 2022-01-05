package leetcode.thead;
/**
 * @author Sam Gao
 * @date 08/13/20 11:09
 */
public class Synchronize {
    public static void main(String[] args) {
        Widget widget = new Widget();
        widget.start();
        for (; ; ) {
            if (!widget.getFlag()) {
                System.out.println(widget.getFlag());
            }
        }

    }
}


class Widget extends Thread {

    private volatile Boolean flag = true;

    public Boolean getFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println("flag has change to false");
    }
}
