package com.yc.stickyindexview.entity;

/**
 * 想要展示的数据实体基类
 *
 * @author by KABUQINUOFU on 2018/5/11.
 */
public interface BaseEntity {

    /**
     * 索引的字段数据信息，比如通讯录中对姓名进行索引，则此处返回姓名字段值
     *
     * @return
     */
    String getIndexContent();

}
