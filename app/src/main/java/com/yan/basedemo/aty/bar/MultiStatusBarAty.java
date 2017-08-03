package com.yan.basedemo.aty.bar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/8/2.
 * describe：
 * modify:
 * modify date:
 */
public class MultiStatusBarAty extends BaseAty {

    @Nullable
    TextBadgeItem numberBadgeItem;

    @Nullable
    ShapeBadgeItem shapeBadgeItem;

    @BindView(R.id.bnb_multi_status_bar)
    BottomNavigationBar bnbMultiStatusBar;

    private int msgNum = 3;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_multi_status_bar);
        ButterKnife.bind(this);


    }

    @Override
    protected void initView() {

        numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(0)
                .setBackgroundColorResource(R.color.bg_color_red)
                .setText("" + msgNum);

        //"SHAPE_OVAL", "SHAPE_RECTANGLE", "SHAPE_HEART",
        // "SHAPE_STAR_3_VERTICES", "SHAPE_STAR_4_VERTICES", "SHAPE_STAR_5_VERTICES", "SHAPE_STAR_6_VERTICES"
        shapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.color_blue)
                .setGravity(Gravity.TOP | Gravity.END);
        bnbMultiStatusBar.setMode(BottomNavigationBar.MODE_FIXED);
        bnbMultiStatusBar
                .addItem(new BottomNavigationItem(R.drawable.icon_msg, "消息").setActiveColorResource(R.color.color_orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_home, "主页").setActiveColorResource(R.color.color_orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_find, "发现").setActiveColorResource(R.color.color_orange).setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_me, "我的").setActiveColorResource(R.color.color_orange))
                .setFirstSelectedPosition(0)
                .initialise();
        for (int i = 0; i < bnbMultiStatusBar.getChildCount() ; i++) {
            FrameLayout fl=  ((FrameLayout)bnbMultiStatusBar.getChildAt(i));
            fl .getChildCount();
//            ((View)fl.getChildAt(0))
        }
        bnbMultiStatusBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (msgNum <= 0) {
                    if (numberBadgeItem != null && !numberBadgeItem.isHidden()) {
                        numberBadgeItem.toggle();
                    }
                } else {
                    if (position == 0) {
                        msgNum--;
                    }
                }
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void initData() {

    }


}
