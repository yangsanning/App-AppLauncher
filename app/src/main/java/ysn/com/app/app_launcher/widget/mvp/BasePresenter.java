package ysn.com.app.app_launcher.widget.mvp;

/**
 * @Author yangsanning
 * @ClassName BasePresenter
 * @Description mvp的 p 的通用处理类
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class BasePresenter<V> {

    public V mView;

    public BasePresenter(V view) {
        this.mView = view;
    }

    public boolean isSafe() {
        return mView != null;
    }
}
