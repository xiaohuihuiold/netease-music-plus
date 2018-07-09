package com.xhhold.xp.plus.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import com.xhhold.xp.plus.base.MusicVersion;
import com.xhhold.xp.plus.data.ModuleApp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

/**
 * @author nameh
 */
public class ModuleUtils {
    public static final String PACKAGE_HOOK = "com.netease.cloudmusic";
    public static final String PACKAGE_MODULE = "com.xhhold.xp.plus.module";
    public static final String CLASS_MODULE = "com.xhhold.xp.plus.module.Main";

    public static final String META_DATA_VERSION="netversion";

    public static int MODULE_VERSION_CODE = 0;

    public static final List<MusicVersion> PACKAGE_VERSIONS = new ArrayList<>();

    static {
        PACKAGE_VERSIONS.add(new MusicVersion(120, "5.3.1"));
    }

    public static ModuleApp loadAppFrom(Context context, String packageName, String className) {
        return loadAppFrom(context, packageName, className, new Class[]{}, new Object[]{});
    }

    public static ModuleApp loadAppFrom(Context context, String packageName, String className,
                                        Class[] classes,
                                        Object[]
                                                objects) {
        ModuleApp moduleApp = null;
        if (context == null) {
            return moduleApp;
        }
        try {
            Context contextModule = context.createPackageContext(packageName, Context
                    .CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            ApplicationInfo applicationInfo = contextModule.getApplicationInfo();

            String pathDex = applicationInfo.sourceDir;

            PathClassLoader pathClassLoader = new PathClassLoader(pathDex,
                    ModuleUtils.class.getClassLoader());
            Class<?> clazz = Class.forName(className, true, pathClassLoader);

            Constructor constructor = clazz.getConstructor(classes);
            Object object = constructor.newInstance(objects);

            moduleApp = new ModuleApp();
            moduleApp.setObject(object);
            moduleApp.setContextNet(context);
            moduleApp.setContextModule(contextModule);
            moduleApp.setClassLoader(pathClassLoader);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return moduleApp;
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
