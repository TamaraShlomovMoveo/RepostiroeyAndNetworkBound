package il.co.moveo.repostiroeyandnetworkbound;

import android.app.Application;

import com.moveosoftware.infrastructure.mvp.model.network.ApiConfig;
import com.moveosoftware.infrastructure.mvp.model.network.ApiConstants;
import com.moveosoftware.infrastructure.mvp.model.network.NetworkManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initNetworkManager();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("mydb.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private void initNetworkManager() {
        ApiConfig apiConfig =
                new ApiConfig.Builder(BuildConfig.BASE_URL)
                        .setAuthHeader(ApiConstants.AUTH_HEADER_KEY, ApiConstants.AUTH_HEADER_VALUE)
                        .setVersion(ApiConstants.API_VERSION)
                        .build();

        NetworkManager.create(apiConfig);

    }
}
