package com.moveosoftware.infrastructure.mvp.presenter;

import com.moveosoftware.infrastructure.mvp.view.ListView;

/**
 * Created by oferdan-on on 8/26/17
 * An item list dedicated Presenter, commonly used in collection displaying views.
 */

public abstract class ListPresenter<V extends ListView<T>, T> extends Presenter<V> {

    public ListPresenter(V mView) {
        super(mView);
    }

    public abstract void getList();
}
