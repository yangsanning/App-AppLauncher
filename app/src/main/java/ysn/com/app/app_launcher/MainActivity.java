package ysn.com.app.app_launcher;

import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import ysn.com.app.app_launcher.bean.AppInfo;
import ysn.com.app.app_launcher.bean.AppInfoGroup;
import ysn.com.app.app_launcher.utils.AppUtil;
import ysn.com.app.app_launcher.widget.adapter.AppInfoGroupAdapter;
import ysn.com.app.app_launcher.widget.component.BottomItemDecoration;
import ysn.com.app.app_launcher.widget.mvp.BaseMvpActivity;
import ysn.com.baserecyclerviewadapter.adapter.BaseRecyclerViewAdapter;
import ysn.com.baserecyclerviewadapter.holder.BaseViewHolder;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private ArrayList<AppInfoGroup> appInfoGroupList = new ArrayList<>();
    private AppInfoGroupAdapter appInfoGroupAdapter;

    @BindView(R.id.main_activity_total)
    TextView totalTextView;

    @BindView(R.id.main_activity_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        appInfoGroupList.add(new AppInfoGroup("系统应用", new ArrayList<>()));
        appInfoGroupList.add(new AppInfoGroup("普通应用", new ArrayList<>()));
        appInfoGroupAdapter = new AppInfoGroupAdapter(this, appInfoGroupList);
        appInfoGroupAdapter.setOnChildClickListener(new BaseRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                AppUtil.startApp((MainActivity.this), appInfoGroupAdapter.getDatas().get(groupPosition).getAppInfo(childPosition).packageName);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new BottomItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(appInfoGroupAdapter);

        mPresenter.scanAppInfo(this);
    }

    @Override
    public void onAppInfo(AppInfo appInfo) {
        if (appInfo.isSystemApp) {
            appInfoGroupList.get(0).appInfoList.add(appInfo);
        } else {
            appInfoGroupList.get(1).appInfoList.add(appInfo);
        }
        appInfoGroupAdapter.notifyDataChanged();
        int count = appInfoGroupAdapter.getItemCount() - appInfoGroupAdapter.getGroupCount();
        totalTextView.setText("已安装" + count + "个app");
    }

    @Override
    public void onLoading() {
        showProgressDialog();
    }

    @Override
    public void onLoadComplete() {
        dismissProgressDialog();
    }
}