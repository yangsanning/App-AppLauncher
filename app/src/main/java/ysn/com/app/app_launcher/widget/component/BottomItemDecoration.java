package ysn.com.app.app_launcher.widget.component;

import android.content.Context;

import androidx.annotation.Nullable;

import ysn.com.app.app_launcher.R;
import ysn.com.recyclerview.BaseItemDecoration;
import ysn.com.recyclerview.RvItemDecoration;
import ysn.com.recyclerview.RvItemDecorationBuilder;

/**
 * @Author yangsanning
 * @ClassName BottomItemDecoration
 * @Description 底部分割线
 * @Date 2020/4/4
 * @History 2020/4/4 author: description:
 */
public class BottomItemDecoration extends BaseItemDecoration {

    private int lineColor;

    public BottomItemDecoration(Context context) {
        this.lineColor = context.getResources().getColor(R.color.app_line_background);
    }

    @Nullable
    @Override
    public RvItemDecoration getRvItemDecoration(int itemPosition) {
        return new RvItemDecorationBuilder()
                .buildBottom(lineColor, 1)
                .finish();
    }
}
