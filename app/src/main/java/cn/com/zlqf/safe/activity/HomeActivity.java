package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.MD5Util;
import cn.com.zlqf.safe.util.SpUtils;
import cn.com.zlqf.safe.util.ToastUtil;

/**
 * Created by Administrator on 2017/3/9.
 */
public class HomeActivity extends AppCompatActivity{
    private int quitCount = 0;
    private GridView gv_home;
    private String[] mTitles;
    private int[] mImgs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUI();
        initData();
    }

    private void initData() {
        mTitles = new String[]{
                "手机防盗","通信卫视","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"
        };
        mImgs = new int[] {
                R.mipmap.home_safe,R.mipmap.home_callmsgsafe,
                R.mipmap.home_apps,R.mipmap.home_taskmanager,
                R.mipmap.home_netmanager,R.mipmap.home_trojan,
                R.mipmap.home_sysoptimize,R.mipmap.home_tools,
                R.mipmap.home_settings
        };
        gv_home.setAdapter(new MyAdapter());
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //手机防盗
                        showDialog();
                        break;
                    case 8:
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void showDialog() {
        String pwd = SpUtils.getString(this, Const.SAFE_PHONE_PWD, "");
        if(TextUtils.isEmpty(pwd)) {
            //初始化密码
            showSetPwdDialog();
        }else {
            //确认密码
            showConfirmDialog();
        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog confirmPwdDialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_confirm_pwd, null);
        confirmPwdDialog.setView(view);
        confirmPwdDialog.show();

        Button bt_confirm = (Button) view.findViewById(R.id.bt_confirm);
        final Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPwdDialog.dismiss();
            }
        });
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dbPwd = SpUtils.getString(getApplicationContext(), Const.SAFE_PHONE_PWD, "");
                EditText et_pwd = (EditText) view.findViewById(R.id.et_pwd);
                String pwd = et_pwd.getText().toString();
                if(!TextUtils.isEmpty(pwd)){
                    if(MD5Util.encode(pwd).equals(dbPwd)) {
                        Intent intent = new Intent(getApplicationContext(),SetupOverActivity.class);
                        startActivity(intent);
                        confirmPwdDialog.dismiss();
                    }else {
                        ToastUtil.show(getApplicationContext(),"密码错误");
                    }
                }else {
                    ToastUtil.show(getApplicationContext(),"密码不能为空");
                }
            }
        });
    }

    private void showSetPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog setPwdDialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_set_pwd, null);
        setPwdDialog.setView(view);
        setPwdDialog.show();

        Button bt_confirm = (Button) view.findViewById(R.id.bt_confirm);
        final Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPwdDialog.dismiss();
            }
        });
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_pwd = (EditText) view.findViewById(R.id.et_pwd);
                EditText et_repwd = (EditText) view.findViewById(R.id.et_repwd);
                String pwd = et_pwd.getText().toString();
                String repwd = et_repwd.getText().toString();
                if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(repwd)){
                    if(pwd.equals(repwd)) {
                        //保存密码
                        SpUtils.putString(getApplicationContext(),Const.SAFE_PHONE_PWD, MD5Util.encode(pwd));
                        Intent intent = new Intent(getApplicationContext(),SetupOverActivity.class);
                        startActivity(intent);
                        setPwdDialog.dismiss();
                    }else {
                        ToastUtil.show(getApplicationContext(),"密码输入不一致");
                    }
                }else {
                    ToastUtil.show(getApplicationContext(),"密码不能为空");
                }
            }
        });
    }

    private void initUI() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.gridview_item, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(mTitles[position]);
            iv_icon.setImageResource(mImgs[position]);

            return view;
        }
    }

    @Override
    public void onBackPressed() {
        if(quitCount==0) {
            quitCount = 1;
            ToastUtil.show(this,"在按一次退出");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    quitCount = 0;
                }
            },2000);
            return;
        }
        super.onBackPressed();
    }
}
