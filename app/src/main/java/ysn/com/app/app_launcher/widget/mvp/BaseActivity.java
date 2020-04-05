package ysn.com.app.app_launcher.widget.mvp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.Map;

import butterknife.ButterKnife;
import ysn.com.app.app_launcher.utils.ResUtils;
import ysn.com.app.app_launcher.utils.ToastUtils;
import ysn.com.app.app_launcher.widget.component.LoadingDialog;
import ysn.com.statusbar.StatusBarUtils;

/**
 * @Author yangsanning
 * @ClassName BaseActivity
 * @Description BaseActivity
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private LoadingDialog loadingDialog;
    private Map<String, Fragment> fragments = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setDarkMode(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        initDialog();
        setContentView(getLayoutId());
        initView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    private void initDialog() {
        loadingDialog = new LoadingDialog(this);
    }

    public void showProgressDialog() {
        loadingDialog.show();
    }

    public void dismissProgressDialog() {
        loadingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingDialog.onDestroy();
    }

    public void showMessage(@NonNull String msg) {
        ToastUtils.showNormalToast(msg);
    }

    public void showMessage(@StringRes int resId) {
        ToastUtils.showNormalToast(ResUtils.getString(resId));
    }

    public final void replaceFragmentToBackStack(@IdRes int containerViewId, Fragment fragment) {
        if (fragment != null) {
            replaceFragment(containerViewId, fragment, fragment.getClass().getCanonicalName(), true);
        }
    }

    public final void replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        if (fragment != null) {
            replaceFragment(containerViewId, fragment, fragment.getClass().getCanonicalName(), false);
        }
    }

    public void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment, String className, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment containerFragment = fragmentManager.findFragmentById(containerViewId);
        if (containerFragment == null) {
            fragmentTransaction.add(containerViewId, fragment, className);
        } else {
            fragmentTransaction.replace(containerViewId, fragment, className);
        }

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(className);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    public <T extends Fragment> T findFragment(@NonNull Class<T> clazz, @NonNull final FragmentCallBack fragmentCallBack) {
        String key = clazz.getName();
        Fragment fragment = fragments.get(key);
        if (fragment == null) {
            fragment = fragmentCallBack.onCreateFragment();
            fragments.put(key, fragment);
        }
        //noinspection unchecked
        return (T) fragment;
    }

    public interface FragmentCallBack {
        Fragment onCreateFragment();
    }

    public void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void startActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }
}
