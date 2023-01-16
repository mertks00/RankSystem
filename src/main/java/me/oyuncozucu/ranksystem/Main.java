package me.oyuncozucu.ranksystem;

import me.oyuncozucu.ranksystem.commands.RankCommand;
import me.oyuncozucu.ranksystem.listeners.RankListener;
import me.oyuncozucu.ranksystem.manager.NameTagManager;
import me.oyuncozucu.ranksystem.manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private RankManager rankManager;
    private NameTagManager nameTagManager;

    @Override
    public void onEnable() {

        getCommand("rank").setExecutor(new RankCommand(this));

        rankManager = new RankManager(this);
        nameTagManager = new NameTagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

    }


    public RankManager getRankManager() {
        return rankManager;
    }

    public NameTagManager getNameTagManager() {
        return nameTagManager;
    }
}
