package cn.com.zlqf.safe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.SpUtils;
import cn.com.zlqf.safe.util.ToastUtil;
import cn.com.zlqf.safe.view.SettingItemView;

/**
 * Created by Administrator on 2017/3/9.
 */
public class SettingActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUpdate();
    }

    private void initUpdate() {
        final SettingItemView siv_update = (SettingItemView) findViewById(R.id.siv_update);
        //显示之前是否开启了自动更新 如果没有设置 默认关闭
        boolean open_auto_update = SpUtils.getBoolean(this, Const.OPEN_AUTO_UPDATE, false);
        siv_update.setCheck(open_auto_update);
        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = siv_update.isCheck();
                siv_update.setCheck(!check);
                SpUtils.putBoolean(getApplicationContext(),Const.OPEN_AUTO_UPDATE,!check);
            }
        });
    }
}
