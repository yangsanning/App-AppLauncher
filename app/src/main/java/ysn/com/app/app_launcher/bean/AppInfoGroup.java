package ysn.com.app.app_launcher.bean;

import java.util.ArrayList;

/**
 * @Author yangsanning
 * @ClassName AppInfoType
 * @Description 一句话概括作用
 * @Date 2020/4/5
 * @History 2020/4/5 author: description:
 */
public class AppInfoGroup {

    public String type;

    public ArrayList<AppInfo> appInfoList;

    public AppInfoGroup(String type, ArrayList<AppInfo> appInfoList) {
        this.type = type;
        this.appInfoList = appInfoList;
    }

    public AppInfo getAppInfo(int position) {
       return appInfoList.get(position);
    }
}
