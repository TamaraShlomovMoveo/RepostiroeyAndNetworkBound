package il.co.moveo.repostiroeyandnetworkbound.network;


import java.util.ArrayList;

import il.co.moveo.repostiroeyandnetworkbound.network.request.AddPostRequest;
import il.co.moveo.repostiroeyandnetworkbound.network.response.PostResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FeedApi {

    @POST(FeedApiConstant.POST)
    Observable<PostResponse> addPost(@Body AddPostRequest request);

    @GET(FeedApiConstant.FEED)
    Observable<ArrayList<PostResponse>> getFeed();
}
