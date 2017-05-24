package com.time_box.ppht.timebox.Control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.time_box.ppht.timebox.R;
import com.time_box.ppht.timebox.ResourcesHelper;
import com.time_box.ppht.timebox.StyleHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by YC on 2017/5/16.
 */

public class TimeView extends RelativeLayout {
    private TableLayout control_time_view_tlSetting;
    private TextView control_time_view_tvHour;
    private TextView control_time_view_tvMinute;
    private RelativeLayout control_time_view_rlTime;
    private LinearLayout control_time_view_llTime;
    private TextView control_time_view_btnHour;
    private TextView control_time_view_btnMinute;
    private ResourcesHelper resourcesHelper;

    public TimeView(Context context) {
        super(context);
        Init();
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init();
    }

    private void Init() {
        resourcesHelper = new ResourcesHelper(getContext());
        LayoutInflater.from(getContext()).inflate(R.layout.control_time_view, this);
        control_time_view_tlSetting = (TableLayout) findViewById(R.id.control_time_view_tlSetting);
        control_time_view_tvHour = (TextView) findViewById(R.id.control_time_view_tvHour);
        control_time_view_tvMinute = (TextView) findViewById(R.id.control_time_view_tvMinute);
        control_time_view_rlTime = (RelativeLayout) findViewById(R.id.control_time_view_rlTime);
        control_time_view_llTime = (LinearLayout) findViewById(R.id.control_time_view_llTime);
        control_time_view_btnHour = (TextView) findViewById(R.id.control_time_view_btnHour);
        control_time_view_btnMinute = (TextView) findViewById(R.id.control_time_view_btnMinute);
        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetting();
            }
        };
        OnClickListener clickListenerHour = new OnClickListener() {
            @Override
            public void onClick(View v) {
                showHourSetting();
                control_time_view_btnHour.setBackgroundColor(resourcesHelper.GetColor(R.color.colorContentBG));
                control_time_view_btnMinute.setBackgroundColor(resourcesHelper.GetColor(R.color.colorContentNBG));
            }
        };
        OnClickListener clickListenerMinute = new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMinuteSetting();
                control_time_view_btnHour.setBackgroundColor(resourcesHelper.GetColor(R.color.colorContentNBG));
                control_time_view_btnMinute.setBackgroundColor(resourcesHelper.GetColor(R.color.colorContentBG));
            }
        };
        control_time_view_rlTime.setOnClickListener(clickListener);
        control_time_view_btnHour.setOnClickListener(clickListenerHour);
        control_time_view_btnMinute.setOnClickListener(clickListenerMinute);
        control_time_view_tlSetting.setVisibility(View.GONE);
        Time nowTime = new Time(System.currentTimeMillis());
        control_time_view_tvHour.setText(new SimpleDateFormat("HH").format(nowTime));
        control_time_view_tvMinute.setText(new SimpleDateFormat("mm").format(nowTime));
        showHourSetting();
    }

    /**
     * 显示/隐藏设置版面
     */
    public void showSetting() {
        if (control_time_view_tlSetting.getVisibility() != View.VISIBLE)
            control_time_view_tlSetting.setVisibility(View.VISIBLE);
        else
            control_time_view_tlSetting.setVisibility(View.GONE);
//        Animation myAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.anim_time_view);
//        control_time_view_setting.startAnimation(myAnimation);
    }

    /**
     * 显示小时设置
     */
    private void showHourSetting() {
        clearSetting();
        ViewGroup.LayoutParams lp = control_time_view_llTime.getLayoutParams();
        TableRow.LayoutParams tlp = new TableRow.LayoutParams(lp);
        tlp.span = 6;
        control_time_view_llTime.setLayoutParams(tlp);
        for (int i = 0; i < 4; i++) {
            //行
            TableRow tr = new TableRow(getContext());
            tr.setBackgroundResource(R.color.colorContentNBG);
            for (int j = 0; j < 6; j++) {
                //列
                TextView tv = new TextView(getContext());
                StyleHelper.setMargins(tv,10,10,10,10);
                StyleHelper.setWeight(tv,1);
                tv.setText(String.valueOf(i * 6 + j + 1));
                tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                tv.setPadding(0, 10, 0, 10);
                tv.setBackgroundResource(R.color.colorContentBG);
                OnClickListener clickListener = new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        control_time_view_tvHour.setText(((TextView) v).getText());
                    }
                };
                tv.setOnClickListener(clickListener);
                tr.addView(tv);
            }
            control_time_view_tlSetting.addView(tr);
        }
    }

    /**
     * 显示分钟设置
     */
    private void showMinuteSetting() {
        clearSetting();
        ViewGroup.LayoutParams lp = control_time_view_llTime.getLayoutParams();
        TableRow.LayoutParams tlp = new TableRow.LayoutParams(lp);
        tlp.span = 10;
        control_time_view_llTime.setLayoutParams(tlp);
        for (int i = 0; i < 6; i++) {
            //行
            TableRow tr = new TableRow(getContext());
            for (int j = 0; j < 10; j++) {
                //列
                TextView tv = new TextView(getContext());
                StyleHelper.setMargins(tv,10,10,10,10);
                StyleHelper.setWeight(tv,1);
                tv.setText(String.valueOf(i * 10 + j + 1));
                tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                tv.setPadding(0, 10, 0, 10);
                OnClickListener clickListener = new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        control_time_view_tvMinute.setText(((TextView) v).getText());
                    }
                };
                tv.setOnClickListener(clickListener);
                tr.addView(tv);
            }
            control_time_view_tlSetting.addView(tr);
        }
    }

    /**
     * 移除所有控件
     */
    private void clearSetting() {
        int count = control_time_view_tlSetting.getChildCount();
        for (int i = 1; i < count; i++) {
            control_time_view_tlSetting.removeViewAt(1);
        }
    }

    /**
     * 获取值
     * @return
     */
    public Time getValue() {
        Time t = Time.valueOf(control_time_view_tvHour.getText() + ":" + control_time_view_tvMinute.getText() + ":0");
        return t;
    }
}
