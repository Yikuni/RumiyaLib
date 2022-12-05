package com.yikuni.mc.rumiyalib;

import com.yikuni.mc.rumiyalib.command.DebugCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class RumiyaLib extends JavaPlugin {
    private static RumiyaLib instance = null;
    public static Boolean debug = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getLogger().info("Loaded RumiyaLib");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static RumiyaLib getInstance(){
        return instance;
    }
}
