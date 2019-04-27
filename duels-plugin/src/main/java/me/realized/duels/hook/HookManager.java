package me.realized.duels.hook;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.hook.hooks.*;
import me.realized.duels.util.hook.AbstractHookManager;

public class HookManager extends AbstractHookManager<DuelsPlugin> {

    public HookManager(final DuelsPlugin plugin) {
        super(plugin);
        register("BountyHunters", BountyHuntersHook.class);
        register("CombatLogX", CombatLogXHook.class);
        register("CombatTagPlus", CombatTagPlusHook.class);
        register("Essentials", EssentialsHook.class);
        register("Factions", FactionsHook.class);
        register("LeaderHeads", LeaderHeadsHook.class);
        register("mcMMO", McMMOHook.class);
        register("MVdWPlaceholderAPI", MVdWPlaceholderHook.class);
        register("MyPet", MyPetHook.class);
        register("ObsidianAuctions", ObsidianAuctionsHook.class);
        register("PlaceholderAPI", PlaceholderHook.class);
        register("PvPManager", PvPManagerHook.class);
        register("SimpleClans", SimpleClansHook.class);
        register("Vault", VaultHook.class);
        register("WorldGuard", WorldGuardHook.class);
    }
}
