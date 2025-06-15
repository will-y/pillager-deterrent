package dev.willyelton.pillagerdeterrent.compat;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;
import java.util.function.Predicate;

public class CuriosCompatability {
    public static Optional<ItemStack> getCuriosItems(Player player, Predicate<ItemStack> filter) {
        if (!ModList.get().isLoaded("curios")) {
            return Optional.empty();
        }

        LazyOptional<ICuriosItemHandler> inventoryOptional = CuriosApi.getCuriosInventory(player);
        if (inventoryOptional.isPresent() && inventoryOptional.resolve().isPresent()) {
            ICuriosItemHandler handler = inventoryOptional.resolve().get();
            return handler.findFirstCurio(filter).map(SlotResult::stack);
        }

        return Optional.empty();
    }
}
