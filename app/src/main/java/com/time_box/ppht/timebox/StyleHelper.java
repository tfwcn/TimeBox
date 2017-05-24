package com.time_box.ppht.timebox;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by YC on 2017/5/23.
 * 样式设置类
 */

public class StyleHelper {
    /**
     * 创建属性
     *
     * @param v
     */
    public static void createLayoutParams(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            v.setLayoutParams(params);
            v.requestLayout();
        }
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        createLayoutParams(v);
        if (v.getLayoutParams() instanceof ViewGroup.LayoutParams) {
            ViewGroup.MarginLayoutParams newParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
            newParams.setMargins(l, t, r, b);
            v.setLayoutParams(newParams);
            v.requestLayout();
        }
    }

    public static void setWeight(View v, int w) {
        createLayoutParams(v);
        if (v.getLayoutParams() instanceof ViewGroup.LayoutParams) {
            TableLayout.LayoutParams newParams = new TableLayout.LayoutParams(v.getLayoutParams());
            newParams.weight = w;
            v.setLayoutParams(newParams);
            v.requestLayout();
        }
    }
}
