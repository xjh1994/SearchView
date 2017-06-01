package com.xjh1994.searchview.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xjh1994 on 17/6/1.
 */

public class HistoryFragment extends Fragment {

    protected TextView tvKey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initView(view);
        return view;
    }

    public void refresh(String searchText) {
        tvKey.setText("history" + searchText);
    }

    private void initView(View rootView) {
        tvKey = (TextView) rootView.findViewById(R.id.tvKey);
    }
}
