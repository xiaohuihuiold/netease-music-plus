package com.xhhold.xp.plus.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.xhhold.xp.plus.impl.IVersionCheck;
import com.xhhold.xp.plus.util.ModuleUtils;

import java.util.ArrayList;
import java.util.List;

public class VersionCheck {

    private Context contextNet;
    private Context contextModule;
    private IVersionCheck iVersionCheck;

    private int versionNet;
    private int versionModule;

    public VersionCheck(Context contextNet, Context contextModule, IVersionCheck iVersionCheck) {
        this.contextNet = contextNet;
        this.contextModule = contextModule;
        this.iVersionCheck = iVersionCheck;
        check();
    }

    private void check() {
        if (iVersionCheck == null) {
            return;
        }
        try {
            versionModule = contextModule.getPackageManager().getApplicationInfo(contextModule.getPackageName(),
                    PackageManager.GET_META_DATA)
                    .metaData.getInt
                            (ModuleUtils
                                    .META_DATA_VERSION);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        PackageManager packageManager = contextNet.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(contextNet.getPackageName(), 0);
            versionNet = packageInfo.versionCode;
            ModuleUtils.MODULE_VERSION_CODE = versionNet;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (!check(ModuleUtils.PACKAGE_VERSIONS)) {
            iVersionCheck.onCheckImPerfect("模块版本不匹配,请下载包含" + versionNet + "的版本");
            return;
        }
        if (versionModule == versionNet) {
            iVersionCheck.onCheckPerfect();
        } else {
            iVersionCheck.onCheckImPerfect("Module版本+(" + versionModule + ")+不匹配,请下载" + versionNet + "版本");
        }
    }

    private boolean check(List<MusicVersion> musicVersionArrayList) {
        boolean isCheck = false;
        for (MusicVersion mv : musicVersionArrayList) {
            if (mv.versionCode == versionNet) {
                isCheck = true;
                break;
            }
        }
        return isCheck;
    }
}
