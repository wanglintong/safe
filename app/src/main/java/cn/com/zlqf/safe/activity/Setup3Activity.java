package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.zlqf.safe.R;


/**
 * Created by Administrator on 2017/3/11.
 */
public class Setup3Activity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
    }
    public void nextPage(View view) {
        Intent intent = new Intent(this,Setup4Activity.class);
        startActivity(intent);
        finish();
    }
    public void previousPage(View view) {
        Intent intent = new Intent(this,Setup2Activity.class);
        startActivity(intent);
        finish();
    }
}
