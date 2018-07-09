package com.xhhold.xp.plus.base;

/**
 * Created by nameh on 2018/2/17 0017.
 */

public class MusicVersion {

    public int versionCode;
    public String versionName;

    public MusicVersion() {

    }

    public MusicVersion(int versionCode, String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

}
