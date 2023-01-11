package com.yikuni.mc.rumiyalib;

import com.yikuni.db.exception.YikuniDBException;
import com.yikuni.db.main.Database;
import com.yikuni.db.main.JsonSerializeStrategy;
import com.yikuni.db.main.Table;
import com.yikuni.mc.reflect.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class RumiyaLib extends JavaPlugin {
    public Logger log = this.getLogger();
    public static Boolean debug = false;
    public static Database DB;
    public static Table<String> forbiddenBanTable;

    @Override
    public void onEnable() {
        // Plugin startup logic
        initDB();
        PluginLoader.run(RumiyaLib.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DB.save();
    }
    public static RumiyaLib getInstance(){
        return JavaPlugin.getPlugin(RumiyaLib.class);
    }
    private void initDB(){
        File dataFolder = this.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();
        File database = new File(dataFolder, "database");
        if (!database.exists()) database.mkdir();
        try {
            DB = new Database(database.getAbsolutePath(), 1000L * 60 * 60);
            if (DB.existTable("ForbiddenBan")){
                forbiddenBanTable = DB.getTable("ForbiddenBan", String.class);
            }else {
                forbiddenBanTable = DB.createTable("ForbiddenBan", String.class);
            }
            log.info("Loaded Databases");
        } catch (YikuniDBException e) {
            log.severe(String.format("[%s] - Disabled due to Database Failed to Setup!", getDescription().getName()));
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
