package dev.willyelton.pillagerdeterrent;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class Registration {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PillagerDeterrent.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PillagerDeterrent.MODID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, PillagerDeterrent.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PillagerDeterrent.MODID);

    public static final DeferredHolder<Item, Item> PILLAGER_RING = ITEMS.registerSimpleItem("pillager_ring", new Item.Properties().stacksTo(1));

    public static final DeferredHolder<Block, Block> PILLAGER_WARDING_BANNER = BLOCKS.registerSimpleBlock("pillager_warding_banner");

    public static final DeferredHolder<Item, BlockItem> PILLAGER_WARDING_BANNER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(PILLAGER_WARDING_BANNER);

    public static final DeferredHolder<PoiType, PoiType> PILLAGER_WARDING_BANNER_POI = POI_TYPES.register("pillager_warding_banner",
            () -> new PoiType(Set.of(PILLAGER_WARDING_BANNER.get().defaultBlockState()), 1, 1));

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("pillager_deterrent_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pillager_deterrent"))
                    .icon(() -> new ItemStack(PILLAGER_RING.get()))
                    .displayItems((flags, output) -> {
                        output.accept(PILLAGER_RING.get());
                        output.accept(PILLAGER_WARDING_BANNER_BLOCK_ITEM.get());
                    }).build());

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        POI_TYPES.register(modEventBus);
        TABS.register(modEventBus);
    }
}
