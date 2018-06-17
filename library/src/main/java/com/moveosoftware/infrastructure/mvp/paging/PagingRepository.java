package com.moveosoftware.infrastructure.mvp.paging;


import com.moveosoftware.infrastructure.mvp.model.database.RealmController;
import com.moveosoftware.infrastructure.mvp.model.network.ApiController;
import com.moveosoftware.infrastructure.mvp.model.repository.Repository;

import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by moveosoftware on 4/4/18
 */

public abstract class PagingRepository<T extends RealmObject,  AC extends ApiController, RC extends RealmController> extends Repository<AC, RC> {

    /**
     * @param skip  - post to start from
     * @param limit - total number of posts to fetch, should not
     *              exceed the limit in the server { currently the limit is 30}
     * @return - all posts in feed as observable
     */
    public abstract Observable<RealmResults<T>> page(int skip, int limit);
}
