package il.co.moveo.repostiroeyandnetworkbound.model.model;

import il.co.moveo.repostiroeyandnetworkbound.network.response.PostResponse;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Post extends RealmObject {
    @PrimaryKey
    private String mId;
    @Required
    private String mTitle;
    @Required
    private String mDescription;
    public static final String ID="mId";

    public Post(String mTitle, String mDescription) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }
    public Post(){}

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public static Post parse(PostResponse postResponse) {
        Post post=new Post();
        post.setId(postResponse.id);
        post.setTitle(postResponse.title);
        post.setDescription(postResponse.description);

        return post;
    }
}
