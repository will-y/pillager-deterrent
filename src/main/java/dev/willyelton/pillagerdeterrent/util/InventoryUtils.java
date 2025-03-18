package dev.willyelton.pillagerdeterrent.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class InventoryUtils {
    public static ItemStack findItem(Inventory inv, Predicate<ItemStack> predicate) {
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (predicate.test(stack)) return stack;
        }

        return ItemStack.EMPTY;
    }
}
