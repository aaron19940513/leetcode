package leetcode;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GeoHashUtils {

    private static final double MAX_LAT = 90;
    private static final double MIN_LAT = -90;
    private static final double MAX_LNG = 180;
    private static final double MIN_LNG = -180;
    private static final int LAT_LENGTH = 30;
    private static final int LNG_LENGTH = 30;
    private static double LAT_UNIT = 0.2090006553672317D;
    private static double LNG_UNIT = 0.17979144192736426;
    private static final String[] BASE_32_LOOK_UP =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h",
                    "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private GeoHashUtils() {
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

    /**
     * 注意调用around之前请先调用calcLatLngUnit为单元大小重新赋值
     *
     * @return java.util.List<java.lang.String>
     * @Author long.li
     * @Description 获取经纬度所在的周围9宫格geohash值
     * @Date 16:11 2018/8/27
     * @Param [lat, lng]
     **/
    @Deprecated
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


    /**
     * 获取经纬度所在的周围9宫格geohash值
     *
     * @param lat
     * @param lng
     * @param c   需要的位数
     * @return
     */
    @Deprecated
    public static List<String> around(double lat, double lng, int c) {
        return around(lat, lng).stream().map(t -> t.substring(0, c)).distinct().collect(Collectors.toList());
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

    /**
     * 获取经纬度所在的周围9宫格geohash值
     * <p>
     * 根据距离计算出需要位数
     *
     * @param lat
     * @param lng
     * @param distance 距离 单位：米
     * @return
     */
    public static List<String> around(double lat, double lng, Double distance) {
        if (Objects.isNull(distance)) {
            return Lists.newArrayList();
        }
        // 根据距离确定前面多少位hash相同
        int geoHashLength = deduceGeoHashLengthByPrecisionDistance(distance);
        if (geoHashLength == -1) {
            return Lists.newArrayList();
        }
        // 需要重新计算最小单元格
        AroundGeoHash aroundGeoHash = new AroundGeoHash();
        aroundGeoHash.calcLatLngUnit(geoHashLength);
        return aroundGeoHash.around(lat, lng).stream().map(t -> t.substring(0, geoHashLength)).distinct().collect(Collectors.toList());
    }

    /**
     * 根据distance距离确定geohash前面多少位相同 一共12位 按照距离对应的位数进行模糊查询就好了
     *
     * @param precisionDistance 精度距离，单位是米
     * @return 下标
     */
    private static int deduceGeoHashLengthByPrecisionDistance(Double precisionDistance) {
        Objects.requireNonNull(precisionDistance);
        return GeoHashLengthDigitsPrecision.deduceGeoHashLength(precisionDistance);
    }

    /**
     * 参考维基百科：http://en.wikipedia.org/wiki/Geohash geohash长度与数字精度关系
     * <p>
     * 1 2500km;  2 630km; 3 78km; 4 30km
     * <p>
     * 5 2.4km; 6 610m; 7 76m; 8 19m
     * <p>
     * 下列枚举部分
     */

    enum GeoHashLengthDigitsPrecision {
        /**
         * 2.4km hash长度5
         */
        TWO_THOUSAND_AND_FOUR_HUNDRED_METER(2400, 5),
        /**
         * 20km hash长度4
         */
        TWENTY_THOUSAND_METER(20000, 4),
        /**
         * 78km hash长度3
         */
        SEVENTY_EIGHT_THOUSAND_METER(78000, 3),
        /**
         * 630km hash长度2
         */
        SIX_HUNDRED_AND_THIRTY_THOUSAND(630000, 2),
        /**
         * 2500km hash长度1
         */
        TWO_MILLION_AND_FIVE_HUNDRED_THOUSAND(2500000, 1);

        private final int precisionDistance;

        GeoHashLengthDigitsPrecision(int precisionDistance, int geoHashLength) {
            this.precisionDistance = precisionDistance;
            this.geoHashLength = geoHashLength;
        }

        private final int geoHashLength;


        public static int deduceGeoHashLength(Double distance) {
            for (GeoHashLengthDigitsPrecision precision : values()) {
                if (distance < precision.precisionDistance) {
                    return precision.geoHashLength;
                }
            }
            return -1;
        }
    }

    public static String encode(double lat, double lng, int length) {
        return encode(lat, lng).substring(0, length);
    }


    static class AroundGeoHash {
        private double LAT_UNIT;
        private double LNG_UNIT;

        /**
         * 为什么要除5 参考base32Encode方法，需要还原对应的二进制位数
         *
         * @param hashLength
         */
        private void calcLatLngUnit(int hashLength) {
            int latLength = (hashLength * 5) / 2;
            int lngLength;
            if (hashLength % 2 == 0) {
                lngLength = latLength;
            } else {
                lngLength = latLength + 1;
            }
            this.LAT_UNIT = (MAX_LAT - MIN_LAT) / (1 << latLength);
            this.LNG_UNIT = (MAX_LNG - MIN_LNG) / (1 << lngLength);
        }

        /**
         * 注意调用around之前请先调用calcLatLngUnit为单元大小重新赋值
         *
         * @return java.util.List<java.lang.String>
         * @Description 获取经纬度所在的周围9宫格geohash值
         * @Date 16:11 2018/8/27
         * @Param [lat, lng]
         **/
        public Set<String> around(double lat, double lng) {
            Set<String> result = Sets.newHashSet();
            result.add(encode(lat, lng));
            result.add(encode(lat + this.LAT_UNIT, lng));
            result.add(encode(lat - this.LAT_UNIT, lng));
            result.add(encode(lat, lng + this.LNG_UNIT));
            result.add(encode(lat, lng - this.LNG_UNIT));
            result.add(encode(lat + this.LAT_UNIT, lng + this.LNG_UNIT));
            result.add(encode(lat + this.LAT_UNIT, lng - this.LNG_UNIT));
            result.add(encode(lat - this.LAT_UNIT, lng + this.LNG_UNIT));
            result.add(encode(lat - this.LAT_UNIT, lng - this.LNG_UNIT));
            return result;
        }
    }


    public static void main(String[] args) throws Exception {

        System.out.println(encode(30.558098, 104.07217));

        System.out.println(encode(30.543995, 104.077086));

        List<String> around = around(30.543238, 104.075005, 1000D);
        List<String> around1 = around(30.543995, 104.077086, 10000D);
//        double[] geo = geohash.decode(s);
//        System.out.println(geo[0]+" "+geo[1]);
    }
}
