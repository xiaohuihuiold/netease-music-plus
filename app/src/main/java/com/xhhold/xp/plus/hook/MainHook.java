package com.xhhold.xp.plus.hook;

import android.content.Context;

import com.xhhold.xp.plus.base.MusicVersion;
import com.xhhold.xp.plus.base.VersionCheck;
import com.xhhold.xp.plus.data.ModuleApp;
import com.xhhold.xp.plus.impl.IVersionCheck;
import com.xhhold.xp.plus.load.ModuleLoaded;
import com.xhhold.xp.plus.util.ModuleUtils;

/**
 * @author nameh
 */
public class MainHook implements IVersionCheck {

    private Context contextNet;
    private ModuleLoaded moduleLoaded;
    private ModuleApp moduleApp;

    public MainHook(Context contextNet) {
        this.contextNet = contextNet;
        moduleLoaded = ModuleLoaded.getInstance(contextNet);
        moduleApp = moduleLoaded.getModuleApp();
        new VersionCheck(moduleApp.getContextNet(), moduleApp.getContextModule(), this);
    }

    @Override
    public void onCheckPerfect() {
        ModuleUtils.toast(contextNet, "版本匹配");
    }

    @Override
    public void onCheckImPerfect(String message) {
        ModuleUtils.toast(contextNet, message);
    }
}
