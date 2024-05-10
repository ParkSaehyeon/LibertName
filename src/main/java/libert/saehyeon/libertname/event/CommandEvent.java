package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertNameAPI;
import libert.saehyeon.libertname.util.ArrayUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CommandEvent implements Listener {
    @EventHandler
    void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] splits = e.getMessage().split(" ");

        String cmd    = splits[0];
        String[] args = e.getMessage().replace(cmd,"").split(" ");

        switch(cmd) {
            case "/pl":
            case "/minecraft:plugins":
            case "/bukkit:plugins":
            case "/bukkit:pl":
            case "/minecraft:pl":
                break;

            case "/tell":
            case "/minecraft:w":
            case "/minecraft:tell":
            case "/w":
                e.setCancelled(true);

                String target  = args[0];
                String content = ArrayUtil.toSliceString(args, 2);

                Player targetP = Bukkit.getPlayer(target);

                if(targetP != null) {

                    String from = LibertNameAPI.getName(p);
                    String to   = LibertNameAPI.getName(targetP);
                    String message = "§6[ "+from+"§f → "+to+"§6 ]§f §f "+content;

                    p.sendMessage(message);
                    targetP.sendMessage(message);

                } else {
                    p.sendMessage("§c해당 플레이어를 서버에서 찾을 수 없어요.");
                }
                break;
        }
    }
}
