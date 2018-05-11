package com.yc.stickyindexview;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class IndexValueBus {

    private EntityObservable mEntityObservable;
    private static volatile IndexValueBus mEntityBus;

    private IndexValueBus() {
        mEntityObservable = new EntityObservable();
    }

    public static IndexValueBus getInstance() {

        if (mEntityBus == null) {
            synchronized (IndexValueBus.class) {
                if (mEntityBus == null) {
                    mEntityBus = new IndexValueBus();
                }
            }
        }
        return mEntityBus;
    }

    public void addObserver(Observer observer) {
        mEntityObservable.addObserver(observer);
    }

    public void clear() {
        mEntityObservable.deleteObservers();
    }

    public void notifyDataSetChanged(List<String> indexValue) {

        mEntityObservable.observableChanged();
        mEntityObservable.notifyObservers(indexValue);
    }

    public static class EntityObservable extends Observable {
        public void observableChanged() {
            this.setChanged();
        }
    }

}
