package dev.willyelton.pillagerdeterrent.compat;

import dev.willyelton.pillagerdeterrent.platform.services.CuriosCompatability;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Predicate;

public class NeoForgeCuriosCompatability implements CuriosCompatability {
    @Override
    public Optional<ItemStack> getCuriosItems(Player player, Predicate<ItemStack> filter) {
        return Optional.empty();
//        if (!ModList.get().isLoaded("curios")) {
//            return Optional.empty();
//        }
//
//        return CuriosApi.getCuriosInventory(player).flatMap(iCuriosItemHandler -> iCuriosItemHandler.findFirstCurio(filter)).map(SlotResult::stack);
    }
}
