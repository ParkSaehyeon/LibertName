package libert.saehyeon.libertname.event;

import libert.saehyeon.libertname.LibertNameAPI;
import libert.saehyeon.libertname.util.ArrayUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        try {

            if(label.equals("리버트")) {

                if(sender != Bukkit.getConsoleSender() && !sender.isOp()) {
                    sender.sendMessage("§c권한이 없습니다.");
                    return false;
                }

                switch(args[0]) {

                    case "이름":

                        switch(args[1]) {
                            case "설정":
                                if(args.length < 4) {
                                    sender.sendMessage("§c사용법: /리버트 이름 설정 [UUID 또는 플레이어 이름] [설정될 이름]");
                                    return false;
                                }

                                String name = ArrayUtil.join(args, " ", 3).replace("&","§");

                                if(name.contains("|")) {
                                    sender.sendMessage("§c허용되지 않은 문자가 포함되어 있습니다.");
                                    return false;
                                }

                                if(LibertNameAPI.setName(args[2], name)) {
                                    sender.sendMessage("§7"+args[2]+"§f의 이름을 "+name+"§f(으)로 변경했습니다.");
                                } else {
                                    sender.sendMessage("§c해당 이름을 가진 플레이어가 서버에 없으며 올바른 UUID가 아닙니다.");
                                }
                                break;

                            case "제거":
                            case "삭제":
                                if(args.length != 3) {
                                    sender.sendMessage("§c사용법: /리버트 이름 [제거/삭제] [UUID 또는 플레이어 이름]");
                                    return false;
                                }

                                LibertNameAPI.removeName(args[2]);
                                sender.sendMessage("§7"+args[2]+"§f의 이름을 제거했습니다.");
                                break;

                            case "목록":
                                sender.sendMessage("");
                                sender.sendMessage("리버트 이름 목록:");
                                for(String key : LibertNameAPI.getNameKeys()) {
                                    sender.sendMessage(" - "+key+"§f  §f"+LibertNameAPI.getName(key));
                                }
                                break;

                            case "불러오기":
                                LibertNameAPI.load();
                                sender.sendMessage("리버트 이름을 다시 불러왔습니다.");
                                break;
                        }
                        break;
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("§c명령어 구문이 잘못되었습니다.");
        }
        return false;
    }
}
