package il.co.moveo.repostiroeyandnetworkbound.network.response;

import com.google.gson.annotations.SerializedName;
import com.moveosoftware.infrastructure.mvp.model.network.Response;

public class PostResponse extends Response {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String title;
    @SerializedName("text")
    public String description;
  //  @SerializedName("update_at") /** check if i need to change to updateAt */
   // public String updatedAt;
   // @SerializedName("created_at")
   // public String createdAt;
}
