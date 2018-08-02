package com.yan.basedemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by YanZi on 2018/5/22.
 * describe：
 * modify:
 * modify date:
 */
@Entity
public class Dog {
    @Id(autoincrement = true)
    private Long id;
    long dogId;
    String name;
//    @Transient
    String master;
    @Generated(hash = 489780208)
    public Dog(Long id, long dogId, String name, String master) {
        this.id = id;
        this.dogId = dogId;
        this.name = name;
        this.master = master;
    }
    @Generated(hash = 2001499333)
    public Dog() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getDogId() {
        return this.dogId;
    }
    public void setDogId(long dogId) {
        this.dogId = dogId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
