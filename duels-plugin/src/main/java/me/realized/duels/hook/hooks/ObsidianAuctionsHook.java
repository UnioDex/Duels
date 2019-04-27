package me.realized.duels.hook.hooks;

import com.flobi.floauction.Auction;
import com.flobi.floauction.AuctionScope;
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

    /* Queue or auction */
    public boolean isOnAuction(final Player player) {
        Auction auction = null;
        AuctionScope userScope = AuctionScope.getPlayerScope(player);

        if (userScope != null) {
            auction = userScope.getActiveAuction();
            if (auction != null) {
                if (auction.getOwner().equalsIgnoreCase(player.getName())) {
                    return true; // ON AUCTION
                }
                if (auction.getCurrentBid() != null) {
                    if (auction.getCurrentBid().getBidder().equalsIgnoreCase(player.getName())) {
                        return true; // BIDDED ON AN AUCTION
                    }
                }

                for (int i = 0; i < userScope.getAuctionQueueLength(); i++) {
                    if (userScope.getAuctionQueue().get(i) != null) {
                        Auction queuedAuction = userScope.getAuctionQueue().get(i);
                        if (queuedAuction.getOwner().equalsIgnoreCase(player.getName())) {
                            return true; // ON QUEUE
                        }
                    }
                }

            }
        }
        return false;
    }
}
