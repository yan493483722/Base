package com.yan.basedemo.aty.database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YanZi on 2018/5/22.
 * describe：
 * modify:
 * modify date:
 */
public class GreenDaoAty extends BaseAty {


    @BindView(R.id.btn_green_dao_add)
    Button btnGreenDaoAdd;
    @BindView(R.id.btn_green_dao_delete)
    Button btnGreenDaoDelete;
    @BindView(R.id.btn_green_dao_change)
    Button btnGreenDaoChange;
    @BindView(R.id.btn_green_dao_query)
    Button btnGreenDaoQuery;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_green_dao;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.btn_green_dao_add, R.id.btn_green_dao_delete, R.id.btn_green_dao_change, R.id.btn_green_dao_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_green_dao_add:
                break;
            case R.id.btn_green_dao_delete:
                break;
            case R.id.btn_green_dao_change:
                break;
            case R.id.btn_green_dao_query:
                break;
        }
    }
}
