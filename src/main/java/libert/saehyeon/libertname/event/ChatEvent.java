package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertNameAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    void onChat(AsyncPlayerChatEvent e) {
        if(e.getPlayer().isOp()) {
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }
        e.setFormat(LibertNameAPI.getName(e.getPlayer()) + ":§f "  + e.getMessage());
    }
}
