package ysn.com.app.app_launcher.bean;

import android.graphics.drawable.Drawable;

/**
 * @Author yangsanning
 * @ClassName AppInfo
 * @Description 一句话概括作用
 * @Date 2019/10/26
 * @History 2019/10/26 author: description:
 */
public class AppInfo {

    public Drawable icon;

    public String name;

    public String packageName;

    public boolean isSystemApp;

    public AppInfo(Drawable icon, String name, String packageName,boolean isSystemApp) {
        this.icon = icon;
        this.name = name;
        this.packageName = packageName;
        this.isSystemApp = isSystemApp;
    }
}
