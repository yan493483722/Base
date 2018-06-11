package com.yan.basedemo.aty.database;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yan.base.BaseAty;
import com.yan.base.widget.RecyclerListDiv;
import com.yan.basedemo.R;
import com.yan.basedemo.adapter.GreenDaoAdapter;
import com.yan.basedemo.bean.Dog;
import com.yan.basedemo.greendao.DogDaoOperation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.rv_green_dao)
    RecyclerView rvGreenDao;
    @BindView(R.id.sml_green_dao)
    SmartRefreshLayout smlGreenDao;

    GreenDaoAdapter greenDaoAdapter;

    private List<Dog> dogList;
    int page;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_green_dao;
    }

    @Override
    protected void initView() {
        System.out.println(System.currentTimeMillis()/1000);
        smlGreenDao.setEnableLoadmore(true);
        smlGreenDao.setEnableRefresh(true);
        smlGreenDao.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smlGreenDao.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

    @Override
    public void initData() {
        dogList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mAty);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvGreenDao.setLayoutManager(linearLayoutManager);
        greenDaoAdapter = new GreenDaoAdapter(mAty, dogList);
        rvGreenDao.setAdapter(greenDaoAdapter);
        rvGreenDao.addItemDecoration(new RecyclerListDiv(RecyclerListDiv.HORIZONTAL, 2, getResources().getColor(R.color.divider_line_color)));
        greenDaoAdapter.setGreenDaoClickListener(new GreenDaoAdapter.GreenDaoClickListener() {
            @Override
            public void clickItem(int position) {

            }

            @Override
            public void clickDelete(int position) {
                DogDaoOperation.deleteData(mAty, dogList.get(position));
                dogList = DogDaoOperation.queryAll(mAty);
                Logger.e("query " + " " + dogList.size());
                greenDaoAdapter.notifyDataSetChanged(dogList);
            }
        });
    }


    @OnClick({R.id.btn_green_dao_add, R.id.btn_green_dao_delete, R.id.btn_green_dao_change, R.id.btn_green_dao_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_green_dao_add:
                final List<Dog> dogList1 = getDogList(this.dogList.size());
                DogDaoOperation.insertData(this, dogList1);
                Logger.e("insertData" + " " + dogList1.size());
                break;
            case R.id.btn_green_dao_delete:
                break;
            case R.id.btn_green_dao_change:
                break;
            case R.id.btn_green_dao_query:
                this.dogList = DogDaoOperation.queryAll(mAty);
                Logger.e("query " + " " + dogList.size());
                greenDaoAdapter.notifyDataSetChanged(dogList);
                break;
        }
    }

    private List<Dog> getDogList(int position) {
        List<Dog> list = new ArrayList();
        for (int i = position; i < 1 + position; i++) {
            Dog dog = new Dog(null, (long) i, "小狗" + i);
            list.add(dog);
        }
        return list;
    }

}
