package com.moveosoftware.infrastructure.mvp.presenter;

import com.moveosoftware.infrastructure.mvp.view.ItemView;

/**
 * Created by oferdan-on on 8/26/17
 * A single item dedicated Presenter, commonly used in drill down / profile pages
 */

public abstract class ItemPresenter<V extends ItemView<T>,T> extends Presenter<V> {

    public ItemPresenter(V mView) {
        super(mView);
    }

    public abstract void getItem(String id);

}
