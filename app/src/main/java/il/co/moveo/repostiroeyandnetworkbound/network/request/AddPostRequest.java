package il.co.moveo.repostiroeyandnetworkbound.network.request;

public class AddPostRequest {
    public String name;
    public String text;
    public LocationApi location;

    public AddPostRequest(String name, String text, LocationApi location) {
        this.name = name;
        this.text = text;
        this.location = location;
    }
}
