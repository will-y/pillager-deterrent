package dev.willyelton.pillagerdeterrent.util;

import dev.willyelton.pillagerdeterrent.compat.accessories.AccessoriesCompat;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Predicate;

public class InventoryUtils {
    public static ItemStack findItem(Player player, Predicate<ItemStack> predicate) {
        List<ItemStack> accessoriesStacks = AccessoriesCompat.getAccessories(player);

        for (ItemStack stack : accessoriesStacks) {
            if (predicate.test(stack)) return stack;
        }

        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (predicate.test(stack)) return stack;
        }

        return ItemStack.EMPTY;
    }
}
