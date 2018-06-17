package il.co.moveo.repostiroeyandnetworkbound.model.realmController;

import com.moveosoftware.infrastructure.mvp.model.database.CrudRealmController;

import java.util.ArrayList;
import java.util.List;

import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.network.response.PostResponse;

public class PostRealmController extends CrudRealmController<Post,PostResponse>{

    public PostRealmController(Class<Post> clazz) {
        super(clazz);
    }

    @Override
    public String getPrimaryKey() {
        return Post.ID;
    }

    @Override
    public Post map(PostResponse postResponse) {

        return Post.parse(postResponse);
    }

    public void updateList(ArrayList<PostResponse> feedResponse) {
        List<Post> allposts=new ArrayList<>();
        for (PostResponse post: feedResponse) {
            allposts.add(map(post));
        }
        mRealm.executeTransaction(
                realm -> mRealm.insertOrUpdate(allposts));
    }
}
