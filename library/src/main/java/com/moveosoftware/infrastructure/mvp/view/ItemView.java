package com.moveosoftware.infrastructure.mvp.view;

/**
 * Created by oferdan-on on 8/26/17
 */

public interface ItemView<T> extends BaseView {


    void onItem(T t);
}
