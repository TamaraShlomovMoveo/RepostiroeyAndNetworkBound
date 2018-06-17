package il.co.moveo.repostiroeyandnetworkbound.view.interfaceView;

import com.moveosoftware.infrastructure.mvp.view.BaseView;

import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import io.realm.RealmResults;

public interface FeedView extends BaseView{
    void OnLoadingFeed();
    void refreshData(RealmResults<Post> list);
}
