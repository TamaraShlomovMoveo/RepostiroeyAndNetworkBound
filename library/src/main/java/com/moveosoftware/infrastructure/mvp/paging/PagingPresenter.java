package com.moveosoftware.infrastructure.mvp.paging;

import com.moveosoftware.infrastructure.mvp.model.database.RealmController;
import com.moveosoftware.infrastructure.mvp.model.network.ApiController;
import com.moveosoftware.infrastructure.mvp.presenter.Presenter;
import com.moveosoftware.infrastructure.mvp.view.BaseView;

import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Subscriber;

/**
 * Created by moveosoftware on 4/4/18
 */

public abstract class PagingPresenter<T extends RealmObject, R extends PagingRepository, V extends PagingPresenter.PagingView> extends Presenter<V> {

    protected PagingConfig mConfig;
    private PagingRepository<T, ApiController, RealmController> mPagingRepository;

    public PagingPresenter(V mView) {
        super(mView);
        mConfig = initializePagingConfig();
        mPagingRepository = providePagingRepository();
    }

    public void page() {

        mConfig.incrementLimit();

        mPagingRepository.page(mConfig.skip, mConfig.limit)
                .subscribe(new Subscriber<RealmResults<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RealmResults<T> ts) {
                        mView.setPage(ts);
                    }
                });
    }


    public abstract PagingConfig initializePagingConfig();

    /**
     * Providing a suitable repository will allow us to skip it's initialization in the {@link #initRepository()} method.
     *
     * @return - a child implementation of {@link PagingRepository}
     */
    public abstract R providePagingRepository();

    public static class PagingConfig {
        public int skip, limit;

        public PagingConfig(int skip, int limit) {
            this.skip = skip;
            this.limit = limit;
        }

        public void incrementLimit() {
            this.limit += skip;
        }
    }

    public interface PagingView<T extends RealmObject> extends BaseView {
        void setPage(RealmResults<T> data);
    }
}
