package com.yc.stickyindexview.utils;

import com.yc.stickyindexview.ItemType;
import com.yc.stickyindexview.entity.BaseEntity;
import com.yc.stickyindexview.entity.StickyIndexEntity;

import java.util.Comparator;

import static com.yc.stickyindexview.utils.ConvertUtil.INDEX_SPECIAL;

/**
 * 比较工具
 *
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class ComparatorUtil {

    /**
     * 索引值比较器
     *
     * @return
     */
    public static Comparator<String> indexValueComparator() {

        return new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {

                return compareIndexValue(lhs, rhs);
            }
        };
    }

    /**
     * 列表实体比较器
     *
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> Comparator<StickyIndexEntity<T>> indexEntityComparator() {

        return new Comparator<StickyIndexEntity<T>>() {
            @Override
            public int compare(StickyIndexEntity<T> lhs, StickyIndexEntity<T> rhs) {

                int value;
                if (lhs.getItemType() == ItemType.ITEM_TYPE_CONTENT && rhs.getItemType() == ItemType.ITEM_TYPE_CONTENT) {
                    value = compareIndexEntity(lhs, rhs);
                } else {
                    //只要有一个为Index类型，则只需要比较两者的索引值即可
                    value = compareIndexValue(lhs.getIndexValue(), rhs.getIndexValue());
                }
                return value;
            }
        };
    }

    /**
     * 比较索引值大小
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private static int compareIndexValue(String lhs, String rhs) {

        if (INDEX_SPECIAL.equals(lhs)) {//索引值排序时#号排在后面
            return INDEX_SPECIAL.equals(rhs) ? 0 : 1;
        } else if (INDEX_SPECIAL.equals(rhs)) {
            return -1;
        }
        return lhs.compareTo(rhs);
    }

    /**
     * 比较两个实体大小，且lhs为INDEX类型
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private static int compareIndexEntity(StickyIndexEntity lhs, StickyIndexEntity rhs) {

        String lhsIndexValue = lhs.getIndexValue();
        String rhsIndexValue = rhs.getIndexValue();

        if (!INDEX_SPECIAL.equals(lhsIndexValue) && !INDEX_SPECIAL.equals(rhsIndexValue)) {
            //两者的索引值均不为：#，则比较时只需要按照pinyin比较自然大小即可
            return lhs.getPinYin().compareTo(rhs.getPinYin());
        } else if (!INDEX_SPECIAL.equals(lhsIndexValue) && !INDEX_SPECIAL.equals(rhsIndexValue)) {
            //两者索引值均为：#，则比较时按照pinyin比较自然大小即可
            return lhs.getPinYin().compareTo(rhs.getPinYin());
        } else {
            //两者中只有一个索引值为:#，则索引值为#的小
            return INDEX_SPECIAL.equals(lhsIndexValue) ? -1 : 1;
        }
    }

}
