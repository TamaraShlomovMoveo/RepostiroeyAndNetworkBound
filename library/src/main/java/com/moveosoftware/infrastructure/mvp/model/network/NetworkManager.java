package com.moveosoftware.infrastructure.mvp.model.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moveosoftware on 6/1/17
 */

public class NetworkManager {

    private static final int MAX_MONITOR_REQ_RETRY_COUNT = 3;
    private static NetworkManager instance;
    private static ApiConfig apiConfig;


    public static void create(ApiConfig apiConfig) {
        NetworkManager.apiConfig = apiConfig;
    }


    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }

        if (apiConfig == null) {
            throw new UnsupportedOperationException("must call NetworkManager.create(ApiConfig config) before using NetworkManager");
        }

        return instance;
    }


    private NetworkManager() {
    }


    public <T> T registerController(ApiController<T> controller) {
        return getBaseApi(controller.getEndpoint(), controller.useHome()).create(controller.getApiClass());
    }

    private Retrofit getBaseApi(String endpoint, boolean useHome) {
        Interceptor headerInterceptor = chain -> {
            Request.Builder builder = chain
                    .request()
                    .newBuilder();

            if (apiConfig.getAuthHeaderKey() != null) {
                builder.addHeader(apiConfig.getAuthHeaderKey(), apiConfig.getAuthHeaderValue());
            }
            return chain.proceed(builder.build());
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(headerInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit.Builder builder = new Retrofit.Builder();

        if (useHome) {
            builder.baseUrl(apiConfig.getBaseUrl() + endpoint);
        } else {
            builder.baseUrl(endpoint);
        }

        return builder
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
