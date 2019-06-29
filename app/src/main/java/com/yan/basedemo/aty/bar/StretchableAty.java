package com.yan.basedemo.aty.bar;

import android.content.Intent;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;

/**
 * Created by YanZi on 2017/8/7.
 * describe：
 * modify:
 * modify date:
 */
public class StretchableAty extends BaseAty {

    @BindView(R.id.btb_stretchable_status_bar)
    BaseToolbar btbStretchableStatusBar;
    @BindView(R.id.tv_stretchable_status_bar)
    TextView tvbStretchableStatusBar;
    @BindView(R.id.iv_stretchable_status_bar)
    ImageView ivbStretchableStatusBar;
    @BindView(R.id.ctl_stretchable_status_bar)
    CollapsingToolbarLayout ctlStretchableStatusBar;
    @BindView(R.id.apl_stretchable_status_bar)
    AppBarLayout aplStretchableStatusBar;

    int totalHeight = 0;
    int baseToolBarHeight = 0;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_stretchable_status_bar;
    }

    @Override
    protected void initView() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            int type = getIntent().getExtras().getInt("type");
            switch (type) {
                case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                    btbStretchableStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_FULL:
                    btbStretchableStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_FULL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL:
                    btbStretchableStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG_FULL:
                    btbStretchableStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_IMG_FULL);
                    break;
                default:
                    btbStretchableStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
                    break;
            }
            btbStretchableStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
            tvbStretchableStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
            ivbStretchableStatusBar.setImageResource(R.drawable.img_htys);
        }

        setBaseToolbarStretchable(btbStretchableStatusBar, true);
        baseToolBarHeight = btbStretchableStatusBar.getHeight(mAty, true);
        ctlStretchableStatusBar.setMinimumHeight(btbStretchableStatusBar.getHeight(mAty, true));
        btbStretchableStatusBar.setTitleText("散文欣赏");
        btbStretchableStatusBar.setDefaultLayoutRight(null, "图片");

        aplStretchableStatusBar.addOnOffsetChangedListener(appBarLayoutListener);

        btbStretchableStatusBar.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {
                onBackPressed();
            }

            @Override
            public void clickRight(int type) {
                Intent intent = new Intent(mAty, StretchableAty.class);
                intent.putExtra("type", BaseToolbar.STATUS_BAR_TYPE_IMG_FULL);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {

    }

    AppBarLayout.OnOffsetChangedListener appBarLayoutListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (totalHeight != 0 && totalHeight - baseToolBarHeight > 0) {
                float rate = Math.abs(verticalOffset) / (float) (totalHeight - baseToolBarHeight);
                if (rate > 0.75) {//0.75-1
                    btbStretchableStatusBar.setTitleText("散文欣赏");
                    btbStretchableStatusBar.tv_base_tb_title.setAlpha((rate - 0.75f) / 0.25f + 0.25f);//不从0开始变，从0.25开始变化，防止一直不可见
                } else {//0-0.75
                    btbStretchableStatusBar.setTitleText("作者简介");
                    btbStretchableStatusBar.tv_base_tb_title.setAlpha(1.25f - rate / 0.75f);//不减到 0 因为 0.25 基本已经不可见了
                }
                ivbStretchableStatusBar.setScaleX(1.25f - rate / 4);
                ivbStretchableStatusBar.setScaleY(1 - rate / 8);
            } else {
                totalHeight = appBarLayout.getHeight();
            }
        }
    };

}
