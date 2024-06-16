package bolodev.bolodevincognito;

import bolodev.bolodevincognito.Gui.IncognitoGUI;
import bolodev.bolodevincognito.Listeners.IncognitoListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private final HashMap<UUID, Boolean> incognitoStatus = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new IncognitoListener(this), this);
        this.getCommand("incognito").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("bolodev.incognito")) {
                    IncognitoGUI.openIncognitoGUI(player, incognitoStatus.getOrDefault(player.getUniqueId(), false));
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Nie posiadasz permisji do tej komendy!");
                }
            }
            return false;
        });
    }

    public HashMap<UUID, Boolean> getIncognitoStatus() {
        return incognitoStatus;
    }
}