package com.xhhold.xp.plus.module;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.xhhold.xp.plus.data.ModuleApp;
import com.xhhold.xp.plus.load.ModuleLoaded;
import com.xhhold.xp.plus.util.ModuleUtils;

import java.util.Collection;
import java.util.HashMap;

import de.robv.android.xposed.XposedHelpers;

/**
 * Created by nameh on 2018/2/16 0016.
 */

public abstract class BaseModule {

    private ModuleApp moduleApp;
    private int versionCode;

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, HashMap<String, Object>> mfMap = new HashMap<>();

    public BaseModule() {
        moduleApp = ModuleLoaded.getInstance(null).getModuleApp();
        versionCode = ModuleUtils.MODULE_VERSION_CODE;
        init();
    }

    public abstract void init();

    public void createVersion(int version) {
        mfMap.put(version, new HashMap<String, Object>());
    }

    public void addParam(int version, String type, Object param) {
        HashMap<String, Object> mf = mfMap.get(version);
        if (mf != null) {
            mfMap.get(version).put(type, param);
        }
    }

    public String $STRING(String type) {
        Object object = $(type);
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public int $INT(String type) {
        Object object = $(type);
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        }
        return 0;
    }

    public Object $(String type) {
        int version = ModuleUtils.MODULE_VERSION_CODE;
        Object result = null;
        HashMap<String, Object> mf = mfMap.get(version);
        if (mf != null) {
            result = mf.get(type);
        } else {
            Collection<HashMap<String, Object>> mfs = mfMap.values();
            for (HashMap<String, Object> maps : mfs) {
                if (result == null) {
                    result = maps.get(type);
                }
            }
        }
        return result;
    }

    public ModuleApp getModuleApp() {
        return moduleApp;
    }

    public int getVersionCode() {
        return versionCode;
    }
}
