package com.moveosoftware.infrastructure.mvp.view;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moveosoftware.infrastructure.mvp.presenter.Presenter;

/**
 * Created by Ofer Dan-On on 1/20/2017
 */
public abstract class BaseFragment<P extends Presenter> extends Fragment {

    protected ViewGroup mRootView;
    protected P mPresenter;
    protected Context mContext;

    //    protected BaseActivity mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(getLayout(), container, false);
        mPresenter = getPresenter();
        initView(mRootView);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    private void attach(Context context) {
        mContext = context;
    }

    /**
     * Called BEFORE the view is created and binds the presenter
     * to lifecycle events (onResume and onPause)
     *
     * @return The Presenter instance connected to this fragment
     */
    protected abstract P getPresenter();

    protected abstract void bind();

    protected abstract void unbind();


    /**
     * Inflates the given layout and calls initView(View layout)
     * eith the inflated view.
     *
     * @return The layout resource id to inflate on this fragment
     */
    public abstract int getLayout();

    /**
     * Called after the layout resource passed in by getLayout()
     * is inflated.
     * This is where view initialization should occur.
     *
     * @param view The layout for this fragment
     */
    public abstract void initView(ViewGroup view);
}
