package cn.com.zlqf.safe.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.SpUtils;
import cn.com.zlqf.safe.util.ToastUtil;
import cn.com.zlqf.safe.view.SettingItemView;

/**
 * Created by Administrator on 2017/3/10.
 */
public class Setup2Activity extends AppCompatActivity{
    private SettingItemView siv_sim_bind;
    private String sim_number;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        
        initUI();
        initData();
    }

    private void initData() {
        final CheckBox cb_setting_item = (CheckBox) siv_sim_bind.findViewById(R.id.cb_setting_item);
        if(TextUtils.isEmpty(sim_number)) {
            siv_sim_bind.setCheck(false);
        }else {
            siv_sim_bind.setCheck(true);
        }
        siv_sim_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = siv_sim_bind.isCheck();
                if(!check) {
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String simSerialNumber = tm.getSimSerialNumber();
                    if(TextUtils.isEmpty(simSerialNumber)) {
                        ToastUtil.show(getApplicationContext(),"未检测到SIM卡");
                        return;
                    }
                    SpUtils.putString(getApplicationContext(),Const.SIM_NUMBER,simSerialNumber);
                }else {
                    SpUtils.remove(getApplicationContext(),Const.SIM_NUMBER);
                }
                siv_sim_bind.setCheck(!check);
            }
        });
    }

    private void initUI() {
        siv_sim_bind = (SettingItemView) findViewById(R.id.siv_sim_bind);
        sim_number = SpUtils.getString(this, Const.SIM_NUMBER, "");
    }

    public void nextPage(View view) {
        String sim_number = SpUtils.getString(getApplicationContext(), Const.SIM_NUMBER, "");
        if(TextUtils.isEmpty(sim_number)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Setup2Activity.this);
            builder.setTitle("确认信息");
            builder.setMessage("尚未绑定sim卡,确认进行下一步？");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Setup2Activity.this,Setup3Activity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }else {
            Intent intent = new Intent(Setup2Activity.this,Setup3Activity.class);
            startActivity(intent);
            finish();
        }
    }
    public void previousPage(View view) {
        Intent intent = new Intent(this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }
}
