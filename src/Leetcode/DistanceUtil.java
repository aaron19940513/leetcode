package leetcode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class DistanceUtil {
    private static final double MAX_LAT = 90;
    private static final double MIN_LAT = -90;
    private static final double MAX_LNG = 180;
    private static final double MIN_LNG = -180;
    private static final int LAT_LENGTH = 30;
    private static final int LNG_LENGTH = 30;
    private static final double LAT_UNIT = 0.2090006553672317D;
    private static final double LNG_UNIT = 0.17979144192736426;
    private static final String[] BASE_32_LOOK_UP =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h",
                    "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    // 计算两个经纬度之间的距离 (km)
    public static double calcDistance(double lng1, double lat1, double lng2, double lat2) {
        double d = calc(lng1, lat1, lng2, lat2);
        BigDecimal bd =  BigDecimal.valueOf(d / 1000.0);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double calcDistanceM(double lng1, double lat1, double lng2, double lat2) {
        double d = calc(lng1, lat1, lng2, lat2);
        BigDecimal bd = BigDecimal.valueOf(d);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    static double calc(double lng1, double lat1, double lng2, double lat2) {
        double r = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        double a = lat1 - lat2;
        double b = (lng1 - lng2) * Math.PI / 180.0;
        double sa2;
        double sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        return 2 * r * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
    }

    public static Set<String> around(double lat, double lng) {
        Set<String> result = Sets.newHashSet();
        result.add(encode(lat, lng));
        result.add(encode(lat + LAT_UNIT, lng));
        result.add(encode(lat - LAT_UNIT, lng));
        result.add(encode(lat, lng + LNG_UNIT));
        result.add(encode(lat, lng - LNG_UNIT));
        result.add(encode(lat + LAT_UNIT, lng + LNG_UNIT));
        result.add(encode(lat + LAT_UNIT, lng - LNG_UNIT));
        result.add(encode(lat - LAT_UNIT, lng + LNG_UNIT));
        result.add(encode(lat - LAT_UNIT, lng - LNG_UNIT));
        return result;
    }

    public static String encode(double lat, double lng) {
        List<Character> latList = Lists.newArrayList();
        List<Character> lngList = Lists.newArrayList();
        convert(MIN_LAT, MAX_LAT, lat, latList, LAT_LENGTH);
        convert(MIN_LNG, MAX_LNG, lng, lngList, LNG_LENGTH);
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < latList.size(); index++) {
            sb.append(lngList.get(index)).append(latList.get(index));
        }
        return base32Encode(sb.toString());
    }

    private static void convert(double min, double max, double value, List<Character> list,
                                int length) {
        if (list.size() > (length - 1)) {
            return;
        }
        double mid = (max + min) / 2;
        if (value < mid) {
            list.add('0');
            convert(min, mid, value, list, length);
        } else {
            list.add('1');
            convert(mid, max, value, list, length);
        }
    }

    private static String base32Encode(final String str) {
        StringBuilder sb = new StringBuilder();
        for (int start = 0; start < str.length(); start = start + 5) {
            String unit = str.substring(start, start + 5);
            int i = convertToIndex(unit);
            sb.append(BASE_32_LOOK_UP[i]);
        }
        return sb.toString();
    }


    private static int convertToIndex(String str) {
        int length = str.length();
        int result = 0;
        for (int index = 0; index < length; index++) {
            result += str.charAt(index) == '0' ? 0 : 1 << (length - 1 - index);
        }
        return result;
    }

    @Test
    public void test(){
        System.out.println(calcDistance(104.075005,30.543238,104.086418,30.541872));
        System.out.println(calcDistanceM(104.075005,30.543238,104.086418,30.541872));
    }

    @Test
    public void testGeoHash(){
        System.out.println(encode(30.541872, 104.086418));
    }

}
