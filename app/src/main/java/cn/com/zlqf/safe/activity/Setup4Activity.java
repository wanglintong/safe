package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.SpUtils;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Setup4Activity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
    }

    public void previousPage(View view) {
        Intent intent = new Intent(this,Setup3Activity.class);
        startActivity(intent);
        finish();
    }
    public void nextPage(View view) {
        Intent intent = new Intent(this,SetupOverActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(this, Const.SETUP_OVER, true);
        finish();
    }
}
