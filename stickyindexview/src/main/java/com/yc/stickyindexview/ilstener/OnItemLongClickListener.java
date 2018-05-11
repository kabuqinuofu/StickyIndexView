package com.yc.stickyindexview.ilstener;

import android.view.View;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public interface OnItemLongClickListener<T> {
    /**
     * @param childView 点击选中的子视图
     * @param position  点击视图的索引
     * @param item      点击视图的类型
     */
    void onItemLongClick(View childView, int position, T item);

}
