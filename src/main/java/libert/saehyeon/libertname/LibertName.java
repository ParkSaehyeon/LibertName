package libert.saehyeon.libertname;

import libert.saehyeon.libertname.event.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LibertName extends JavaPlugin {
    public static LibertName ins;

    @Override
    public void onEnable() {
        ins = this;

        getDataFolder().mkdir();

        Bukkit.getPluginCommand("리버트").setExecutor(new Command());
        Bukkit.getPluginCommand("리버트").setTabCompleter(new TabComplete());

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);

        LibertNameAPI.load();
    }

    @Override
    public void onDisable() {
        LibertNameAPI.save();
    }
}
