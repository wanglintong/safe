package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.SpUtils;
import cn.com.zlqf.safe.view.SettingItemView;

/**
 * Created by Administrator on 2017/3/10.
 */
public class Setup2Activity extends AppCompatActivity{
    private SettingItemView siv_sim_bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        
        initUI();
        initData();
    }

    private void initData() {
        final CheckBox cb_setting_item = (CheckBox) siv_sim_bind.findViewById(R.id.cb_setting_item);
        boolean sim_bind = SpUtils.getBoolean(getApplicationContext(), Const.SIM_BIND, false);
        siv_sim_bind.setCheck(sim_bind);
        siv_sim_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = siv_sim_bind.isCheck();
                siv_sim_bind.setCheck(!check);
                SpUtils.putBoolean(getApplicationContext(),Const.SIM_BIND,!check);
            }
        });
    }

    private void initUI() {
        siv_sim_bind = (SettingItemView) findViewById(R.id.siv_sim_bind);
    }

    public void nextPage(View view) {
        Intent intent = new Intent(this,Setup3Activity.class);
        startActivity(intent);
        finish();
    }
    public void previousPage(View view) {
        Intent intent = new Intent(this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }
}
