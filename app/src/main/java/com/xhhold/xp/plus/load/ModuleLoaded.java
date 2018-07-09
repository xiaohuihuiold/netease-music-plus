package com.xhhold.xp.plus.load;

import android.content.Context;
import android.content.pm.PackageManager;

import com.xhhold.xp.plus.data.ModuleApp;
import com.xhhold.xp.plus.util.ModuleUtils;

/**
 * @author nameh
 */
public class ModuleLoaded {

    private static ModuleLoaded instance;

    private Context context;
    private ModuleApp moduleApp;

    private ModuleLoaded(Context context) {
        this.context = context;
        this.moduleApp = loadModule();
    }

    public static ModuleLoaded getInstance(Context context) {
        if (instance == null) {
            synchronized (ModuleLoaded.class) {
                if (instance == null) {
                    instance = new ModuleLoaded(context);
                }
            }
        }
        if (instance.getContext() == null) {
            instance.setContext(context);
            instance.setModuleApp(instance.loadModule());
        }
        return instance;
    }

    public ModuleApp loadModule() {
        Context contextModule = null;
        try {
            contextModule = context.createPackageContext(ModuleUtils.PACKAGE_MODULE, Context
                    .CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return ModuleUtils.loadAppFrom(context,
                ModuleUtils.PACKAGE_MODULE,
                ModuleUtils.CLASS_MODULE,
                new Class[]{Context.class, Context.class},
                new Object[]{context, contextModule});
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ModuleApp getModuleApp() {
        return moduleApp;
    }

    public void setModuleApp(ModuleApp moduleApp) {
        this.moduleApp = moduleApp;
    }
}

