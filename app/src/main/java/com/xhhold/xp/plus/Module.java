package com.xhhold.xp.plus;

import android.content.Context;

import com.xhhold.xp.plus.hook.MainHook;
import com.xhhold.xp.plus.load.ModuleLoaded;
import com.xhhold.xp.plus.util.ModuleUtils;

/**
 * @author nameh
 */
public class Module {
    public Module(Context context){
        try {
            ModuleLoaded.getInstance(context);
            new MainHook(context);
        }catch (Exception e){
            e.printStackTrace();
            ModuleUtils.toast(context,e.getMessage());
        }
    }
}
