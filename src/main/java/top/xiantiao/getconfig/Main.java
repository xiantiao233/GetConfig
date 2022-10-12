package top.xiantiao.getconfig;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;

public class Main extends Plugin {
    public  Configuration config;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("demo的数值是：" + getConfig().getString("demo"));
        getLogger().info("正在修改值");
        getConfig().set("demo", "114514");
        saveConfig();
        getLogger().info("修改之后的数值为： " + getConfig().getString("demo"));
    }
    public void  saveDefaultConfig(){
        File dir = getDataFolder();
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir,"config.yml");
        if (!file.exists()) {
            /*
            1
            1
            1
            1
            1
            1
            1
            1
            1
             */
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in,file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void reloadConfig(){
        File file = new File(getDataFolder(), "config.yml");
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void  saveConfig() {
        File file= new File(getDataFolder(),"config.yml");
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Configuration getConfig() {
        return config;
    }
}
