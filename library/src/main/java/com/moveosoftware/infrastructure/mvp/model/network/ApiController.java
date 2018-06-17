package com.moveosoftware.infrastructure.mvp.model.network;


import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by moveosoftware on 7/9/17
 */

public abstract class ApiController<T> {


    protected NetworkManager mNetworkManager;

    protected T api;


    public ApiController() {
        mNetworkManager = NetworkManager.getInstance();
        api = mNetworkManager.registerController(this);
    }


    protected <Response> Observable.Transformer<Response, Response> applyTransformer() {
        return applyTransformer(null);
    }

    private <Response> Observable.Transformer<Response, Response> applyTransformer(Scheduler responseScheduler) {
        return requestObservable ->
                requestObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(responseScheduler == null ? AndroidSchedulers.mainThread() : responseScheduler);
    }


    public abstract String getEndpoint();

    public abstract Class<T> getApiClass();


    protected boolean useHome() {
        return true;
    }

}
