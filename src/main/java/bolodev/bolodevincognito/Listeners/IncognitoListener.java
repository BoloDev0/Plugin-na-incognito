package bolodev.bolodevincognito.Listeners;

import bolodev.bolodevincognito.Gui.IncognitoGUI;
import bolodev.bolodevincognito.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class IncognitoListener implements Listener {

    private final Main plugin;

    public IncognitoListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Incognito")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.GREEN_WOOL) {
                Player player = (Player) event.getWhoClicked();
                UUID playerId = player.getUniqueId();
                boolean isIncognito = plugin.getIncognitoStatus().getOrDefault(playerId, false);

                if (isIncognito) {
                    plugin.getIncognitoStatus().put(playerId, false);
                    player.setDisplayName(player.getName());
                    player.setPlayerListName(player.getName());
                    player.setCustomName(player.getName());
                    player.setCustomNameVisible(false);
                    player.sendMessage(ChatColor.RED + "Incognito zostało wyłączone!");
                } else {
                    String incognitoName = "UKRYTY_" + RandomStringGenerator.generateRandomString(5);
                    plugin.getIncognitoStatus().put(playerId, true);
                    player.setDisplayName(incognitoName);
                    player.setPlayerListName(incognitoName);
                    player.setCustomName(incognitoName);
                    player.setCustomNameVisible(true);
                    player.sendMessage(ChatColor.GREEN + "Incognito zostało pomyślnie włączone!");
                }

                player.closeInventory();
                IncognitoGUI.openIncognitoGUI(player, plugin.getIncognitoStatus().get(playerId));
            }
        }
    }
}
