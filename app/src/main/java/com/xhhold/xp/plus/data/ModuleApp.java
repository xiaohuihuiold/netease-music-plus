package com.xhhold.xp.plus.data;

import android.content.Context;
import android.content.res.Resources;

import dalvik.system.PathClassLoader;

/**
 * @author nameh
 */
public class ModuleApp {
    private Object object;
    private Context contextNet;
    private Context contextModule;
    private Resources resourcesNet;
    private Resources resourcesModule;
    private PathClassLoader classLoader;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Context getContextNet() {
        return contextNet;
    }

    public void setContextNet(Context contextNet) {
        this.contextNet = contextNet;
    }

    public Context getContextModule() {
        return contextModule;
    }

    public void setContextModule(Context contextModule) {
        this.contextModule = contextModule;
    }

    public Resources getResourcesNet() {
        return contextNet != null ? contextNet.getResources() : null;
    }

    public Resources getResourcesModule() {
        return contextModule != null ? contextModule.getResources() : null;
    }

    public PathClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(PathClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
