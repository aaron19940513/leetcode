package Leetcode;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @author Sam Gao
 * @date 04/21/21 10:36
 */
public class StreamTest {
    public static void main(String[] args) {
        StreamTest streamTest =new StreamTest();
        streamTest.test();
    }

    public void test() {
        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Product> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);
        Map<String, Integer> collect = prodList.stream().collect(
            Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(
                Product::getNum)), Optional::get), Product::getNum)));

    }

    class Product{
        Long id;
        Integer num;
        BigDecimal price;
        String name;
        String category;
        public Product(Long id, Integer num, BigDecimal price, String name, String category) {
            this.id = id;
            this.num = num;
            this.price = price;
            this.name = name;
            this.category = category;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }


}
