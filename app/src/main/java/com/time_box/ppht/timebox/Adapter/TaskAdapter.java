package com.time_box.ppht.timebox.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.time_box.ppht.timebox.Model.TaskInfo;
import com.time_box.ppht.timebox.R;
import com.time_box.ppht.timebox.ResourcesHelper;

import java.util.ArrayList;

/**
 * Created by YC on 2017/5/7.
 */

public class TaskAdapter extends ArrayAdapter<TaskInfo> {
    private Context mContext;
    private int mViewId;//视图ID
    private ArrayList<TaskInfo> mDataList;//数据
    private ResourcesHelper resourcesHelper;

    public TaskAdapter(Context context, int viewId) {
        super(context, viewId);
        this.mContext = context;
        this.mViewId = viewId;
        this.mDataList = new ArrayList<TaskInfo>();
        this.resourcesHelper = new ResourcesHelper(context);
    }

    @Override
    public void add(TaskInfo taskInfo) {
        mDataList.add(taskInfo);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tvTitle = null;//标题
        TextView tvContent = null;//内容
        Switch swOpen = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    mViewId, null);
        }
        swOpen = (Switch) convertView.findViewById(R.id.main_list_item_swOpen);
        //开关事件
        swOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TaskInfo item = mDataList.get(position);
                if (item.isOpen != isChecked)
                    item.isOpen = isChecked;
            }
        });
        tvTitle = (TextView) convertView.findViewById(R.id.main_list_item_tvTitle);
        tvContent = (TextView) convertView.findViewById(R.id.main_list_item_tvContent);
        TaskInfo item = mDataList.get(position);
        tvTitle.setText(item.name);
        String strContent = item.getContent();
        resourcesHelper.ConvertColorString(strContent, "#colorContentFocus", R.color.colorContentFocus);
        resourcesHelper.ConvertColorString(strContent, "#colorContentHot", R.color.colorContentHot);
        tvContent.setText(Html.fromHtml(strContent, Html.FROM_HTML_MODE_LEGACY));
        swOpen.setChecked(item.isOpen);
        return convertView;
    }
}
