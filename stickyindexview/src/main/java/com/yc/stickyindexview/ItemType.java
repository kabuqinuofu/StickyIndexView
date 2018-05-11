package com.yc.stickyindexview;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class ItemType {

    /**
     * 列表中普通数据项类型，例如联系人列表中的：联系人信息项
     */
    public static final int ITEM_TYPE_CONTENT = 0;
    /**
     * 列表中索引项类型，例如联系人列表中的：A,B,C...等索引数据
     */
    public static final int ITEM_TYPE_INDEX = 1;
    /**
     * 列表中增加头部索引数据类型(如自定义的常用联系人)
     */
    public static final int ITEM_TYPE_INDEX_HEADER = 2;
    /**
     * 列表中增加底部索引数据类型
     */
    public static final int ITEM_TYPE_INDEX_FOOTER = 3;

}
