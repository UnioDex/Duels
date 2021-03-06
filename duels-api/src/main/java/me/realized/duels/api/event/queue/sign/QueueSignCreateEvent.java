package me.realized.duels.api.event.queue.sign;

import javax.annotation.Nonnull;
import me.realized.duels.api.queue.sign.QueueSign;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a {@link QueueSign} is created.
 *
 * @since 3.2.0
 */
public class QueueSignCreateEvent extends QueueSignEvent {

    private static final HandlerList handlers = new HandlerList();

    public QueueSignCreateEvent(@Nonnull final Player source, @Nonnull final QueueSign queueSign) {
        super(source, queueSign);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
