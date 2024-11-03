package net.player005.vanillablocking;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VanillaBlockingPaper extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInventoryUpdate(@NotNull InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        boolean isPlayer = event.getClickedInventory().getHolder() instanceof Player;
        for (@Nullable ItemStack stack : event.getClickedInventory().getContents()) {
            if (stack == null) continue;
            if (isPlayer) VanillaBlocking.addSwordComponents(((CraftItemStack) stack).handle);
            else VanillaBlocking.removeSwordComponents(((CraftItemStack) stack).handle);
        }
    }

    @EventHandler
    public void onDisconnect(@NotNull PlayerQuitEvent event) {
        for (@Nullable ItemStack stack : event.getPlayer().getInventory().getContents()) {
            if (stack == null) continue;
            VanillaBlocking.removeSwordComponents(((CraftItemStack) stack).handle);
        }
    }

    @EventHandler
    public void onDamagePlayer(@NotNull EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            event.setDamage(event.getDamage() * VanillaBlocking.damageMultiplier(((CraftPlayer) player).getHandle()));
        }
    }
}
