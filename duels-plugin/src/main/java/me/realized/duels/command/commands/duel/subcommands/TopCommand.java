package me.realized.duels.command.commands.duel.subcommands;

import java.util.List;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.api.user.UserManager.TopData;
import me.realized.duels.api.user.UserManager.TopEntry;
import me.realized.duels.command.BaseCommand;
import me.realized.duels.extra.Permissions;
import me.realized.duels.kit.Kit;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class TopCommand extends BaseCommand {

    public TopCommand(final DuelsPlugin plugin) {
        super(plugin, "top", null, null, Permissions.TOP, 1, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        if (!userManager.isLoaded()) {
            lang.sendMessage(sender, "ERROR.data.not-loaded");
            return;
        }

        if (args.length < 2) {
            lang.sendMessage(sender, "COMMAND.duel.top-usage");
            return;
        }

        final TopEntry topEntry;

        if (args[1].equalsIgnoreCase("wins")) {
            topEntry = userManager.getWins();
        } else if (args[1].equalsIgnoreCase("losses")) {
            topEntry = userManager.getLosses();
        } else {
            final String name = StringUtils.join(args, " ", 1, args.length);
            final Kit kit = kitManager.get(name);

            if (kit == null) {
                lang.sendMessage(sender, "ERROR.kit.not-found", "name", name);
                return;
            }

            topEntry = userManager.getTopRatings().get(kit);
        }

        final List<TopData> top;

        if (topEntry == null || (top = topEntry.getData()).isEmpty()) {
            lang.sendMessage(sender, "ERROR.top.no-data-available");
            return;
        }

        lang.sendMessage(sender, "COMMAND.duel.top.next-update", "remaining", userManager.getNextUpdate(topEntry.getCreation()));
        lang.sendMessage(sender, "COMMAND.duel.top.header", "type", topEntry.getType());

        for (int i = 0; i < top.size(); i++) {
            final TopData data = top.get(i);
            lang.sendMessage(sender, "COMMAND.duel.top.display-format",
                "rank", i + 1, "name", data.getName(), "score", data.getValue(), "identifier", topEntry.getIdentifier());
        }

        lang.sendMessage(sender, "COMMAND.duel.top.footer", "type", topEntry.getType());
    }
}
