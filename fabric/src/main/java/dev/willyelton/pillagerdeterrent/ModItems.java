package dev.willyelton.pillagerdeterrent;

import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;

import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final Item PILLAGER_RING = register("pillager_ring", Item::new, new Item.Properties().stacksTo(1).component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("lore.pillager_deterrent.ring", "test")))));
    public static final Item PILLAGER_WARDING_BANNER_ITEM = register("pillager_warding_banner", properties -> new PillagerWardingBannerItem(ModBlocks.PILLAGER_WARDING_BANNER, ModBlocks.PILLAGER_WARDING_WALL_BANNER, properties), new Item.Properties().useBlockDescriptionPrefix());

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MODID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {

    }
}
