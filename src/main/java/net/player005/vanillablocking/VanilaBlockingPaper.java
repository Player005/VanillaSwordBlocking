package net.player005.vanillablocking;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VanilaBlockingPaper extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        System.out.println("HI!");
    }

    @EventHandler
    public void onInventoryUpdate(@NotNull InventoryMoveItemEvent event) {
        if (event.getDestination().getHolder() instanceof Player player) {
            VanillaBlocking.onInventoryUpdate(((CraftPlayer) player).getHandle(), ((CraftItemStack) event.getItem()).handle);
        }
    }

    @EventHandler
    public void onDamagePlayer(@NotNull EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            event.setDamage(event.getDamage() * VanillaBlocking.damageMultiplier(((CraftPlayer) player).getHandle()));
        }
    }
}
