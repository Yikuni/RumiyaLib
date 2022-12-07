package com.yikuni.mc.rumiyalib;

import com.yikuni.mc.reflect.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class RumiyaLib extends JavaPlugin {
    private static RumiyaLib instance = null;
    public static Boolean debug = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginLoader.run(RumiyaLib.class);
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static RumiyaLib getInstance(){
        return instance;
    }
}
