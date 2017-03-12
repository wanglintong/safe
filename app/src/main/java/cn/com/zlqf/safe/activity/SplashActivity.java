package cn.com.zlqf.safe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.entity.Version;
import cn.com.zlqf.safe.util.SpUtils;
import cn.com.zlqf.safe.util.StringUtil;
import cn.com.zlqf.safe.util.ToastUtil;

/**2017 3.9
*/
public class SplashActivity extends AppCompatActivity {
    private TextView tv_version;
    private int mLocalVersionCode;
    public static final int ENTER_HOME = 1;
    public static final int UPDATE = 2;
    public static final int EXCEPTION = 3;
    private RelativeLayout rl_root;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENTER_HOME:
                    enterHome();
                    break;
                case UPDATE:
                    Version version = (Version) msg.obj;
                    showUpdateDialog(version);
                    break;
                case EXCEPTION:
                    ToastUtil.show(getApplicationContext(),"exception");
                    break;
            }
        }
    };

    private void showUpdateDialog(Version version) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("发现新版本");
        builder.setMessage(version.getVersionDesc());
        builder.setPositiveButton("立即更新",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("稍后更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });
        builder.show();
    }

    private void enterHome() {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        initUI();
        initData();
        initAnimation();
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        rl_root.startAnimation(alphaAnimation);
    }

    private void initUI() {
        tv_version = (TextView) findViewById(R.id.tv_version);
        rl_root = (RelativeLayout) findViewById(R.id.activity_splash);
    }
    private void initData() {
        tv_version.setTextColor(Color.WHITE);
        tv_version.setText(getVersionName());
        mLocalVersionCode = getVersionCode();

        if(SpUtils.getBoolean(this, Const.OPEN_AUTO_UPDATE,false)) {
            checkVersionCode();
        }else {
            mHandler.sendEmptyMessageDelayed(ENTER_HOME,2000);
        }
    }

    private void checkVersionCode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                try{
                    URL url = new URL("http://192.168.0.87:8080/safeService/version/getLastestVersion.do");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(2000);
                    connection.setConnectTimeout(10000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200) {
                        InputStream inputStream = connection.getInputStream();
                        String retMsg = StringUtil.stream2String(inputStream);
                        Version version = JSON.parseObject(retMsg, Version.class);
                        if(mLocalVersionCode==version.getVersionCode()) {
                            message.what = ENTER_HOME;
                        }else {
                            message.obj = version;
                            message.what = UPDATE;
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    message.what = EXCEPTION;
                }finally {
                    SystemClock.sleep(2000);
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }

    private int getVersionCode() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String getVersionName() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
