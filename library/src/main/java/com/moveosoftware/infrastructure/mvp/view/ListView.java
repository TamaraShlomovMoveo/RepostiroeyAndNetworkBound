package com.moveosoftware.infrastructure.mvp.view;

import java.util.List;

/**
 * Created by oferdan-on on 8/26/17
 */

public interface ListView<T> extends BaseView {

    void onList(List<T> list);
}
