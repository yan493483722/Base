package com.yan.basedemo.aty.bar;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/8/8.
 * describe：
 * modify:
 * modify date:
 */
public class MoreActivity extends BaseAty {
    @BindView(R.id.btb_more)
    BaseToolbar btbMore;


    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_more);
        ButterKnife.bind(this);

        setBaseToolbar(btbMore, false);
        btbMore.setTitleText("更多");

        btbMore.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {

            }
        });
        btbMore.ll_base_tb_right.setVisibility(View.GONE);
        btbMore.ll_base_tb_left.setVisibility(View.GONE);
        btbMore.tb_base_tb.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.menu_more_0:
                    msg += "Click menu_more";
                    break;
                case R.id.menu_more_1:
                    msg += "Click menu_more_1";
                    break;
                case R.id.menu_more_2:
                    msg += "Click menu_more_2";
                    break;
                case R.id.menu_more_3:
                    msg += "Click menu_more_3";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(MoreActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

}
