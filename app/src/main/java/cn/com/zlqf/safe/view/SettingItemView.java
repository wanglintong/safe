package cn.com.zlqf.safe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.zlqf.safe.R;

/**
 * Created by Administrator on 2017/3/9.
 */

public class SettingItemView extends RelativeLayout{
    private static final  String NAMESPACE = "http://schemas.android.com/apk/res/cn.com.zlqf.safe";
    private CheckBox cb_setting_item;
    private TextView tv_setting_item_title;
    private TextView getTv_setting_item_desc;
    private String title;
    private String off;
    private String on;

    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义与原生属性的操作
        initAttrs(attrs);

        View.inflate(context, R.layout.setting_item_view, this);
        initUI();
        initAttrs(attrs);
        initData();

    }

    private void initData() {
        tv_setting_item_title.setText(title);
    }

    private void initUI() {
        tv_setting_item_title = (TextView) this.findViewById(R.id.tv_setting_item_title);
        getTv_setting_item_desc = (TextView) this.findViewById(R.id.tv_setting_item_desc);
        cb_setting_item = (CheckBox) this.findViewById(R.id.cb_setting_item);
    }

    private void initAttrs(AttributeSet attrs) {
        //取到自定义属性
        title = attrs.getAttributeValue(NAMESPACE, "title");
        off = attrs.getAttributeValue(NAMESPACE, "off");
        on = attrs.getAttributeValue(NAMESPACE, "on");
    }

    public boolean isCheck() {
        return cb_setting_item.isChecked();
    }
    public void setCheck(boolean isCheck) {
        cb_setting_item.setChecked(isCheck);
        if(isCheck) {
            getTv_setting_item_desc.setText(on);
        }else {
            getTv_setting_item_desc.setText(off);
        }
    }
}
