package com.moveosoftware.infrastructure.mvp.model.network;

/**
 * Created by moveosoftware on 7/9/17
 */

public class ApiConstants {

    public static final String API_VERSION = "v1/";

    public static final String BASE_URL = "http://danny-dev.moveodevelop.com/api/" + API_VERSION;

    public static final String AUTH_HEADER_KEY = "Danny-App-Auth";

    public static final String AUTH_HEADER_VALUE = "vj5WOmJZ4hEy6kOrFCzg51HoJEhbK2g$";

    /**
     * The '.' is used to send a
     * request to the base url defined on
     * a {@link retrofit2.Retrofit} instance
     */
    public static final String HOME = ".";


    public static String getBasse(){
        return "";
    }
}
