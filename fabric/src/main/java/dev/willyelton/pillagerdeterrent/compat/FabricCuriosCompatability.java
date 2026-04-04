package dev.willyelton.pillagerdeterrent.compat;

import dev.willyelton.pillagerdeterrent.platform.services.CuriosCompatability;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Predicate;

public class FabricCuriosCompatability implements CuriosCompatability {
    @Override
    public Optional<ItemStack> getCuriosItems(Player player, Predicate<ItemStack> filter) {
        // TODO
        return Optional.empty();
    }
}
