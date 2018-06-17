package il.co.moveo.repostiroeyandnetworkbound.model.repository;

import android.content.Context;

import com.moveosoftware.infrastructure.mvp.model.network.NetworkBoundResource;
import com.moveosoftware.infrastructure.mvp.model.network.Resource;
import com.moveosoftware.infrastructure.mvp.model.repository.Repository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.model.realmController.PostRealmController;
import il.co.moveo.repostiroeyandnetworkbound.network.FeedApiController;
import il.co.moveo.repostiroeyandnetworkbound.network.request.AddPostRequest;
import il.co.moveo.repostiroeyandnetworkbound.network.request.LocationApi;
import il.co.moveo.repostiroeyandnetworkbound.network.response.PostResponse;
import io.realm.RealmResults;
import rx.Observable;

public class PostRepository extends Repository<FeedApiController,PostRealmController> {
    @Override
    public FeedApiController getApiController() {
        return new FeedApiController();
    }

    @Override
    public PostRealmController getRealmController() {
        return new PostRealmController(Post.class);
    }

    public Observable<Post> publishPost(String title, String description) {
        AddPostRequest postRequest=new AddPostRequest(title,description,new LocationApi("Sderot",36.8457,32.4354));
        return mApiController.addPost(postRequest).
                map(postResponse -> mRealmController.map(postResponse));
    }

    public Observable<Resource<RealmResults<Post>>> getFeed(Context context) {
        return new NetworkBoundResource<RealmResults<Post>, ArrayList<PostResponse>>(context){
            @NotNull
            @Override
            protected Observable<ArrayList<PostResponse>> createCall() {
                return mApiController.getFeed();
            }

            @NotNull
            @Override
            protected Observable<RealmResults<Post>> loadFromDb() {
                RealmResults<Post> list=mRealmController.getList();
                if(list==null || list.size()==0)
                    return null;
                else return list.asObservable();
            }

            @Override
            protected void saveCallResult(ArrayList<PostResponse> response) {
                mRealmController.updateList(response);
            }
        }.asFlowable();
    }

}
