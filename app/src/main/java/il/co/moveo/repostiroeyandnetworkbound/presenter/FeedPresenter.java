
package il.co.moveo.repostiroeyandnetworkbound.presenter;

import android.content.Context;
import android.util.Log;

import com.moveosoftware.infrastructure.mvp.model.network.Resource;
import com.moveosoftware.infrastructure.mvp.presenter.Presenter;

import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.model.repository.PostRepository;
import il.co.moveo.repostiroeyandnetworkbound.view.interfaceView.FeedView;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;


public class FeedPresenter extends Presenter<FeedView> {
    private Realm realm;
    private PostRepository mPostRepository;
    Subscription subscribe;

    public FeedPresenter(FeedView mView) {
        super(mView);
    }

    @Override
    public void unbind() {
        super.unbind();
        realm.close();
        subscribe.unsubscribe();
    }

    @Override
    public void bind() {
        super.bind();
        realm = Realm.getDefaultInstance();
        initRepository();

    }
    private void initRepository() {
        mPostRepository = new PostRepository();
    }


    public void allPosts(Context context){
        subscribe = mPostRepository.getFeed(context).subscribe(resource -> {
            if (resource instanceof Resource.Loading) {
                Log.d("tag", "");
                mView.OnLoadingFeed();
            } else if (resource instanceof Resource.Failure) {
                mView.onError(((Resource.Failure) resource).getThrowable().getMessage());
            } else if (resource instanceof Resource.Success) {
                mView.refreshData(((Resource.Success<RealmResults<Post>>) resource).getData());
            }
        });


    }
}
