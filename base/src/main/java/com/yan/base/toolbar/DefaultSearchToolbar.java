package com.yan.base.toolbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/8/13 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class DefaultSearchToolbar extends BaseToolbar implements TextWatcher {

    ImageButton ib_base_tb_search_delete;

    /**
     * 搜索
     */
    public EditText et_base_tb_search;

    public DefaultSearchToolbar(Context context) {
        super(context);
    }

    public DefaultSearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public DefaultSearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void initOtherLayout(Context context) {
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
    protected void setSearchDefaultLayout(Context context) {
        tb_base_tb.removeAllViews();
        tv_base_tb_title.setVisibility(GONE);
        final View view = LayoutInflater.from(context).inflate(R.layout.toolbar_search, rl_base_tb, false);
        rl_base_tb.removeAllViews();
        rl_base_tb.addView(view);

        ib_base_tb_search_delete = (ImageButton) view.findViewById(R.id.ib_base_tb_search_delete);
        et_base_tb_search = (EditText) view.findViewById(R.id.et_base_tb_search);

        ib_base_tb_search_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_base_tb_search.length() > 0) {
                    et_base_tb_search.setText("");
                }
            }
        });

        et_base_tb_search.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (et_base_tb_search.length() > 0) {
                        ib_base_tb_search_delete.setVisibility(VISIBLE);
                        return;
                    }
                }
                ib_base_tb_search_delete.setVisibility(GONE);
            }
        });

        et_base_tb_search.addTextChangedListener(this);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        et_base_tb_search.removeTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (et_base_tb_search.length() > 0) {
            ib_base_tb_search_delete.setVisibility(VISIBLE);
        } else {
            ib_base_tb_search_delete.setVisibility(GONE);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_base_tb_search.length() > 0) {
            ib_base_tb_search_delete.setVisibility(VISIBLE);
        } else {
            ib_base_tb_search_delete.setVisibility(GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
