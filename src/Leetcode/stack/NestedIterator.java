package Leetcode.stack;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class NestedIterator implements Iterator<Integer> {

    Stack<List<NestedInteger>> stack = new Stack<>();
    Stack<Integer> indexStack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList);
        indexStack.push(0);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        Integer ans = 0;
        List<NestedInteger> tail = stack.peek();
        Integer index = indexStack.peek();
        NestedInteger nestedInteger = tail.get(index);
        if (nestedInteger.isInteger()) {
            ans = nestedInteger.getInteger();
            indexStack.pop();
            indexStack.push(index + 1);
        } else {
            indexStack.pop();
            indexStack.push(index + 1);
            stack.push(nestedInteger.getList());
            indexStack.push(0);
            return next();
        }
        return ans;
    }

    @Override
    public boolean hasNext() {
        if (stack.size() == 0) {
            return false;
        }
        List<NestedInteger> tail = stack.peek();
        Integer index = indexStack.peek();
        if (index < tail.size() && isEmpty(tail.get(index))) {
            indexStack.pop();
            indexStack.push(index + 1);
            return hasNext();
        }
        if (index >= tail.size()) {
            stack.pop();
            indexStack.pop();
            return hasNext();
        }
        return true;
    }

    private boolean isEmpty(NestedInteger nestedInteger) {
        if (nestedInteger.isInteger()) {
            return false;
        } else {
            for (NestedInteger t : nestedInteger.getList()) {
                if (!isEmpty(t)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        NestedInteger nestedInteger = new NestIntegerImpl(1);
        NestedInteger nestedInteger1 = new NestIntegerImpl(2);
        NestedInteger nestedInteger2 = new NestIntegerImpl(3);
        NestedInteger nestedInteger3 = new NestIntegerImpl(4);
        NestedInteger nestedInteger4 = new NestIntegerImpl(5);
        List<NestedInteger> nestedIntegers = new ArrayList<>();
        nestedIntegers.add(nestedInteger);
        nestedIntegers.add(nestedInteger1);
        List<NestedInteger> nestedIntegers1 = new ArrayList<>();
        nestedIntegers1.add(nestedInteger3);
        nestedIntegers1.add(nestedInteger4);

        List<NestedInteger> nestedIntegers2 = new ArrayList<>();
        nestedIntegers2.add(new NestIntegerImpl(6));
        List<NestedInteger> nestedIntegers3 = new ArrayList<>();
        nestedIntegers3.add(new NestIntegerImpl(7));
        nestedIntegers3.add(new NestIntegerImpl(nestedIntegers2));
        List<NestedInteger> param = new ArrayList<>();
        param.add(new NestIntegerImpl(nestedIntegers));
        param.add(nestedInteger2);
        param.add(new NestIntegerImpl(nestedIntegers3));
        param.add(new NestIntegerImpl(nestedIntegers1));

        NestedIterator nestedIterator = new NestedIterator(param);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
        // List<NestedInteger> nestedIntegers4 = new ArrayList<>();
        // nestedIntegers4.add(new NestIntegerImpl(8));
        // List<NestedInteger> nestedIntegers6 = new ArrayList<>();
        // nestedIntegers6.add(new NestIntegerImpl(nestedIntegers4));
        // nestedIntegers6.add(new NestIntegerImpl(new ArrayList<>()));
        // NestedIterator nestedIterator1 = new NestedIterator(nestedIntegers6);
        // while (nestedIterator1.hasNext()) {
        //     System.out.println(nestedIterator1.next());
        // }

    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */