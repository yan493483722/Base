package com.yan.basedemo.aty.bar;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    protected void initContentView() {
        setContentView(R.layout.aty_stretchable_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            btbStretchableStatusBar.setBaseToolBarType(getIntent().getExtras().getInt("type"));
            btbStretchableStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
            tvbStretchableStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
            ivbStretchableStatusBar.setImageResource(R.drawable.img_htys);

        }
        setStatusBarInFragment();
        setBaseToolbar(btbStretchableStatusBar, true);
        baseToolBarHeight = btbStretchableStatusBar.getHeight(mAty);
        ctlStretchableStatusBar.setMinimumHeight(btbStretchableStatusBar.getHeight(mAty));
        btbStretchableStatusBar.setTitleText("可拉伸图片");
        btbStretchableStatusBar.setRightText("图片");

        aplStretchableStatusBar.addOnOffsetChangedListener(appBarLayoutListener);

        btbStretchableStatusBar.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                Intent intent = new Intent(mAty, StretchableAty.class);
                intent.putExtra("type", BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL);
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
                ivbStretchableStatusBar.setScaleX(1.25f-rate/4);
                ivbStretchableStatusBar.setScaleY(1-rate/8);
            } else {
                totalHeight = appBarLayout.getHeight();
            }
            Log.e("yan", "verticalOffset" + verticalOffset);
            Log.e("yan", "totalHeight" + totalHeight);
            Log.e("yan", "baseToolBarHeight" + baseToolBarHeight);
        }
    };

}
