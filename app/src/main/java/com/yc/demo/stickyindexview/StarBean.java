package com.yc.demo.stickyindexview;

import com.yc.stickyindexview.entity.BaseEntity;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class StarBean implements BaseEntity {

    private String name;
    private String focus;

    @Override
    public String getIndexContent() {
        return name;
    }

    public StarBean() {
    }

    public StarBean(String name, String focus) {
        this.name = name;
        this.focus = focus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        return "StarBean{" +
                "name='" + name + '\'' +
                ", focus='" + focus + '\'' +
                '}';
    }
}
