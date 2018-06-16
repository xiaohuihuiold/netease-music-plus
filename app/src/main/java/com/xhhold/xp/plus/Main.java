package com.xhhold.xp.plus;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.xhhold.xp.plus.util.ModuleUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author nameh
 */
public class Main implements IXposedHookLoadPackage{
    private final String modulePack = "com.xhhold.xp.plus";
    private final String handleClass = Module.class.getName();
    private String moduleFile=null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if(lpparam.packageName.equals(ModuleUtils.HOOK_PACKAGE)) {
            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Context context=(Context)param.args[0];
                    lpparam.classLoader=context.getClassLoader();
                    Context con=context.createPackageContext(modulePack, Context.CONTEXT_IGNORE_SECURITY);
                    if(con!=null){
                        moduleFile=con.getApplicationInfo().sourceDir;
                    }
                    if(moduleFile!=null){
                        PathClassLoader pathClassLoader=new PathClassLoader(moduleFile,ClassLoader.getSystemClassLoader());
                        Class<?> cls=Class.forName(handleClass,true,pathClassLoader);
                        Constructor constructor=cls.getConstructor();
                        constructor.newInstance();
                    }
                }
            });
        }
    }
}
