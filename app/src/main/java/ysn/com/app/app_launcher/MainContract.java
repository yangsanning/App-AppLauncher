package ysn.com.app.app_launcher;

import android.content.Context;

import ysn.com.app.app_launcher.bean.AppInfo;
import ysn.com.app.app_launcher.widget.mvp.BaseView;

/**
 * @Author yangsanning
 * @ClassName MainCroctact
 * @Description 一句话概括作用
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public interface MainContract {

    interface View extends BaseView {

        void onAppInfo(AppInfo appInfo);
    }

    interface Presenter {

        void scanAppInfo(Context context);
    }
}
