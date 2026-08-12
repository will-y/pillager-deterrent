package dev.willyelton.pillagerdeterrent.compat.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AccessoriesCompat {
    public static List<ItemStack> getAccessories(Player player) {
        if (FabricLoader.getInstance().isModLoaded("accessories")) {
            AccessoriesCapability accessoriesCapability = AccessoriesCapability.get(player);
            if (accessoriesCapability != null) {
                return accessoriesCapability.getAllEquipped().stream().map(SlotEntryReference::stack).toList();
            }
        }

        return List.of();
    }
}
