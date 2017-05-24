package com.time_box.ppht.timebox;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by YC on 2017/5/6.
 * 获取资源辅助类
 */

public class ResourcesHelper {
    private Resources nowResources;

    public ResourcesHelper(Context c) {
        nowResources = c.getResources();
    }

    /**
     * 获取颜色字符串(#FFFFFF)
     *
     * @param id
     * @return
     */
    public String GetColorString(int id) {
        return "#" + String.format("%08x", GetColor(id)).substring(2);
    }

    /**
     * 获取颜色整数
     *
     * @param id
     * @return
     */
    public int GetColor(int id) {
        return nowResources.getColor(id, null);
    }

    /**
     * 替换特定颜色字符串(#FFFFFF)
     *
     * @param id
     * @return
     */
    public String ConvertColorString(String source, String key, int id) {
        return source.replace(key,GetColorString(id)) ;
    }
}
