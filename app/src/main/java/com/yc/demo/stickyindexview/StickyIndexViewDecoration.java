package com.yc.demo.stickyindexview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yc.stickyindexview.ItemType;
import com.yc.stickyindexview.adapter.StickyIndexViewAdapter;
import com.yc.stickyindexview.entity.StickyIndexEntity;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class StickyIndexViewDecoration extends RecyclerView.ItemDecoration {

    private final int mSize;
    private final int mLeftMargin;
    private final int mRightMargin;
    private final Drawable mDivider;

    public StickyIndexViewDecoration(Context context) {
        mDivider = new ColorDrawable(context.getResources().getColor(R.color.divider_color));
        mSize = context.getResources().getDimensionPixelSize(R.dimen.global_divider_size);
        mLeftMargin = context.getResources().getDimensionPixelSize(R.dimen.contact_item_left_margin);
        mRightMargin = context.getResources().getDimensionPixelSize(R.dimen.contact_item_right_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        StickyIndexViewAdapter adapter = (StickyIndexViewAdapter) parent.getAdapter();
        StickyIndexEntity entity = adapter.getItem(position);
        if (entity.getItemType() != ItemType.ITEM_TYPE_INDEX && position < (adapter.getItemCount() - 1)
                && adapter.getItem(position + 1).getItemType() != ItemType.ITEM_TYPE_INDEX) {
            outRect.set(0, 0, 0, mSize);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        super.onDraw(c, parent, state);
        int top;
        int bottom;
        int left = parent.getPaddingLeft() + mLeftMargin;
        int right = parent.getWidth() - parent.getPaddingRight() - mRightMargin;
        final int childCount = parent.getChildCount();
        StickyIndexViewAdapter adapter = (StickyIndexViewAdapter) parent.getAdapter();
        StickyIndexEntity entity;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            entity = adapter.getItem(position);
            if (entity.getItemType() != ItemType.ITEM_TYPE_INDEX &&
                    position < (adapter.getItemCount() - 1) &&
                    adapter.getItem(position + 1).getItemType() != ItemType.ITEM_TYPE_INDEX) {
                //获得child的布局信息
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
