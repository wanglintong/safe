package cn.com.zlqf.safe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/3/10.
 */

public class MD5Util {
    public static String encode(String msg) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(msg.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : digest) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if(hexString.length()<2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
