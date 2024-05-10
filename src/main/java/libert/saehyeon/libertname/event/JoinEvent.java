package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertName;
import libert.saehyeon.libertname.LibertNameAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Bukkit.getScheduler().runTaskLater(LibertName.ins, () -> {
            LibertNameAPI.applyName(p);
        },2);
    }
}
