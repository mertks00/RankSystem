package me.oyuncozucu.ranksystem.manager;

import me.oyuncozucu.ranksystem.Main;
import me.oyuncozucu.ranksystem.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.security.Permission;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private Main main;

    private File file;
    private YamlConfiguration data;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Main main) {

        this.main = main;

        if(!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "data.yml");

        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin) {

        if(Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {

            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;

            if(perms.containsKey(uuid)) {
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }

            for (String perm : getRank(uuid).getPermissions()) {
                if(player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm, true);
            }

        }

        data.set(uuid.toString(), rank.name());

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            main.getNameTagManager().removeTag(player);
            main.getNameTagManager().newTag(player);
        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(data.getString(uuid.toString()));
    }

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }
}
