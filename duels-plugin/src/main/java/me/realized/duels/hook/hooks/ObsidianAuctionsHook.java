package me.realized.duels.hook.hooks;

import com.flobi.floauction.Auction;
import com.flobi.floauction.AuctionScope;
import com.flobi.floauction.FloAuction;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.config.Config;
import me.realized.duels.util.hook.PluginHook;
import org.bukkit.entity.Player;

public class ObsidianAuctionsHook extends PluginHook<DuelsPlugin> {

    private final Config config;

    public ObsidianAuctionsHook(final DuelsPlugin plugin) {
        super(plugin, "ObsidianAuctions");
        this.config = plugin.getConfiguration();
    }

    public boolean canJoinDuel(Player player) {
        return (!(inAuction(player) || isBidding(player) || inQueue(player)));
    }

    private boolean inAuction(Player player) {
        return FloAuction.getPlayerAuction(player) != null;
    }

    private boolean isBidding(Player player) {
        if (AuctionScope.getPlayerScope(player).getActiveAuction() != null) {
            if (AuctionScope.getPlayerScope(player).getActiveAuction().getCurrentBid() != null) {
                return AuctionScope.getPlayerScope(player).getActiveAuction().getCurrentBid().getBidder().equalsIgnoreCase(player.getName());
            }
        }
        return false;
    }

    private boolean inQueue(Player player) {
        for (Auction auc : AuctionScope.getPlayerScope(player).getAuctionQueue()) {
            if (auc.getOwner().equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }
}
