package me.oyuncozucu.ranksystem.manager;

import me.oyuncozucu.ranksystem.Main;
import me.oyuncozucu.ranksystem.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTagManager {

    private Main main;

    public NameTagManager(Main main) {
        this.main = main;
    }

    public void setNameTags(Player player) {

        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for(Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay());
        }

        for(Player target : Bukkit.getOnlinePlayers()) {
            if(player.getUniqueId() != target.getUniqueId()) {
                player.getScoreboard().getTeam(main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
            }

        }

    }

    public void newTag(Player player) {

        Rank rank = main.getRankManager().getRank(player.getUniqueId());

        for(Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }

    }

    public void removeTag(Player player) {

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }

    }

}
