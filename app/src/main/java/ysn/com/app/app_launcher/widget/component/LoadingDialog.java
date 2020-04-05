package ysn.com.app.app_launcher.widget.component;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import ysn.com.app.app_launcher.R;
import ysn.com.view.chrysanthemum.ChrysanthemumView;

/**
 * @Author yangsanning
 * @ClassName LoadingDialog
 * @Description loading 加载圈
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class LoadingDialog {

    private Dialog loadingDialog;
    private View view;
    private ChrysanthemumView chrysanthemumView;
    private Handler handler;
    private Runnable delayRunnable;

    public LoadingDialog(Activity activity) {
        init(activity);
        initTask();
    }

    public void init(Activity context) {
        // 首先得到整个View
        view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        chrysanthemumView = view.findViewById(R.id.loading_dialog_chrysanthemum_view);
        loadingDialog = new Dialog(context, R.style.TransparentDialogStyles);
        loadingDialog.getWindow().setDimAmount(0f);
        handler = new Handler();
        setCanceledOnTouchOutside(true);
    }

    /**
     * 显示dialog
     *
     */
    public void show() {
        handler.postDelayed(delayRunnable, 50);
    }

    private void showLoading() {
        loadingDialog.show();
        chrysanthemumView.startAnimation();
    }

    /**
     * 隐藏
     */
    public void dismiss() {
        long id = Thread.currentThread().getId();
        if (id == android.os.Process.myTid()) {
            dismissLoading();
        } else {
            handler.post(this::dismissLoading);
        }
        handler.removeCallbacks(delayRunnable);
    }

    private void dismissLoading() {
        chrysanthemumView.stopAnimation();
        loadingDialog.dismiss();
    }

    private void initTask() {
        delayRunnable = this::showLoading;
    }

    /**
     * 设置返回键无效
     */
    public void setCanceledOnTouchOutside(boolean isClick) {
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        loadingDialog.setCancelable(isClick);
    }

    public void onDestroy() {
        dismissLoading();
    }
}
