package il.co.moveo.repostiroeyandnetworkbound.presenter;

import com.moveosoftware.infrastructure.mvp.presenter.Presenter;

import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.model.repository.PostRepository;
import il.co.moveo.repostiroeyandnetworkbound.view.interfaceView.PostView;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class PostPresenter extends Presenter<PostView> {
    private Realm realm;
    PostRepository mPostRepository;

    public PostPresenter(PostView view) {
        super(view);
        initRepository();
    }

    private void initRepository() {
        mPostRepository = new PostRepository();
    }

    @Override
    public void unbind() {
        super.unbind();
        realm.close();
    }

    @Override
    public void bind() {
        super.bind();
        realm = Realm.getDefaultInstance();
    }

    //public void publishPost(String title, String description, final int id) {
//        boolean isValid = isValid(title, description);
//        if (!isValid) {
//            view.notifyTheUser();
//            return;
//        }
//        final Post post = new Post(title, description);
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                int postId;
//                if (id != -1)
//                    postId = id;
//                else {
//                    // increment index
//                    Number currentIdNum = realm.where(Post.class).max(Post.ID);
//                    int nextId;
//                    if (currentIdNum == null) {
//                        nextId = 1;
//                    } else {
//                        nextId = currentIdNum.intValue() + 1;
//                    }
//                    postId = nextId;
//                }
//                post.setId(postId);
//                realm.insertOrUpdate(post);
//                view.finishActivity();
//            }
//        });
//    }

    public void publishPost(String title, String description,String id) {
        boolean isValid = isValid(title, description);
        if (!isValid) {
            mView.notifyTheUser();
            return;
        }

        mPostRepository.publishPost(title, description)
                .asObservable()
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Post post) {
                        if(!id.equals(""))
                            post.setId(id);
                        realm.executeTransaction(realm ->
                                realm.insertOrUpdate(post));
                        mView.finishActivity();
                    }
                });
    }

    private boolean isValid(String title, String description) {
        if (title.equals("") || description.equals(""))
            return false;
        return true;
    }

    public OrderedRealmCollection<Post> allPosts() {
        return realm.where(Post.class).findAllAsync();
    }

    public String getPostId() {
        return mView.getPostId();
    }

    //todo check where is better to put this method . her or in repository
    public void updatePostDataIfNeeded(final String mPostId) {
        realm.executeTransaction(realm -> {
            Post post = realm.where(Post.class).equalTo(Post.ID, mPostId).findFirst();
            if (post != null)
                mView.updatePostDataIfNeeded(post.getTitle(), post.getDescription());
        });
    }
}
