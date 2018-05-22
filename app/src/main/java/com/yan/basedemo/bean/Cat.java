package com.yan.basedemo.bean;

import java.util.Observable;

/**
 * Created by YanZi on 2018/5/22.
 * describe：
 * modify:
 * modify date:
 */
public class Cat extends Observable {

    String name;

    int id;

    boolean isOut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", isOut=" + isOut +
                '}';
    }
}
