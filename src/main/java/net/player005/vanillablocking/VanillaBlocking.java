package net.player005.vanillablocking;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import org.jetbrains.annotations.NotNull;

public final class VanillaBlocking {

    private static final Consumable CONSUMABLE_COMPONENT = Consumable.builder().consumeSeconds(Float.MAX_VALUE).animation(ItemUseAnimation.BLOCK).build();

    public static float damageMultiplier(Player player) {
        return (isBlockingSword(player)) ? 0.5f : 1;
    }

    public static boolean isBlockingSword(@NotNull Player player) {
        return player.getUseItem().is(ItemTags.SWORDS);
    }

    public static void addSwordComponents(@NotNull ItemStack itemStack) {
        if (!itemStack.is(ItemTags.SWORDS)) return;
        itemStack.applyComponents(
                DataComponentPatch.builder().set(DataComponents.CONSUMABLE, CONSUMABLE_COMPONENT).build()
        );
    }

    public static void removeSwordComponents(@NotNull ItemStack stack) {
        if (!stack.is(ItemTags.SWORDS)) return;
        stack.applyComponents(DataComponentPatch.builder().remove(DataComponents.CONSUMABLE).build());
    }

}
