package com.mig35.homeservice.app;

import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mig35.homeservice.dagger.app.AppComponent;
import com.mig35.homeservice.dagger.app.AppModule;
import com.mig35.homeservice.dagger.app.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public final class HomeServiceApplication extends Application {

    @SuppressWarnings("NullableProblems")
    @NonNull
    private AppComponent mAppComponent;

    private Toast mToast;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        mAppComponent = prepareAppComponent().build();
    }

    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this));
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void showToast(final String message) {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }
}