package net.player005.vanillablocking;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VanillaBlockingPaper extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    /**
     * Updates the necessary components on all swords in an inventory
     *
     * @param inventory the inventory
     * @param add       whether to add or remove the component that allows blocking the sword
     */
    private static void updateAllItems(@NotNull Inventory inventory, boolean add) {
        for (@Nullable ItemStack stack : inventory.getContents()) {
            if (stack == null) continue;
            if (add) VanillaBlocking.addSwordComponents(((CraftItemStack) stack).handle);
            else VanillaBlocking.removeSwordComponents(((CraftItemStack) stack).handle);
        }
    }

    // when items transferred to player inventory, make swords blockable
    // when items transferred to different inventory, remove component
    @EventHandler
    public void onInventoryUpdate(@NotNull InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        boolean isPlayerInventory = event.getClickedInventory().getHolder() instanceof Player;
        updateAllItems(event.getClickedInventory(), isPlayerInventory);
    }

    // When player joins, make all their swords blockable
    @EventHandler(ignoreCancelled = true)
    public void onJoin(@NotNull PlayerJoinEvent event) {
        updateAllItems(event.getPlayer().getInventory(), true);
    }

    // When player leaves, remove blockable components from swords
    @EventHandler
    public void onDisconnect(@NotNull PlayerQuitEvent event) {
        try {
            updateAllItems(event.getPlayer().getInventory(), false);
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onKick(@NotNull PlayerKickEvent event) {
        try {
            updateAllItems(event.getPlayer().getInventory(), false);
        } catch (Exception ignored) {}
    }

    // reduce damage by 50% when sword is blocked
    @EventHandler
    public void onDamagePlayer(@NotNull EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            event.setDamage(event.getDamage() * VanillaBlocking.damageMultiplier(((CraftPlayer) player).getHandle()));
        }
    }
}
