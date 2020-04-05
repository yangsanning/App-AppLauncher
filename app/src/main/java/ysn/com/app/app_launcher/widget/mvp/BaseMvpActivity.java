package ysn.com.app.app_launcher.widget.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @Author yangsanning
 * @ClassName BaseMvpActivity
 * @Description 通用的 mvp activity
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView  {
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=getPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P getPresenter();
}