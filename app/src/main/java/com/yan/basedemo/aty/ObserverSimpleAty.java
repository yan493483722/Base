package com.yan.basedemo.aty;

import android.view.View;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;
import com.yan.basedemo.bean.Cat;
import com.yan.basedemo.bean.Mouse;

import butterknife.OnClick;

/**
 * Created by YanZi on 2018/5/23.
 * describe：
 * modify:
 * modify date:
 */
public class ObserverSimpleAty extends BaseAty {

    Cat cat = new Cat("大脸猫",1,false);

    Mouse mouse =new Mouse("小老鼠",1);


    @Override
    protected int setContentLayout() {
        return R.layout.aty_observer_simple;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        mouse.setCat(cat);
    }

    @OnClick({R.id.btn_observer_simple_out, R.id.btn_observer_simple_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_observer_simple_out:
                cat.setOut(true, "草地");
                break;
            case R.id.btn_observer_simple_in:
                cat.setOut(false, "老窝");
                break;
            default:
                break;
        }
    }
}
