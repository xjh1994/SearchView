package com.xjh1994.searchview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xjh1994.searchview.SearchView;

public class MainActivity extends AppCompatActivity {

    protected SearchView searchView;
    private ResultFragment resultFragment;
    private HistoryFragment historyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);

        searchView.addOnSearchTextChangedListener(new SearchView.OnSearchTextChangedListener() {
            @Override
            public void onSearchTextChanged(String searchText) {
                // 历史记录。如果有，展示；如果没有，显示原文字

                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                    FragmentUtils.addFragment(getSupportFragmentManager(), historyFragment, R.id.flContent);
                } else {
                    FragmentUtils.showFragment(historyFragment);
                    historyFragment.refresh(searchText);
                }
                if (resultFragment != null) {
                    FragmentUtils.hideFragment(resultFragment);
                }
            }

            @Override
            public void onSearchTextEmpty() {
                if (historyFragment != null) {
                    FragmentUtils.hideFragment(historyFragment);
                }
                if (resultFragment != null) {
                    FragmentUtils.hideFragment(resultFragment);
                }
            }
        });
        searchView.addOnSearchClickListener(new SearchView.OnSearchClickListener() {
            @Override
            public void onSearchClick(String trim) {
                // 查询并显示搜索结果

                if (resultFragment == null) {
                    resultFragment = new ResultFragment();
                    FragmentUtils.addFragment(getSupportFragmentManager(), resultFragment, R.id.flContent);
                } else {
                    FragmentUtils.showFragment(resultFragment);
                    resultFragment.refresh(trim);
                }
                if (historyFragment != null) {
                    FragmentUtils.hideFragment(historyFragment);
                }
            }

            @Override
            public void onSearchInputEmpty() {
                // 输入为空
                Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
