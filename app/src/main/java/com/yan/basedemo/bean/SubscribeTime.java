package com.yan.basedemo.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.Date;

/**
 * Created by YanZi on 2018/5/28.
 * describe：
 * modify:
 * modify date:
 */
public class SubscribeTime implements IPickerViewData {

    long beginTimeMills;

    long endTimeMills;

    String beginTime;

    String endTime;

    String dateString;

    Date date;

    public long getBeginTimeMills() {
        return beginTimeMills;
    }

    public void setBeginTimeMills(long beginTimeMills) {
        this.beginTimeMills = beginTimeMills;
    }

    public long getEndTimeMills() {
        return endTimeMills;
    }

    public void setEndTimeMills(long endTimeMills) {
        this.endTimeMills = endTimeMills;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String getPickerViewText() {
        return beginTime+"-"+endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SubscribeTime{" +
                "beginTimeMills=" + beginTimeMills +
                ", endTimeMills=" + endTimeMills +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", dateString='" + dateString + '\'' +
                ", date=" + date +
                '}';
    }
}
