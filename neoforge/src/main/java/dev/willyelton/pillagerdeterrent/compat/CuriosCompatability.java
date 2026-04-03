package dev.willyelton.pillagerdeterrent.compat;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.function.Predicate;

public class CuriosCompatability {
    public static Optional<ItemStack> getCuriosItems(Player player, Predicate<ItemStack> filter) {
        if (!ModList.get().isLoaded("curios")) {
            return Optional.empty();
        }

        return CuriosApi.getCuriosInventory(player).flatMap(iCuriosItemHandler -> iCuriosItemHandler.findFirstCurio(filter)).map(SlotResult::stack);
    }
}
