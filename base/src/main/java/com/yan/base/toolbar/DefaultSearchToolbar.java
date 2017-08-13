package com.yan.base.toolbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
        et_base_tb_search = (EditText) findViewById(R.id.et_base_tb_search);
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
        final View view = LayoutInflater.from(context).inflate(R.layout.toolbar_search, rl_base_tb, false);
        rl_base_tb.removeAllViews();
        rl_base_tb.addView(view);
    }
}
