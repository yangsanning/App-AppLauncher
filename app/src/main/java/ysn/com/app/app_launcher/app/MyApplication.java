package ysn.com.app.app_launcher.app;

import android.app.Application;

import com.lazy.library.logging.Builder;
import com.lazy.library.logging.Logcat;

import ysn.com.app.app_launcher.BuildConfig;
import ysn.com.app.app_launcher.constant.Constant;
import ysn.com.app.app_launcher.utils.ResUtils;

/**
 * @Author yangsanning
 * @ClassName MyApplication
 * @Description 一句话概括作用
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initialize();
    }

    private void initialize() {
        ResUtils.inject(this);
        initLogCat();
    }

    private void initLogCat() {
        Builder builder = Logcat.newBuilder();
        builder.topLevelTag(Constant.LOG_GLOBAL_TAG);
        if (BuildConfig.DEBUG) {
            builder.logCatLogLevel(Logcat.SHOW_ALL_LOG);
        } else {
            builder.logCatLogLevel(Logcat.SHOW_INFO_LOG | Logcat.SHOW_WARN_LOG | Logcat.SHOW_ERROR_LOG);
        }
        Logcat.initialize(this, builder.build());
    }

    public static MyApplication get() {
        return mInstance;
    }
}
