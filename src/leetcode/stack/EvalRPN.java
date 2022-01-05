package leetcode.stack;
import java.util.Stack;

public class EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int t1 = stack.pop();
                int t2 = stack.pop();
                stack.push(t2 + t1);
            } else if (token.equals("-")) {
                int t1 = stack.pop();
                int t2 = stack.pop();
                stack.push(t2 - t1);
            } else if (token.equals("*")) {
                int t1 = stack.pop();
                int t2 = stack.pop();
                stack.push(t2 * t1);
            } else if (token.equals("/")) {
                int t1 = stack.pop();
                int t2 = stack.pop();
                stack.push(t2 / t1);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        EvalRPN evalRPN = new EvalRPN();
        System.out.println(evalRPN.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
