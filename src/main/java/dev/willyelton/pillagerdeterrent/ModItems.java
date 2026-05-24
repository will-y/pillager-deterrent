package dev.willyelton.pillagerdeterrent;

import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item PILLAGER_RING = register("pillager_ring", Item::new, new Item.Properties().stacksTo(1));
    public static final Item PILLAGER_WARDING_BANNER_ITEM = register("pillager_warding_banner", properties -> new PillagerWardingBannerItem(ModBlocks.PILLAGER_WARDING_BANNER, ModBlocks.PILLAGER_WARDING_WALL_BANNER, properties), new Item.Properties());

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, new ResourceLocation(PillagerDeterrent.MOD_ID, name));
        T item = itemFactory.apply(settings);
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {

    }
}
