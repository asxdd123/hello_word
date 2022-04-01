package com.itheima.springbppt.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM",
            "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月",
            "yyyy"};

    /**
     * 得到日期字符串 ，转换格式（yyyy-MM-dd）
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(long dateTime, String pattern) {
        return formatDate(new Date(dateTime), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (date != null){
//            if (StringUtils.isNotBlank(pattern)) {
//                formatDate = DateFormatUtils.format(date, pattern);
//            } else {
//                formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
//            }
            if (StringUtils.isBlank(pattern)) {
                pattern = "yyyy-MM-dd";
            }
            formatDate = FastDateFormat.getInstance(pattern).format(date);
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
//        return DateFormatUtils.format(new Date(), pattern);
        return FastDateFormat.getInstance(pattern).format(new Date());
    }



    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }



    /**
     * 日期型字符串转化为日期 格式   see to DateUtils#parsePatterns
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }



    /**
     * 格式化为日期范围字符串
     * @param beginDate 2018-01-01
     * @param endDate 2018-01-31
     * @return 2018-01-01 ~ 2018-01-31

     */
    public static String formatDateBetweenString(Date beginDate, Date endDate){
        String begin = DateUtils.formatDate(beginDate);
        String end = DateUtils.formatDate(endDate);
        if (StringUtils.isNoneBlank(begin, end)){
            return begin + " ~ " + end;
        }
        return null;
    }

    /**
     * 解析日期范围字符串为日期对象
     * @param dateString 2018-01-01 ~ 2018-01-31
     * @return new Date[]{2018-01-01, 2018-01-31}

     */
    public static Date[] parseDateBetweenString(String dateString){
        Date beginDate = null; Date endDate = null;
        if (StringUtils.isNotBlank(dateString)){
            String[] ss = StringUtils.split(dateString, "~");
            if (ss != null && ss.length == 2){
                String begin = StringUtils.trim(ss[0]);
                String end = StringUtils.trim(ss[1]);
                if (StringUtils.isNoneBlank(begin, end)){
                    beginDate = DateUtils.parseDate(begin);
                    endDate = DateUtils.parseDate(end);
                }
            }
        }
        return new Date[]{beginDate, endDate};
    }

    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;
    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }
    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }




    /**
     * 日期比较
     * @param date1 日期参数1
     * @param date2 日期参数2
     * @return 1 date1>date2;-1 date1<date2;0 date1=date2
     */
    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


}
