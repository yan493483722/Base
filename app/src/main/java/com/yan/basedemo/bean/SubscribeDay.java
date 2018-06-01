package com.yan.basedemo.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by YanZi on 2018/5/28.
 * describe：
 * modify:
 * modify date:
 */
public class SubscribeDay implements IPickerViewData{

    String dateStringShow;
    private List<SubscribeTime> subscribeTimes;

    public void setDateStringShow(String dateStringShow) {
        this.dateStringShow = dateStringShow;
    }

    public List<SubscribeTime> getSubscribeTimes() {
        return subscribeTimes;
    }

    public void setSubscribeTimes(List<SubscribeTime> subscribeTimes) {
        this.subscribeTimes = subscribeTimes;
    }

    public String getDateStringShow() {
        return dateStringShow;
    }

    @Override
    public String getPickerViewText() {
        return dateStringShow;
    }

    @Override
    public String toString() {
        return "SubscribeDay{" +
                "dateStringShow='" + dateStringShow + '\'' +
                ", subscribeTimes=" + subscribeTimes +
                '}';
    }
}
