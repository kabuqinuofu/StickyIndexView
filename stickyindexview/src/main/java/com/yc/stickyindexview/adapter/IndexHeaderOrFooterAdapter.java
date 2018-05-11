package com.yc.stickyindexview.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.yc.stickyindexview.entity.BaseEntity;
import com.yc.stickyindexview.entity.StickyIndexEntity;
import com.yc.stickyindexview.ilstener.OnItemClickListener;
import com.yc.stickyindexview.ilstener.OnItemLongClickListener;
import com.yc.stickyindexview.utils.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表头部或底部自定义实体数据适配器
 *
 * @author by KABUQINUOFU on 2018/5/11.
 */
public abstract class IndexHeaderOrFooterAdapter<T extends BaseEntity> {

    private String mIndexName;
    private String mIndexValue;
    private int mItemType = -1;
    private List<T> mOriginalList;
    private boolean mNormalView = false;
    private List<StickyIndexEntity<T>> mEntityList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public IndexHeaderOrFooterAdapter() {
        mNormalView = true;
    }

    public IndexHeaderOrFooterAdapter(String indexValue, String indexName, List<T> list) {
        mIndexValue = indexValue;
        mIndexName = indexName;
        mOriginalList = list;
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position, T itemData);

    void transfer(int itemType) {
        mItemType = itemType;
        //转换得到当前添加的Header数据
        mEntityList = ConvertUtil.transferHeaderFooterData(this, mItemType);
        if (isNormalView() == false && !TextUtils.isEmpty(getIndexName())) {
            //当前添加的Header为索引Header且要显示的索引标题不为空时才创建索引实体
            StickyIndexEntity<T> indexEntity = ConvertUtil.createIndexEntity(getIndexValue(), getIndexName());
            mEntityList.add(0, indexEntity);
        }
    }

    public String getIndexValue() {
        return mIndexValue;
    }

    public String getIndexName() {
        return mIndexName;
    }

    public List<T> getOriginalList() {

        return mOriginalList;
    }

    public boolean isNormalView() {

        return mNormalView;
    }

    /**
     * 添加到{StickyIndexView}中之后才会有值，否则为长度为0的列表
     *
     * @return
     */
    public List<StickyIndexEntity<T>> getEntityList() {
        return mEntityList == null ? new ArrayList<StickyIndexEntity<T>>(0) : mEntityList;
    }

    /**
     * 添加到{StickyIndexView}中之后才会有值，否则为-1
     *
     * @return
     */
    public int getItemType() {
        return mItemType;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

}
