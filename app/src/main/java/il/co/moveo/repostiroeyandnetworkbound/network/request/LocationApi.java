package il.co.moveo.repostiroeyandnetworkbound.network.request;

public class LocationApi {
    public String name;
    public double lat;
    public double lng;

    public LocationApi(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
