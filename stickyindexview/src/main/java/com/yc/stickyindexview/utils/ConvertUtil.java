package com.yc.stickyindexview.utils;

import com.yc.stickyindexview.ItemType;
import com.yc.stickyindexview.adapter.IndexHeaderOrFooterAdapter;
import com.yc.stickyindexview.entity.BaseEntity;
import com.yc.stickyindexview.entity.StickyIndexEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 实体类转换工具，将要显示的数据转换成{@link android.support.v7.widget.RecyclerView}中展示的实体
 *
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class ConvertUtil {

    /**
     * 转换过程中，如果待索引字段信息为非字母串，则将其索引值设为：#
     */
    public static final String INDEX_SPECIAL = "#";

    public static class ConvertResult<T> {
        //转换后得到的实际展示数据列表，包括联系人数据+组名称数据(索引名称)
        private List<StickyIndexEntity<T>> mIndexStickyEntities = new ArrayList<>();
        //索引条中展示的数据列表
        private List<String> mIndexValueList = new ArrayList<>();
        //索引条中展示数据与对应组在列表中位置索引的一一映射
        private Map<String, Integer> mIndexValuePositionMap = new HashMap<>();

        public List<StickyIndexEntity<T>> getIndexStickyEntities() {
            return mIndexStickyEntities;
        }

        public List<String> getIndexValueList() {

            return mIndexValueList;
        }

        public Map<String, Integer> getIndexValuePositionMap() {

            return mIndexValuePositionMap;
        }
    }

    public static <T extends BaseEntity> ConvertResult<T> transfer(List<T> list) {

        ConvertResult<T> convertResult = new ConvertResult<T>();

        //使用TreeMap自动按照Key(字母索引值)进行排序
        TreeMap<String, List<StickyIndexEntity<T>>> treeMap = new TreeMap<>(ComparatorUtil.indexValueComparator());
        for (int i = 0; i < list.size(); i++) {
            StickyIndexEntity<T> entity = originalEntityToIndexEntity(list.get(i));

            if (treeMap.containsKey(entity.getIndexValue())) {//Map中已存在此索引值
                treeMap.get(entity.getIndexValue()).add(entity);
            } else {
                List<StickyIndexEntity<T>> indexStickyEntities = new ArrayList<>();
                indexStickyEntities.add(entity);
                treeMap.put(entity.getIndexValue(), indexStickyEntities);
            }
        }
        for (String indexValue : treeMap.keySet()) {
            StickyIndexEntity<T> indexValueEntity = createIndexEntity(indexValue, indexValue);

            //将索引值添加到索引值列表中
            convertResult.getIndexValueList().add(indexValue);
            //按顺序将索引实体添加到列表中
            convertResult.getIndexStickyEntities().add(indexValueEntity);
            //将索引值与索引值在结果列表中的位置进行映射
            convertResult.getIndexValuePositionMap().put(indexValue, convertResult.getIndexStickyEntities().size() - 1);

            //得到当前索引值下的索引数据实体
            List<StickyIndexEntity<T>> indexStickyEntities = treeMap.get(indexValue);
            //对数据实体按自然进行排序
            Collections.sort(indexStickyEntities, ComparatorUtil.<T>indexEntityComparator());
            //将排序后的实体列表按顺序加入到结果列表中
            convertResult.getIndexStickyEntities().addAll(indexStickyEntities);
        }

        return convertResult;
    }

    /**
     * 原始数据转换成展示的索引数据
     *
     * @param originalEntity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> StickyIndexEntity<T> originalEntityToIndexEntity(T originalEntity) {

        StickyIndexEntity<T> entity = new StickyIndexEntity<>();
        T item = originalEntity;
        String indexFieldName = item.getIndexContent();
        String pinyin = PinYinUtil.getPingYin(indexFieldName);
        String indexValue = pinyin.substring(0, 1).toUpperCase();
        if (!PinYinUtil.isLetter(indexValue)) {//首字符如果非字母以#代替
            indexValue = INDEX_SPECIAL;
        }
        entity.setPinYin(pinyin);
        entity.setOriginalData(item);
        entity.setIndexValue(indexValue);
        entity.setIndexName(indexValue);
        return entity;
    }

    /**
     * 根据索引值创建索引实体对象
     *
     * @param indexValue
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> StickyIndexEntity<T> createIndexEntity(String indexValue, String indexName) {

        //根据索引值创建索引实体对象
        StickyIndexEntity<T> indexValueEntity = new StickyIndexEntity<>();
        indexValueEntity.setIndexValue(indexValue);
        indexValueEntity.setPinYin(indexValue);
        indexValueEntity.setIndexName(indexName);
        indexValueEntity.setItemType(ItemType.ITEM_TYPE_INDEX);
        return indexValueEntity;
    }

    public static <T extends BaseEntity> List<StickyIndexEntity<T>> transferHeaderFooterData(IndexHeaderOrFooterAdapter<T> adapter, int itemType) {

        List<StickyIndexEntity<T>> entityList = new ArrayList<>();
        if (adapter.isNormalView()) {
            StickyIndexEntity entity = new StickyIndexEntity();
            entity.setPinYin("");
            entity.setIndexValue("");
            entity.setItemType(itemType);
            entityList.add(entity);
        } else {
            for (int i = 0; i < adapter.getOriginalList().size(); i++) {
                StickyIndexEntity<T> entity = new StickyIndexEntity<>();
                T item = adapter.getOriginalList().get(i);
                String indexFieldName = item.getIndexContent();
                String pinyin = PinYinUtil.getPingYin(indexFieldName);
                entity.setPinYin(pinyin);
                entity.setOriginalData(item);
                entity.setIndexValue(adapter.getIndexValue());
                entity.setIndexName(adapter.getIndexName());
                entity.setItemType(itemType);
                entityList.add(entity);
            }
            Collections.sort(entityList, ComparatorUtil.<T>indexEntityComparator());
        }
        return entityList;
    }
}
