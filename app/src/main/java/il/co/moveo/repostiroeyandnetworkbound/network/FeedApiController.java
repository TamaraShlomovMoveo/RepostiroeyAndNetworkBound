package il.co.moveo.repostiroeyandnetworkbound.network;

import com.moveosoftware.infrastructure.mvp.model.network.CrudApiController;
import com.moveosoftware.infrastructure.mvp.model.network.Request;
import com.moveosoftware.infrastructure.mvp.model.network.Response;

import java.util.ArrayList;
import java.util.List;

import il.co.moveo.repostiroeyandnetworkbound.network.request.AddPostRequest;
import il.co.moveo.repostiroeyandnetworkbound.network.response.PostResponse;
import rx.Observable;
import rx.Subscriber;

public class FeedApiController extends CrudApiController<FeedApi, Request, Response> {

    public Observable<PostResponse> addPost(AddPostRequest request) {
        return api.addPost(request)
                .compose(applyTransformer());
    }
    public Observable<ArrayList<PostResponse>> getFeed() {
        return api.getFeed()
                .compose(applyTransformer());
    }

    @Override
    public void create(Request request, Subscriber<Response> subscriber) {

    }

    @Override
    public void getItem(String id, Subscriber<Response> subscriber) {

    }

    @Override
    public void getList(Subscriber<List<Response>> subscriber) {

    }

    @Override
    public void delete(String id, Subscriber<Response> subscriber) {

    }

    @Override
    public void update(String id, Subscriber<Response> subscriber) {

    }

    @Override
    public String getEndpoint() {
        return FeedApiConstant.ENDPOINT;
    }

    @Override
    public Class<FeedApi> getApiClass() {
        return FeedApi.class;
    }

}
