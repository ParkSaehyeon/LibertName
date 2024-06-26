package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertName;
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
            case "/team":
                Bukkit.getScheduler().runTaskLater(LibertName.ins, () -> {
                    LibertNameAPI.applyNameAll();
                },2);
                break;

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

                if(args.length <= 2 || args[1].isEmpty()) {
                    p.sendMessage("§c명령어 구문이 올바르지 않아요. (사용법: "+cmd+" [플레이어 이름] [말할 내용])");
                    return;
                }

                String target  = args[0];
                String content = ArrayUtil.join(args," ", 2);

                Player targetP = Bukkit.getPlayer(target);

                if(targetP != null) {

                    String from = LibertNameAPI.getName(p);
                    String to   = LibertNameAPI.getName(targetP);
                    String message = "§6[ §f"+from+"§f → "+to+"§6 ]§f "+content;

                    p.sendMessage(message);
                    targetP.sendMessage(message);

                } else {
                    p.sendMessage("§c해당 플레이어를 서버에서 찾을 수 없어요.");
                }
                break;
        }
    }
}
