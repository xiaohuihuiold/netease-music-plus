package com.xhhold.xp.plus.load;

/**
 * @author nameh
 */
public class ModuleLoaded {

    private static ModuleLoaded instance;

    private ModuleLoaded() {

    }

    public static ModuleLoaded getInstance() {
        if (instance == null) {
            synchronized (ModuleLoaded.class) {
                if (instance == null) {
                    instance = new ModuleLoaded();
                }
            }
        }
        return instance;
    }
}

