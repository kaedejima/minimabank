package kaedejima.io.minimabank;

import android.app.Application;

import io.realm.Realm;

public class MemoApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);
    }
}