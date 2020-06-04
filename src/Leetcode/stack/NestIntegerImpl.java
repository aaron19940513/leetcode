package Leetcode.stack;
import java.util.List;

public class NestIntegerImpl implements NestedInteger {
    Integer value;
    List<NestedInteger> nestedIntegers;

    public NestIntegerImpl(Integer value) {
        this.value = value;
    }

    public NestIntegerImpl(List<NestedInteger> value) {
        nestedIntegers = value;
    }

    @Override
    public boolean isInteger() {
        return this.value != null;
    }

    @Override
    public Integer getInteger() {
        return this.value;
    }

    @Override
    public List<NestedInteger> getList() {
        return nestedIntegers;
    }
}
