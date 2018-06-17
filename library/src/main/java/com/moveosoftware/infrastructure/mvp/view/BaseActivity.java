package com.moveosoftware.infrastructure.mvp.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.moveosoftware.infrastructure.mvp.presenter.Presenter;

/**
 * Created by Ofer Dan-On on 1/20/2017
 */

public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {
    protected Resources mResources;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    protected static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



    @Override
    protected void onResume() {
        super.onResume();
        mResources = getResources();
        mPresenter = getPresenter();
        bind();

    }

    @Override
    protected void onPause() {
        super.onPause();
        unbind();
    }


    /**
     * Called BEFORE the view is created and binds the presenter
     * to lifecycle events (onResume and onPause)
     *
     * @return The Presenter instance connected to this Activity
     */
    protected abstract P getPresenter();

    protected abstract void bind();

    protected abstract void unbind();


}
