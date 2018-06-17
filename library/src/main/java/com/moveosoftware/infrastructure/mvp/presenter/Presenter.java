package com.moveosoftware.infrastructure.mvp.presenter;

import com.moveosoftware.infrastructure.mvp.view.BaseView;


/**
 * Created by Ofer Dan-On @ Moveo on 1/20/2017
 */

public abstract class Presenter<V extends BaseView> {


    public Presenter(V mView) {
        this.mView = mView;
    }

    /**
     * mView - represents the view the Presenter will bind to it's Lifecycle
     */
    protected V mView;
    protected String TAG = this.getClass().getSimpleName();

    /**
     * Called in order to bind the presenter to the view and to it's Lifecycle
     * Best practice in accordance with the view's bind method
     */
    public void bind() {

    }

    /**
     * Called in order to unbind the {@link Presenter} from the view's Lifecycle
     * Best practice in accordance with the view's unbind method
     */
    public void unbind() {
        this.mView = null;
    }


}
