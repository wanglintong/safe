package cn.com.zlqf.safe.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/9.
 */
public class ToastUtil {
    public static void show(Context applicationContext, String update) {
        Toast.makeText(applicationContext,update,Toast.LENGTH_SHORT).show();
    }
}
