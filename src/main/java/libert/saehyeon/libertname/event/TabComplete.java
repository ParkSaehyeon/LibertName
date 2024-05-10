package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertNameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(alias.equals("리버트")) {
            if(args.length == 1) {
                return Arrays.asList("이름");
            }

            else if(args.length == 2) {
                if(args[0].equals("이름")) {
                    return Arrays.asList("설정","제거","삭제","불러오기", "목록");
                }
            }

            else if(args.length == 3) {
                if(args[0].equals("제거") || args[0].equals("삭제")) {
                    return LibertNameAPI.getNameKeys();
                }
            }
        }
        return null;
    }
}
