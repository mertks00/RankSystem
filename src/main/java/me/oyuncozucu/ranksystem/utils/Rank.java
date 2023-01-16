package me.oyuncozucu.ranksystem.utils;

import me.oyuncozucu.ranksystem.utils.Colorize;
import org.bukkit.ChatColor;

public enum Rank {


    FOUNDER(Colorize.format("&7[&6Founder &7] &e"), new String[]{"worldedit.help"}),
    ADMIN(Colorize.format("&7[&3Admin &7] &b"), new String[]{"worldedit.help"}),
    MEMBER(Colorize.format("&7[&2Member &7] &a"), new String[]{}),
    GUEST(Colorize.format("&7[&5Guest &7] &d"), new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() {
        return display;
    }

    public String[] getPermissions() {
        return permissions;
    }
}
