package me.realized.duels.gui.settings.buttons;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.gui.BaseButton;
import me.realized.duels.setting.Settings;
import me.realized.duels.util.inventory.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RequestDetailsButton extends BaseButton {

    public RequestDetailsButton(final DuelsPlugin plugin) {
        super(plugin, ItemBuilder.of(Material.SIGN).name(plugin.getLang().getMessage("GUI.settings.buttons.details.name")).build());
    }

    @Override
    public void update(final Player player) {
        final Settings settings = settingManager.getSafely(player);
        final Player target = Bukkit.getPlayer(settings.getTarget());

        if (target == null) {
            settings.reset();
            player.closeInventory();
            lang.sendMessage(player, "ERROR.player.no-longer-online");
            return;
        }

        final String lore = lang.getMessage("GUI.settings.buttons.details.lore",
            "opponent", target.getName(),
            "kit", settings.getKit() != null ? settings.getKit().getName() : "Seçilmedi",
            "arena", settings.getArena() != null ? settings.getArena().getName() : "Rastgele",
            "item_betting", settings.isItemBetting() ? "&aetkin" : "&cdevre dışı",
            "bet_amount", settings.getBet()
        );
        setLore(lore.split("\n"));
    }
}
