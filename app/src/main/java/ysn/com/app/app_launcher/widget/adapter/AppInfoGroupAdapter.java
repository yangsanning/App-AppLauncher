package ysn.com.app.app_launcher.widget.adapter;

import android.content.Context;

import java.util.List;

import ysn.com.app.app_launcher.R;
import ysn.com.app.app_launcher.bean.AppInfo;
import ysn.com.app.app_launcher.bean.AppInfoGroup;
import ysn.com.baserecyclerviewadapter.adapter.BaseRecyclerViewAdapter;
import ysn.com.baserecyclerviewadapter.holder.BaseViewHolder;

/**
 * @Author yangsanning
 * @ClassName AppInfoTypeAdapter
 * @Description 一句话概括作用
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class AppInfoGroupAdapter extends BaseRecyclerViewAdapter<AppInfoGroup> {

    private List<AppInfoGroup> appInfoGroupList;

    public AppInfoGroupAdapter(Context context, List<AppInfoGroup> appInfoGroupList) {
        super(context);
        this.appInfoGroupList = appInfoGroupList;
    }

    @Override
    public List<AppInfoGroup> getDatas() {
        return appInfoGroupList;
    }

    @Override
    public int getGroupCount() {
        return appInfoGroupList == null ? 0 : appInfoGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return appInfoGroupList.get(groupPosition).appInfoList == null ? 0 : appInfoGroupList.get(groupPosition).appInfoList.size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_app_info_group_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_app_info_group;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        AppInfoGroup appInfoGroup = appInfoGroupList.get(groupPosition);
        holder.setText(R.id.app_info_group_header_item_type, (appInfoGroup.type + "(" + appInfoGroup.appInfoList.size() + ")"));
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int groupItemPosition) {
        AppInfo appInfo = appInfoGroupList.get(groupPosition).getAppInfo(groupItemPosition);
        // 设置点击效果，模拟5.0水波波纹，兼容5.0以下
        holder.itemView.setBackgroundResource(R.drawable.bg_app_info_group_item);
        holder.setImageDrawable(R.id.app_info_group_item_icon, appInfo.icon);
        holder.setText(R.id.app_info_group_item_name, appInfo.name);
        holder.setText(R.id.app_info_group_item_package_name, appInfo.packageName);
    }
}
