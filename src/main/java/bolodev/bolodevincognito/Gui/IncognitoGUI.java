package bolodev.bolodevincognito.Gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class IncognitoGUI {

    public static void openIncognitoGUI(Player player, boolean isIncognito) {
        Inventory gui = Bukkit.createInventory(null, 27, "Incognito");

        ItemStack greenWool = new ItemStack(Material.GREEN_WOOL);
        ItemMeta woolMeta = greenWool.getItemMeta();
        woolMeta.setDisplayName(ChatColor.GREEN + "INCOGNITO");
        String status = isIncognito ? ChatColor.GREEN + "Włączone" : ChatColor.RED + "Wyłączone";
        woolMeta.setLore(Arrays.asList(ChatColor.GRAY + "Status: " + status));
        greenWool.setItemMeta(woolMeta);

        ItemStack glassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glassPane.getItemMeta();
        glassMeta.setDisplayName(" ");
        glassPane.setItemMeta(glassMeta);

        for (int i = 0; i < 27; i++) {
            if (i == 13) {
                gui.setItem(i, greenWool);
            } else {
                gui.setItem(i, glassPane);
            }
        }

        player.openInventory(gui);
    }
}