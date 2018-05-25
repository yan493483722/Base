package com.yan.basedemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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
    @Generated(hash = 1073004011)
    public Dog(Long id, long dogId, String name) {
        this.id = id;
        this.dogId = dogId;
        this.name = name;
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

    


}
