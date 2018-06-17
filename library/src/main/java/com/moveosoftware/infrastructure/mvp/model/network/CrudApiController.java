package com.moveosoftware.infrastructure.mvp.model.network;

import java.util.List;

import rx.Subscriber;

/**
 * Created by oferdan-on on 8/26/17
 */

public abstract class CrudApiController<A, Req extends Request,Res extends Response> extends ApiController<A> {

    public abstract void create(Req request,Subscriber<Res> subscriber);

    public abstract void getItem(String id, Subscriber<Res> subscriber);

    public abstract void getList(Subscriber<List<Res>> subscriber);

    public abstract void delete(String id, Subscriber<Res> subscriber);

    public abstract void update(String id, Subscriber<Res> subscriber);


}
