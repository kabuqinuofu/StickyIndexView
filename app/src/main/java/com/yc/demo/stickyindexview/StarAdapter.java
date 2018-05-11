package com.yc.demo.stickyindexview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yc.stickyindexview.adapter.StickyIndexViewAdapter;

import java.util.List;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class StarAdapter extends StickyIndexViewAdapter<StarBean> {

    private Context mContext;

    public StarAdapter(Context context, List<StarBean> originalList) {
        super(originalList);
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateIndexViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_index, parent, false);
        return new IndexViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindIndexViewHolder(RecyclerView.ViewHolder holder, int position, String indexName) {

        IndexViewHolder indexViewHolder = (IndexViewHolder) holder;
        indexViewHolder.mTextView.setText(indexName);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position, StarBean itemData) {

        CityViewHolder cityViewHolder = (CityViewHolder) holder;
        cityViewHolder.mTextView.setText(itemData.getName());
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        private CityViewHolder(View itemView) {

            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    class IndexViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        private IndexViewHolder(View itemView) {

            super(itemView);
            mTextView = itemView.findViewById(R.id.index);
        }
    }

}
