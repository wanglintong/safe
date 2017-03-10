package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.com.zlqf.safe.Const;
import cn.com.zlqf.safe.R;
import cn.com.zlqf.safe.util.SpUtils;

/**
 * Created by Administrator on 2017/3/10.
 */
public class SetupOverActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean setup_over = SpUtils.getBoolean(this, Const.SETUP_OVER, false);
        if(setup_over) {
            setContentView(R.layout.activity_setup_over);
        }else {
            Intent intent = new Intent(this,Setup1Activity.class);
            startActivity(intent);
            finish();
        }

    }
}
