package com.yan.basedemo.utils;

import com.yan.basedemo.bean.SubscribeDay;
import com.yan.basedemo.bean.SubscribeTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: april
 * Date: 18/5/25
 * Time: 下午12:14
 */
public class SubScribeTimesUtils {


    private final static String FORMAT_DATE = "yyyy-MM-dd";

    private final static String FORMAT_TIME = "HH:mm";

    private SubScribeTimesUtils(){

    }
    /**
     * 获取天
     *
     * @return
     */
    public static List<SubscribeDay> getSubscribeDays(int day, int timeDivisions) {
        int dayMinute = 24 * 60;//一天的分钟数
        int singleTimeGaps = dayMinute / timeDivisions;//时间间隔～分钟数

        List<SubscribeDay> days = new ArrayList<>(day);

        for (int i = 0; i < day; i++) {
            switch (i) {
                case 2:
                    SubscribeDay subscribeDay3 = getSubscribeDay(2, timeDivisions, singleTimeGaps);
                    subscribeDay3.setDateStringShow(subscribeDay3.getDateStringShow() + "后天");
                    days.add(subscribeDay3);
                    break;
                case 1:
                    SubscribeDay subscribeDay2 = getSubscribeDay(1, timeDivisions, singleTimeGaps);
                    subscribeDay2.setDateStringShow(subscribeDay2.getDateStringShow() + "明天");
                    days.add(subscribeDay2);
                    break;
                case 0:
                    SubscribeDay subscribeDay1 = getSubscribeDay(0, timeDivisions, singleTimeGaps);
                    subscribeDay1.setDateStringShow("今天");
                    days.add(subscribeDay1);
                    break;
//                default:
//                    SubscribeDay subscribeDay0 = getSubscribeDay(0, timeDivisions, singleTimeGaps);
//                    subscribeDay0.setDateStringShow("今天");
//                    days.add(subscribeDay0);
//                    break;
            }
        }

        return days;
    }

    private static SubscribeDay getSubscribeDay(int day, int timeDivisions, int singleTimeGaps) {
        SubscribeDay subscribeDay = new SubscribeDay();
        Date date = getDateNextDay(new Date(), day);
        String dateString = format(date, FORMAT_DATE);
        subscribeDay.setDateStringShow(format(date, dateString));
        subscribeDay.setSubscribeTimes(getSubscribeTime(date, dateString, timeDivisions, singleTimeGaps));
        return subscribeDay;
    }

    /**
     * 获取时间间隔数组
     *
     * @return
     */
    public static List<SubscribeTime> getSubscribeTime(Date date, String dateString, int timeDivisions, int singleTimeGaps) {
        List<SubscribeTime> subscribeTimeList = new ArrayList<>(timeDivisions);
        Date baseDate = parse(date, FORMAT_DATE);
        SubscribeTime subscribeTime;
        for (int index = 0; index < timeDivisions; index++) {
            subscribeTime = new SubscribeTime();
            subscribeTime.setDate(date);
            subscribeTime.setDateString(dateString);
            subscribeTime.setBeginTimeMills(getDateNextMinutesTime(baseDate, singleTimeGaps * index));
            subscribeTime.setEndTimeMills(getDateNextMinutesTime(baseDate, singleTimeGaps * (index + 1)));
            subscribeTime.setBeginTime(longToDate(subscribeTime.getBeginTimeMills()));
            if (index == timeDivisions - 1) {//最后一个时刻
                subscribeTime.setEndTime("24:00");
            } else {
                subscribeTime.setEndTime(longToDate(subscribeTime.getEndTimeMills()));
            }
            subscribeTimeList.add(subscribeTime);
        }
        return subscribeTimeList;
    }

    private static String format(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 解析成日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    private static Date parse(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(format(date, format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private static Date getDateNextDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        return calendar.getTime();
    }


    /**
     * 获取多少分钟以后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    private static long getDateNextMinutesTime(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis() + (minute * 60 * 1000);
//        calendar.setTimeInMillis(timeMillis);
//        return format(calendar.getTime(), FORMAT_TIME);
    }

    private static String longToDate(long l) {
        Date date = new Date(l);
        return new SimpleDateFormat(FORMAT_TIME).format(date);
    }

}
