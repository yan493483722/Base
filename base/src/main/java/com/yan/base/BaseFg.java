package com.yan.base;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.toolbar.BaseToolbarUtil;

/**
 * Created by YanZi on 2017/2/27.
 * describe：
 * modify:
 * modify date:
 */
public class BaseFg extends Fragment {

    /**
     * FragmentPagerAdapter与FragmentStatePagerAdapter区别:
     主要区别就在与对于fragment是否销毁，下面细说：
     FragmentPagerAdapter：对于不再需要的fragment，选择调用detach方法，仅销毁视图，并不会销毁fragment实例。
     FragmentStatePagerAdapter：会销毁不再需要的fragment，当当前事务提交以后，
     会彻底的将fragment从当前Activity的FragmentManager中移除，state标明，销毁时，
     会将其onSaveInstanceState(Bundle outState)中的bundle信息保存下来，当用户切换回来，
     可以通过该bundle恢复生成新的fragment，也就是说，你可以在onSaveInstanceState(Bundle outState)方法中保存一些数据，
     在onCreate中进行恢复创建。
     如上所说，使用FragmentStatePagerAdapter当然更省内存，但是销毁新建也是需要时间的。
     一般情况下，如果你是制作主页面，就3、4个Tab，那么可以选择使用FragmentPagerAdapter，
     如果你是用于ViewPager展示数量特别多的条目时，那么建议使用FragmentStatePagerAdapter。
     * **/

    /**
     * hide和show :
     * a、我在FragmentA中的EditText填了一些数据，当切换到FragmentB时，如果希望会到A还能看到数据，
     * 这适合你的就是hide和show；也就是说，希望保留用户操作的面板，你可以使用hide和show，当然了不要使劲在那new实例，进行下非null判断。
     * b、再比如：我不希望保留用户操作，你可以使用remove()，然后add()；或者使用replace()这个和remove,add是相同的效果。
     * c、remove和detach有一点细微的区别，在不考虑回退栈的情况下，remove会销毁整个Fragment实例，而detach则只是销毁其视图结构，
     * 实例并不会被销毁。那么二者怎么取舍使用呢？如果你的当前Activity一直存在，那么在不希望保留用户操作的时候，你可以优先使用detach。
     **/
    void setResult(int resultCode, Intent data) {
        getActivity().setResult(resultCode, data);
    }

    /**
     * 当前fragment由别的fragment启动 返回数据
     * setTargetFragment(Fragment fragment, int requestCode)
     */

    /**
     * 普通页面设置baseToolbar
     *
     * @param toolbar
     * @param showLeftIcon
     */
    public void setBaseToolbar(BaseToolbar toolbar, boolean showLeftIcon) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar.tb_base_tb);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BaseToolbarUtil.setBaseToolbar(toolbar, getActivity(), false);
        }
    }

    /**
     * 两边有侧滑时设置baseToolbar
     * 注意：
     * 1.根布局 android.support.v4.widget.DrawerLayout 的DrawerLayout 要设置 android:fitsSystemWindows="true"
     * 2.左右滑动菜单根本局 为 android.support.design.widget.NavigationView 勿使用其他作为根布局
     *
     * @param toolbar
     * @param showLeftIcon
     */
    public void setSlideBaseToolbar(BaseToolbar toolbar, boolean showLeftIcon) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar.tb_base_tb);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BaseToolbarUtil.setBaseToolbar(toolbar, getActivity(), true);
        }
    }
}
