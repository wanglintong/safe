package cn.com.zlqf.safe.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/9.
 */

public class SpUtils {

    private static SharedPreferences sp;
    public static void putBoolean(Context ctx,String key,boolean value) {
        if(sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context ctx,String key,boolean defValue) {
        if(sp == null) {
            sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
}
