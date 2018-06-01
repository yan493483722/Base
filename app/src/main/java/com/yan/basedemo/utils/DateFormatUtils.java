package com.yan.basedemo.utils;

import android.util.Log;

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
public class DateFormatUtils {


  private final static String FORMAT_DATE = "yyyy-MM-dd";

  private final static String FORMAT_TIME = "HH:mm";


  private int day, timeDivision;


  private DateFormatUtils() {
  }

  public DateFormatUtils(int day, int timeDivision) {
    this.day = day;
    this.timeDivision = timeDivision;
  }

  /**
   * 获取天
   * @return
   */
  public List<String> getDays() {
    List<String> days = new ArrayList<>(day);
    switch (day) {
      case 3:
          System.out.println(3);
        days.add(0, getDateNextDay(new Date(), 2, FORMAT_DATE) + "后天");
      case 2:
          System.out.println(2);
        days.add(0, getDateNextDay(new Date(), 1, FORMAT_DATE) + "明天");
      case 1:
          System.out.println(1);
        days.add(0, "今天");
        break;
      default:
          System.out.println("default");
        for (int index = 0; index < day; index++) {
          days.add(getDateNextDay(new Date(), index, FORMAT_DATE));
        }
    }
    return days;
  }

  /**
   * 获取时间间隔数组
   * @return
   */
  public List<String> getTimeGaps() {
    List<String> timeGaps = new ArrayList<>(timeDivision);
    int dayMinute = 1440;//一天的分钟数
    int div = dayMinute / timeDivision;//时间间隔～分钟数
    Date baseDate = parse(new Date(), FORMAT_DATE);
    for (int index = 0; index < timeDivision; index++) {
     if(index==timeDivision-1){
       timeGaps.add(getDateNextSomeMinute(baseDate, div * index) + "~" + "24:00");
     }else{
       timeGaps.add(getDateNextSomeMinute(baseDate, div * index) + "~" + getDateNextSomeMinute(baseDate, div * (index + 1)));

     }

    }
    return timeGaps;
  }

  private String format(Date date, String format) {
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
  private Date parse(Date date, String format) {
    SimpleDateFormat df = new SimpleDateFormat(format);
    try {
      return df.parse(format(date, format));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Date();
  }

  private String getDateNextDay(Date date, int day, String format) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
    return format(calendar.getTime(), format);
  }

  /**
   * 获取多少分钟以后的时间
   *
   * @param date
   * @param minute
   * @return
   */
  private String getDateNextSomeMinute(Date date, int minute) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    long timeMillis = calendar.getTimeInMillis() + (minute * 60 * 1000);
    calendar.setTimeInMillis(timeMillis);
    return format(calendar.getTime(), FORMAT_TIME);
  }


  public static void main(String[] args) {
    DateFormatUtils bean = new DateFormatUtils(3,12);

    System.out.println(bean.getDays());

    System.out.println(bean.getTimeGaps());
  }
}
