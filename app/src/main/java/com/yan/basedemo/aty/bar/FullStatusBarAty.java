package com.yan.basedemo.aty.bar;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/24.
 * describe：
 * modify:
 * modify date:
 */
public class FullStatusBarAty extends BaseAty {
    @BindView(R.id.btb_status_bar_full)
    BaseToolbar btbStatusBarFull;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_full_status_bar;
    }

    @Override
    protected void initView() {
        setBaseToolbar(btbStatusBarFull, true);
        btbStatusBarFull.setTitleText("全屏的状态栏");
        btbStatusBarFull.setDefaultLayoutRight(null,"全屏");

        btbStatusBarFull.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {
                onBackPressed();
            }

            @Override
            public void clickRight(int type) {

            }
        });
        //        btbStatusBarNormal.setBaseToolbarStatusBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }

    @Override
    public void initData() {

    }


}
