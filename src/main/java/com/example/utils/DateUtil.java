package com.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 */
public class DateUtil extends DateUtils {

    private DateUtil() {

    }

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_V1 = "yyyyMMddHHmmssSSS";
    public static final String DATE_TIMEZ_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String DATEPATTERN = "yyyyMMdd";
    public static final String HOURS_PATTERN = "HH:mm:ss";
    public static final String FIRST_TIME = " 00:00:01";
    public static final String LAST_TIME = " 23:59:59";
    public static final long DAYS = 60 * 60 * 24;
    public static final long HOURS = 60 * 60;

    /**
     * 获取当前日期
     */
    public static Date getDate() {
        return new DateTime().toDate();
    }

    /**
     * 日期转换dateTime
     */
    public static DateTime getDateTime(Date date) {
        return new DateTime(date);
    }

    /**
     * 日期转换为字符串yyyy-MM-dd
     */
    public static String dateformatString(Date date) {
        return DateUtil.date2String(date, null);
    }

    /**
     * 指定格式日期转字符串
     */
    public static String date2String(Date date, String pattern) {
        return DateUtil.getDateTime(date).toString(StringUtils.isNotBlank(pattern) ? pattern : DATE_PATTERN);
    }

    /**
     * 得到以yyyyMMdd格式表示的当前日期字符串
     */
    public static String getDatePatternConnect() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATEPATTERN);
        return sdf.format(new Date());
    }

    /**
     * 得到以yyyy-MM-dd HH:mm:ss格式表示的当前日期字符串
     */
    public static String getDateTimePatternConnect() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 字符串转日期
     */
    public static Date stringFormatDate(String date, String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(StringUtils.isNotBlank(pattern) ? pattern : DATE_PATTERN);
        DateTime dateTime = DateTime.parse(date, format);
        return dateTime.toDate();
    }

    /**
     * 字符串时间
     *
     * @return
     */
    public static Date parse(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date stringFormatDate2(String date, String pattern) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        LocalDate holiday = LocalDate.parse(date, formatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = holiday.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date stringFormatDateTime(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 字符串转日期
     */
    public static Date stringFormatDate(String date) {
        return DateUtil.stringFormatDate(date, null);
    }

    /**
     * 计算两个日期时间差(天)
     */
    public static int gapDays(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.days());
        return p.getDays();
    }

    /**
     * 计算两个日期时间差(月)
     */
    public static int gapMonths(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.months());
        return p.getMonths();
    }

    /**
     * 计算两个日期时间差(年)
     */
    public static int gapYears(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.years());
        return p.getYears();
    }

    /**
     * 计算两个日期时间差(小时)
     */
    public static int gapHours(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.hours());
        return p.getHours();
    }

    /**
     * 计算两个日期时间差(分)
     */
    public static int gapMinutes(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.minutes());
        return p.getMinutes();
    }

    /**
     * 计算两个日期时间差(秒)
     */
    public static int gapSeconds(Date date, Date date1) {
        Period p = new Period(DateUtil.getDateTime(date), DateUtil.getDateTime(date1), PeriodType.seconds());
        return p.getSeconds();
    }

    /**
     * 计算两个日期时间差(秒)
     */
    public static long gapMillis(Date date, Date date1) {
        Duration d = new Duration(DateUtil.getDateTime(date), DateUtil.getDateTime(date1));
        return d.getStandardSeconds();
    }

    /**
     * 获取N天前日期
     */
    public static Date minusDays(Date date, int day) {
        return DateUtil.getDateTime(date).minusDays(day).toDate();
    }

    /**
     * 获取N天后日期
     */
    public static Date plusDays(Date date, int day) {
        return DateUtil.getDateTime(date).plusDays(day).toDate();
    }

    /**
     * 获取N个月前日期
     */
    public static Date minusMonths(Date date, int month) {
        return DateUtil.getDateTime(date).minusMonths(month).toDate();
    }

    /**
     * 获取N个月后日期
     */
    public static Date plusMonths(Date date, int month) {
        return DateUtil.getDateTime(date).plusMonths(month).toDate();
    }

    /**
     * 获取N年前日期
     */
    public static Date minusYears(Date date, int year) {
        return DateUtil.getDateTime(date).minusYears(year).toDate();
    }

    /**
     * 获取N年后日期
     */
    public static Date plusYears(Date date, int year) {
        return DateUtil.getDateTime(date).plusYears(year).toDate();
    }

    /**
     * 获取N周前日期
     */
    public static Date minusWeeks(Date date, int week) {
        return DateUtil.getDateTime(date).minusWeeks(week).toDate();
    }

    /**
     * 获取N周前日期
     */
    public static Date plusWeeks(Date date, int week) {
        return DateUtil.getDateTime(date).plusWeeks(week).toDate();
    }

    /**
     * 获取N小时前日期
     */
    public static Date minusHours(Date date, int hour) {
        return DateUtil.getDateTime(date).minusHours(hour).toDate();
    }

    /**
     * 获取N小时前日期
     */
    public static Date minusMinutes(Date date, int minute) {
        return DateUtil.getDateTime(date).minusMinutes(minute).toDate();
    }

    /**
     * 获取N小时后日期
     */
    public static Date plusHours(Date date, int hour) {
        return DateUtil.getDateTime(date).plusHours(hour).toDate();
    }

    /**
     * 是否比系统时间大
     */
    public static boolean isAfterNow(Date date) {
        return DateUtil.getDateTime(date).isAfterNow();
    }

    /**
     * 是否比系统时间小
     */
    public static boolean isBeforeNow(Date date) {
        return DateUtil.getDateTime(date).isBeforeNow();
    }

    /**
     * 是否与系统时间相等
     */
    public static boolean isEqualNow(Date date) {
        return DateUtil.getDateTime(date).isEqualNow();
    }

    /**
     * 时间比较date大于date1
     */
    public static boolean isEqualNow(Date date, Date date1) {
        return DateUtil.getDateTime(date).isAfter(DateUtil.getDateTime(date1));
    }

    /**
     * 时间比较date小于date1
     */
    public static boolean isBefore(Date date, Date date1) {
        return DateUtil.getDateTime(date).isBefore(DateUtil.getDateTime(date1));
    }

    /**
     * 时间比较date等于date1
     */
    public static boolean isEqual(Date date, Date date1) {
        return DateUtil.getDateTime(date).isEqual(DateUtil.getDateTime(date1));
    }

    /**
     * 获取日期中年份
     */
    public static int getYear(Date date) {
        return new DateTime(date).getYear();
    }

    /**
     * 获取日期中月份
     */
    public static int getMonth(Date date) {
        return new DateTime(date).getMonthOfYear();
    }

    /**
     * 获取日期中日份
     */
    public static int getDay(Date date) {
        return new DateTime(date).getDayOfMonth();
    }


    public static int getHours(Date date) {
        return new DateTime(date).getHourOfDay();
    }

    public static int getMinute(Date date) {
        return new DateTime(date).getMinuteOfHour();
    }

    public static int getSecond(Date date) {
        return new DateTime(date).getSecondOfMinute();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeap(Date date) {
        Property month = DateUtil.getDateTime(date).monthOfYear();
        return month.isLeap();
    }

    /**
     * 获取当前时间到第二天凌晨时间剩余秒数
     */
    public static long surplusSeconds() {
        Date date = DateUtil.plusDays(new Date(), 1);
        DateTime dateTime1 = new DateTime(DateUtil.stringFormatDate(DateUtil.date2String(date, "yyyy-MM-dd")));
        return DateUtil.gapMillis(new Date(), dateTime1.toDate());
    }

    /**
     * 获取指定时间年
     *
     * @param date
     * @return
     */
    public static Integer getYear(String date) {
        LocalDate ld = LocalDate.parse(date);
        return Integer.parseInt(String.valueOf(ld.getYear()));
    }

    /**
     * 获取指定时间月
     *
     * @param date
     * @return
     */
    public static Integer getMonth(String date) {
        LocalDate ld = LocalDate.parse(date);
        return Integer.parseInt(String.valueOf(ld.getMonth().getValue()));
    }

    /**
     * 获取指定时间日
     *
     * @param date
     * @return
     */
    public static Integer getDayOfMonth(String date) {
        LocalDate ld = LocalDate.parse(date);
        return Integer.parseInt(String.valueOf(ld.getDayOfMonth()));
    }

    /**
     * 得到以yyyy-MM-dd格式
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(new Date());
    }


    /**
     * 得到以yyyyMMddHHmmss格式表示的当前日期字符串
     *
     * @param date
     * @return
     */
    public static String getAllDateV1(Object date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN_V1);
        return sdf.format(date);
    }

    /**
     * 根据身份证号计算年龄
     *
     * @param idNo
     * @return
     */
    public static long getAgeByIdNo(String idNo) {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(idNo.substring(6, 14),
                java.time.format.DateTimeFormatter.ofPattern(DATEPATTERN));
        return ChronoUnit.YEARS.between(birthDate, now);
    }

    /**
     * 根据身份证号计算毫秒
     *
     * @param idNo
     * @return
     */
    public static long getMillisByIdNo(String idNo) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATEPATTERN);
        long time = 0L;
        try {
            time = sdf.parse(idNo.substring(6, 14)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Date getFirstDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.getWeekYear();
        calendar.getFirstDayOfWeek();
        // 获得当前的年
        int weekYear = calendar.get(Calendar.YEAR);
        // 获得当前日期属于今年的第几周
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        // 获得指定年的第几周的开始日期
        calendar.setWeekDate(weekYear, weekOfYear, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 获取 当月几号
     *
     * @param date 日期
     * @return 几号
     */
    public static Integer getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
