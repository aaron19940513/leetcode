package leetcode.daily.y2022m10;

import java.util.ArrayList;
import java.util.List;

public class StockSpanner {

    List<Integer> revspan;

    List<Integer> span;

    List<Integer> elements;

    public StockSpanner() {
        revspan = new ArrayList<>();
    }

    public int next(int price) {
        elements.add(price);
        int tempAns = 1;
        if (!span.isEmpty()) {
            revspan.add(-1);
            span.add(1);
        }

        int index = span.size() - 1;

        while (index >= 0) {
            if (price < elements.get(index)) {
                tempAns = 1;
                break;
            } else if (span.get(index) == 1) {
                int tempStart = revspan.get(index);
                int tempEnd = index;
                if (price >= elements.get(tempStart)) {
                    index = tempStart - 1;
                    continue;
                }

                int mid = (tempStart + tempEnd) / 2;
                while(tempStart <=tempEnd){
                    if(elements.get(mid) > price){
                        tempStart = mid +1;
                    }else if(elements.get(mid) == price){
                        tempEnd = mid;
                    }else{
                        tempEnd = mid-1;
                    }
                }
                tempAns = mid;

            } else {
                tempAns += span.get(index);
                index -= span.get(index);
            }
        }

        return tempAns;

    }

    private void monotonicDecrement(int price, int tempAns) {
        elements.add(price);
        span.add(tempAns);
        if (tempAns == 1) {
            Integer preSpan = revspan.get(revspan.size() - 1);
            if (preSpan == -1) {
                revspan.add(revspan.size() - 1);
            } else {
                revspan.add(preSpan);
            }

        } else {
            revspan.add(-1);
        }
    }


    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();

        int param_1 = obj.next(100);

    }
}
