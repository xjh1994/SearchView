package com.xjh1994.searchview.inter;

/**
 * Created by xjh1994 on 17/6/1.
 */

public interface SearchHistoryListener {
    void onItemClick(int position, Object item); //点击搜索结果项
    void onClearHistoryClick(); //清除历史记录
}
