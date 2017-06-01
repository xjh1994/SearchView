package com.xjh1994.searchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xjh1994 on 17/6/1.
 */

public class SearchView extends LinearLayout {

    RelativeLayout rlSearch;
    EditText etSearch;
    ImageView ivClear;
    ProgressBar pbLoading;
    ImageView ivCancel;
    TextView tvCancel;

    String searchEmptyToast;

    OnSearchClickListener onSearchClickListener;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);

        obtainAttrs(context, attrs);
        initView();
    }

    private void obtainAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SearchView);

        searchEmptyToast = ta.getString(R.styleable.SearchView_searchEmptyToast);

        ta.recycle();
    }

    private void initView() {
        rlSearch = (RelativeLayout) findViewById(R.id.rlSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        ivClear = (ImageView) findViewById(R.id.ivClear);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        ivCancel = (ImageView) findViewById(R.id.ivCancel);
        tvCancel = (TextView) findViewById(R.id.tvCancel);

        ivClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
            }
        });
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        etSearch.addTextChangedListener(textWatcher);
    }

    public void addOnSearchClickListener(final OnSearchClickListener onSearchClickListener) {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    String trim = v.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        if (!TextUtils.isEmpty(searchEmptyToast)) {
                            Toast.makeText(getContext(), searchEmptyToast, Toast.LENGTH_SHORT).show();
                        }
                        onSearchClickListener.onSearchInputEmpty();
                    } else {
                        onSearchClickListener.onSearchClick(trim);
                    }
                }

                return false;
            }
        });
    }

    public void addOnSearchTextChangedListener(final OnSearchTextChangedListener onSearchTextChangedListener) {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    onSearchTextChangedListener.onSearchTextEmpty();
                    ivClear.setVisibility(GONE);
                } else {
                    onSearchTextChangedListener.onSearchTextChanged(s.toString().trim());
                    ivClear.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setOnCancelClickListener(OnClickListener onClickListener) {
        tvCancel.setOnClickListener(onClickListener);
    }

    /**
     * 点击搜索按钮事件
     */
    public interface OnSearchClickListener {
        void onSearchClick(String trimSearchText);    //点击搜索（输入不为空）
        void onSearchInputEmpty();   //输入为空
    }

    /**
     * 输入框字发生变化事件
     */
    public interface OnSearchTextChangedListener {
        void onSearchTextChanged(String trimSearchText);
        void onSearchTextEmpty();
    }
}
