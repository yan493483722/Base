package com.yan.base.toolbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/8/13 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class DefaultSearchToolbar extends BaseToolbar {

    public DefaultSearchToolbar(Context context) {
        super(context);
    }

    public DefaultSearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public DefaultSearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

     void initOtherLayout(Context context){
         setSearchDefaultLayout(context);
    }

    /**
     * 获得输入框中的内容
     *
     * @return
     */
    public EditText getDefaultLayoutEditText() {
        return et_base_tb_search;
    }

    /**
     * 设置默认的搜索标题
     */
    public void setSearchDefaultLayout(Context context) {
        tv_base_tb_title.setVisibility(GONE);
        et_base_tb_search = (EditText) findViewById(R.id.et_base_tb_search);
        final View view = LayoutInflater.from(context).inflate(R.layout.toolbar_search, rl_base_tb, false);

        ImageButton iv_base_tb_search_delete= (ImageButton) view.findViewById(R.id.iv_base_tb_search_delete);

        iv_base_tb_search_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rl_base_tb.removeAllViews();
        rl_base_tb.addView(view);
    }
}
