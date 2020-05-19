package com.ddz.utils.base;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author
 */
public class DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDD = "yyyy年MM月dd日";

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 计算距离现在多少分钟
     *
     * @param date
     * @return
     */
    public static long getTimeBeforeMin(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long min = l / (60 * 1000);
        return min;
    }

    /**
     * 在当前时间上 + 多少时间
     *
     * @param s 多少秒后
     * @return
     */
    public static String getTimeAfterSec(Long s) {
        Date now = new Date();
        long l = now.getTime() + s;
        ;
        return parseDateToStr(YYYYMMDDHHMMSS, new Date(l));
    }

    public static String Timestamp2String(Long timeMillis) {
        Date ts = new Date(timeMillis * 1000);
        String tsStr = null;
        DateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            tsStr = sdf.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    public static void main(String[] args) {
        try {
            String n = new String("201 鍟嗘埛璁㈠崟鍙烽噸澶峕".getBytes("GBK"), "UTF-8");
            System.out.println(n);
        } catch (UnsupportedEncodingException w) {
            w.printStackTrace();
        }
    }
}
