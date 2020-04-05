package ysn.com.app.app_launcher.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * @Author yangsanning
 * @ClassName ToastUtils
 * @Description 通用 toast 类
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class ToastUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application application = null;

    private static Toast mToastNormal;
    private static long ThreadID = android.os.Process.myTid();
    private static Handler handler;

    public static void inject(@NonNull Application application) {
        ToastUtils.application = application;
    }

    /**
     * 普通的吐司提示
     *
     * @param message 吐司内容
     */
    public static void showNormalToast(String message) {
        long id = Thread.currentThread().getId();
        if (ThreadID == id) {
            makeToast(message);
        } else {
            if (handler == null) {
                handler = new Handler();
            }
            handler.post(() -> makeToast(message));
        }
    }

    public static void showNetworkError() {
        showNormalToast("请检查网络是否正常");
    }

    private static void makeToast(String msg) {
        ToastUtils.cancel();
        mToastNormal = Toast.makeText(application, msg, Toast.LENGTH_SHORT);
        mToastNormal.show();
    }

    public static void cancel() {
        if (mToastNormal != null) {
            mToastNormal.cancel();
        }
    }
}
