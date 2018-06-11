package com.yan.basedemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yan.basedemo.R;
import com.yan.basedemo.bean.Dog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2018/4/23.
 * describe：
 * modify:
 * modify date:
 */
public class GreenDaoAdapter extends RecyclerView.Adapter<GreenDaoAdapter.MyEntity> {

    LayoutInflater mLayoutInflater;
    Context mContext;

    List<Dog> dogList;

    //点击事件回调
    GreenDaoClickListener greenDaoClickListener;

<<<<<<< HEAD
    //构造方法
=======
>>>>>>> f945679358b0407874c87af2d57fac0be02f6d72
    public GreenDaoAdapter(Context mContext, List<Dog> dogList) {
        this.mContext = mContext;
        this.dogList = dogList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public  void  notifyDataSetChanged( List<Dog> dogList){
        this.dogList=dogList;
        super.notifyDataSetChanged();
    };

    public void setGreenDaoClickListener(GreenDaoClickListener greenDaoClickListener) {
        this.greenDaoClickListener = greenDaoClickListener;

    }

    @NonNull
    @Override
    public MyEntity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyEntity(mLayoutInflater.inflate(R.layout.item_green_dao, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyEntity holder, final int position) {
        holder.tvItemGreenDaoName.setText(dogList.get(position).getDogId()+"  "+dogList.get(position).getName());
        holder.ivItemGreenDaoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (greenDaoClickListener != null) {
                    greenDaoClickListener.clickDelete(position);
                }
            }
        });
        holder.clItemGreenDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (greenDaoClickListener != null) {
                    greenDaoClickListener.clickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogList == null ? 0 : dogList.size();
    }

    class MyEntity extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_green_dao_delete)
        ImageView ivItemGreenDaoDelete;
        @BindView(R.id.tv_item_green_dao_name)
        TextView tvItemGreenDaoName;
        @BindView(R.id.cl_item_green_dao)
        ConstraintLayout clItemGreenDao;

        public MyEntity(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface GreenDaoClickListener {

        void clickItem(int position);

        void clickDelete(int position);
    }
}
