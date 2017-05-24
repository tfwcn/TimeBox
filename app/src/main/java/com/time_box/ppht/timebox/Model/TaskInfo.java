package com.time_box.ppht.timebox.Model;

import com.time_box.ppht.timebox.R;
import com.time_box.ppht.timebox.ResourcesHelper;

/**
 * Created by YC on 2017/5/7.
 * 任务数据
 */

public class TaskInfo {
    /**
     * 任务名
     */
    public String name;
    /**
     * 是否打开
     */
    public boolean isOpen;

    /**
     * 获取定时描述(需外部进行颜色和HTML转换)
     * @return
     */
    public String getContent() {
        String notificationvContent = "每周一"
                + "<font color = '#colorContentHot'>10</font>"
                + "<font color = '#colorContentFocus'>:</font>"
                + "<font color = '#colorContentHot'>29</font>"
                + "<font color = '#colorContentFocus'>:</font>"
                + "<font color = '#colorContentHot'>23</font>"
                + "执行";
        return notificationvContent;
    }
}
