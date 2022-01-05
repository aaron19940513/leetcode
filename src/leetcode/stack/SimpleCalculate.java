package leetcode.stack;
import java.util.ArrayList;
import java.util.List;

public class SimpleCalculate {
    public int calculate(String s) {
        List<String> elements = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/' && stringBuffer.length() > 0) {
                elements.add(stringBuffer.toString());
                stringBuffer.setLength(0);
                elements.add(String.valueOf(c));
            } else if (c == ' ') {

            } else {
                stringBuffer.append(c);
            }
        }
        elements.add(stringBuffer.toString());
        stringBuffer.setLength(0);
        List<String> reverseStack = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            if (element.equals("*")) {
                int e1 = Integer.valueOf(elements.get(i + 1));
                int e2 = Integer.valueOf(reverseStack.remove(reverseStack.size() - 1));
                reverseStack.add(String.valueOf(e1 * e2));
                i++;
            } else if (element.equals("/")) {
                int e1 = Integer.valueOf(elements.get(i + 1));
                int e2 = Integer.valueOf(reverseStack.remove(reverseStack.size() - 1));
                reverseStack.add(String.valueOf(e2 / e1));
                i++;
            } else {
                reverseStack.add(element);
            }
        }

        int result = Integer.valueOf(reverseStack.get(0));
        for (int i = 0; i < reverseStack.size() - 1 && reverseStack.size() >= 3; ) {
            int e1 = result;
            String sign = reverseStack.get(++i);
            int e2 = Integer.valueOf(reverseStack.get(++i));
            if (sign.equals("+")) {
                result = e2 + e1;
            } else if (sign.equals("-")) {
                result = e1 - e2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SimpleCalculate simpleCalculate = new SimpleCalculate();
        System.out.println(simpleCalculate.calculate("1-1+1"));
        System.out.println(simpleCalculate.calculate("3+2*2"));
        System.out.println(simpleCalculate.calculate(" 3/2 "));
        System.out.println(simpleCalculate.calculate(" 3+5 / 2 "));

    }
}
