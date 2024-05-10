package libert.saehyeon.libertname;

import libert.saehyeon.libertname.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class LibertNameAPI {
    private static HashMap<String, String> nameMap = new HashMap<>();

    public static List<String> getNameKeys() {
        return new ArrayList<>(nameMap.keySet());
    }
    public static boolean setName(String str, String name) {

        if(str.length() == 36) {
            nameMap.put(str, name);
            save();
            return true;
        }

        Player target = Bukkit.getPlayer(str);

        if(target != null) {
            nameMap.put(target.getUniqueId().toString(), name);
            LibertNameAPI.applyName(target);
            save();
            return true;
        }

        return false;
    }

    public static void applyName(Player p) {
        String name = LibertNameAPI.getName(p);

        ArrayList<Team> teams = new ArrayList<>(p.getScoreboard().getTeams());

        String prefix = teams.stream().map(Team::getPrefix).collect(Collectors.joining(""));
        String suffix = teams.stream().map(Team::getSuffix).collect(Collectors.joining(""));
        ChatColor color = teams.get(teams.size()-1).getColor();

        p.setPlayerListName(color+prefix+ChatColor.stripColor(name)+suffix);
    }

    public static boolean removeName(String key) {

        if(key.length() == 36) {
            nameMap.remove(key);
            save();
            return true;
        }

        Player target = Bukkit.getPlayer(key);

        if(target != null) {
            nameMap.remove(target.getUniqueId().toString());
            LibertNameAPI.applyName(target);
            save();
            return true;
        }

        return false;
    }

    public static String getName(Player p) {
        return nameMap.getOrDefault(p.getUniqueId().toString(), p.getName())+"§f";
    }

    public static String getName(String uuid) {
        return nameMap.getOrDefault(uuid, "(알 수 없음)");
    }

    public static void applyNameAll() {
        Bukkit.getOnlinePlayers().forEach(LibertNameAPI::applyName);
    }

    public static void load() {
        String root = LibertName.ins.getDataFolder()+"/";

        try {
            String path = root+"names.txt";

            FileUtil.createFile(path);

            String namesStr = FileUtil.readFile(root+"names.txt");
            int nameAmount  = 0;

            for(String kv : namesStr.split("\n")) {

                if(!kv.isEmpty()) {
                    String k = kv.split("\\|")[0];
                    String v = kv.split("\\|")[1];

                    nameMap.put(k,v);
                    nameAmount++;
                }
            }

            applyNameAll();

            log(nameAmount+"개의 이름을 불러왔습니다.");

        } catch (Exception e) {
            log(ChatColor.RED+"이름 목록을 불러오지 못했습니다.");
        }
    }

    public static void save() {
        String root = LibertName.ins.getDataFolder()+"/";

        try {
            StringBuilder sb = new StringBuilder();

            for(Map.Entry<String, String> kv : nameMap.entrySet()) {
                String k = kv.getKey();
                String v = kv.getValue();

                sb.append(k+"|"+v+"\n");
            }

            // 파일로 텍스트 저장
            FileUtil.writeFile(root+"names.txt", sb.toString());

            log("이름 목록을 저장했습니다.");
        } catch (Exception e) {
            log("§c이름 목록을 저장하지 못했습니다.");
        }
    }

    public static void log(String message) {
        LibertName.ins.getLogger().log(Level.INFO, "[LibertNameAPI]: "+message);
    }
}
