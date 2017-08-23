package com.yan.basedemo.aty.bar;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.yan.base.BaseAty;
import com.yan.base.BaseFg;
import com.yan.basedemo.R;
import com.yan.basedemo.aty.bar.fg.MultiStatusFindFg;
import com.yan.basedemo.aty.bar.fg.MultiStatusHomeFg;
import com.yan.basedemo.aty.bar.fg.MultiStatusMsgFg;
import com.yan.basedemo.aty.bar.fg.MultiStatusMyFg;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.vp_multi_status_bar)
    ViewPager vpMultiStatusBar;

    private int msgNum = 3;

    int size;

    List<BaseFg> baseFgs;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_multi_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        setBaseToolbarInFragment();
        numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(0)
                .setBackgroundColorResource(R.color.bg_color_red)
                .setText("" + msgNum);
        //"SHAPE_OVAL", "SHAPE_RECTANGLE", "SHAPE_HEART",
        // "SHAPE_STAR_3_VERTICES", "SHAPE_STAR_4_VERTICES", "SHAPE_STAR_5_VERTICES", "SHAPE_STAR_6_VERTICES"
        shapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.color_teal)
                .setGravity(Gravity.TOP | Gravity.END).setHideOnSelect(true);
        bnbMultiStatusBar.setMode(BottomNavigationBar.MODE_FIXED);
        bnbMultiStatusBar
                .addItem(new BottomNavigationItem(R.drawable.icon_msg_select, "消息").setInactiveIconResource(R.drawable.icon_msg).setActiveColorResource(R.color.font_red).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_home_select, "主页").setInactiveIconResource(R.drawable.icon_home).setActiveColorResource(R.color.font_red))
                .addItem(new BottomNavigationItem(R.drawable.icon_find_select, "发现").setInactiveIconResource(R.drawable.icon_find).setActiveColorResource(R.color.font_red).setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_me_select, "我的").setInactiveIconResource(R.drawable.icon_me).setActiveColorResource(R.color.font_red))
                .setFirstSelectedPosition(0)
                .initialise();
        bnbMultiStatusBar.setTabSelectedListener(tabSelectedListener);
        baseFgs = new ArrayList<>();
        MultiStatusMsgFg home = new MultiStatusMsgFg();
        MultiStatusHomeFg home2 = new MultiStatusHomeFg();
        MultiStatusFindFg home3 = new MultiStatusFindFg();
        MultiStatusMyFg home4 = new MultiStatusMyFg();
        baseFgs.add(home);
        baseFgs.add(home2);
        baseFgs.add(home3);
        baseFgs.add(home4);
        size = baseFgs.size();
        vpMultiStatusBar.setAdapter(fragmentPagerAdapter);
        vpMultiStatusBar.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void initData() {

    }

    BottomNavigationBar.OnTabSelectedListener tabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            setBadgeShow(position);
            vpMultiStatusBar.setCurrentItem(position);

        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }

        private void show(int position) {
            if (currentTab == position) {
                return;
            }
            baseFgs.get(currentTab).onPause();
            Fragment fragment = baseFgs.get(position);
            if (fragment.isAdded()) {
                fragment.onResume(); // 启动目标tab的onResume()
            } else {
                FragmentTransaction ft = obtainFragmentTransaction(position);
                ft.add(R.id.vp_multi_status_bar, fragment);
                ft.commit();
            }
            currentTab = position;

            for (int i = 0; i < size; i++) {
                FragmentTransaction ft = obtainFragmentTransaction(i);
                if (i == position) {
                    ft.show(fragment);
                } else {
                    if (!baseFgs.get(i).isHidden()) {
                        ft.hide(baseFgs.get(i));
                    }
                }
                ft.commit();
            }
        }

        /**
         * 获取一个带动画的FragmentTransaction
         *
         * @param index
         * @return
         */
        private FragmentTransaction obtainFragmentTransaction(int index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // 设置切换动画
//            if (index > currentTab) {
//                ft.setCustomAnimations(com.wangjie.androidbucket.R.anim.slide_left_in, com.wangjie.androidbucket.R.anim.slide_left_out);
//            } else {
//                ft.setCustomAnimations(com.wangjie.androidbucket.R.anim.slide_right_in, com.wangjie.androidbucket.R.anim.slide_right_out);
//            }
            return ft;
        }
    };

    private int currentTab;
    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            return baseFgs.get(position);
        }

        @Override
        public int getCount() {
            return baseFgs == null ? 0 : baseFgs.size();
        }

    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bnbMultiStatusBar.selectTab(position, false);
            setBadgeShow(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vpMultiStatusBar.removeOnPageChangeListener(onPageChangeListener);
    }

    public void setBadgeShow(int position) {
        if (position == 0) {
            if (msgNum > 0) {
                msgNum--;
                mSnackBarAndToastManager.showToast("减少了一条消息");
                numberBadgeItem.setText("" + msgNum);
            }
        }
        if (msgNum <= 0) {
            if (numberBadgeItem != null && !numberBadgeItem.isHidden()) {
                numberBadgeItem.toggle();
            }
        }
    }
}
