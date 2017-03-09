package cn.com.zlqf.safe.entity;

/**
 * Created by Administrator on 2017/3/9.
 */

public class Version {
    private String versionName;
    private int versionCode;
    private String versionDesc;
    private String downloadUrl;

    public String getVersionName() {
        return versionName;
    }
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }
    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
    public String getVersionDesc() {
        return versionDesc;
    }
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
    public String getDownloadUrl() {
        return downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
