package dev.willyelton.pillagerdeterrent.platform.services;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Predicate;

public interface CuriosCompatability {
    Optional<ItemStack> getCuriosItems(Player player, Predicate<ItemStack> filter);
}
