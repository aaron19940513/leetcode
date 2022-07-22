package leetcode.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

public class SqlSplit {

    @Test
    public void test() throws Throwable {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\jianchao.gao\\Downloads" +
                "\\test1.txt")))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String prefix = "SELECT count(DISTINCT user_id) new_users FROM gas_user_business_analysis WHERE is_new_user = 1 AND first_pay_date BETWEEN '2021-10-01' and '2021-10-30' AND (";
            String postfix = sb.substring(sb.lastIndexOf("(") +1);


            List<String> split = Arrays.asList(postfix.split(",").clone());
            StringBuilder sb1 = new StringBuilder();
            for (List<String> strings : Lists.partition(split, 2000)) {
                sb1.append(" gas_station_id in ( ").append(strings.stream().collect(Collectors.joining(","))).append(" ) or ");
            }
            System.out.println(prefix + sb1.toString());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\jianchao.gao\\Downloads" +
                    "\\test.txt")));
            bufferedWriter.write(prefix + sb1.toString());
            bufferedWriter.flush();
        }

    }
}
