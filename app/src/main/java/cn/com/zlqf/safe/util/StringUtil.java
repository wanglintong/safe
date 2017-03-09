package cn.com.zlqf.safe.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/9.
 */
public class StringUtil {
    public static String stream2String(InputStream inputStream) {
        int len = 0;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len=inputStream.read(buffer))>0) {
                bos.write(buffer,0,len);
            }
            return bos.toString();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                inputStream.close();
                bos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
