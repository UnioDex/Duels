package me.realized.duels.extra;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.arena.ArenaManager;
import me.realized.duels.spectate.SpectateManager;
import net.minelink.ctplus.event.CombatLogEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CombatTagListener implements Listener {

    private final ArenaManager arenaManager;
    private final SpectateManager spectateManager;

    public CombatTagListener(final DuelsPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.arenaManager = plugin.getArenaManager();
        this.spectateManager = plugin.getSpectateManager();
    }

    @EventHandler(ignoreCancelled = true)
    public void on(final CombatLogEvent event) {
        final Player player = event.getPlayer();

        if (arenaManager.isInMatch(player) || spectateManager.isSpectating(player)) {
            event.setCancelled(true);
        }
    }
}
