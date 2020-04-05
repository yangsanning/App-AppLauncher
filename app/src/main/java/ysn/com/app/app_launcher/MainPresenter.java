package ysn.com.app.app_launcher;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ysn.com.app.app_launcher.bean.AppInfo;
import ysn.com.app.app_launcher.widget.mvp.BasePresenter;

/**
 * @Author yangsanning
 * @ClassName MainPresenter
 * @Description 一句话概括作用
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void scanAppInfo(Context context) {
        if (!isSafe()) {
            return;
        }
        mView.onLoading();
        Observable.create((ObservableOnSubscribe<AppInfo>) emitter -> {
            List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo packageInfo = packages.get(i);
                AppInfo app = new AppInfo(
                        packageInfo.applicationInfo.loadIcon(context.getPackageManager()),
                        packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString(),
                        packageInfo.packageName,
                        !((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0));
                emitter.onNext(app);
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        if (isSafe()) {
                            mView.onAppInfo(appInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isSafe()) {
                            mView.onLoadComplete();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isSafe()) {
                            mView.onLoadComplete();
                        }
                    }
                });
    }
}
