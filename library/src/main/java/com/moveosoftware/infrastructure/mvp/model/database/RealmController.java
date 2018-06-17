package com.moveosoftware.infrastructure.mvp.model.database;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by oferdan-on on 8/26/17
 */

public abstract class RealmController<T extends RealmObject> {
    protected Realm mRealm = Realm.getDefaultInstance();

}
