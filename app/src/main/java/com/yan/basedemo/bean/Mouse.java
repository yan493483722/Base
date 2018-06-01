package com.yan.basedemo.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by YanZi on 2018/5/22.
 * describe：
 * modify:
 * modify date:
 */
public class Mouse implements Observer{


    String name;

    int id;

    private  Cat cat;

    public Mouse(String name, int id) {
        this.name = name;
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public void setCat(Cat cat) {
        this.cat = cat;
        this.cat.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.print("老鼠---->" + toString() + "得到猫的状态==>>");
        cat= (Cat) o;
        String where= (String) arg;
        if(cat.isOut){
            System.out.println("猫---->"+cat.toString()+"去"+where+"了");
        }else{
            System.out.println("猫---->"+cat.toString()+"回"+where+"了");
        }

    }

}
