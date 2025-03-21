package dev.willyelton.pillagerdeterrent;

import dev.willyelton.pillagerdeterrent.block.WardingBannerBlock;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    public static final DeferredHolder<Block, Block> PILLAGER_WARDING_BANNER = BLOCKS.registerBlock("pillager_warding_banner", WardingBannerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BANNER));
    public static final DeferredHolder<Block, Block> PILLAGER_WARDING_WALL_BANNER = BLOCKS.registerBlock("pillager_warding_wall_banner", properties -> new WallBannerBlock(DyeColor.WHITE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER));

    public static final DeferredHolder<Item, BlockItem> PILLAGER_WARDING_BANNER_BLOCK_ITEM = ITEMS.registerItem("pillager_warding_banner", properties -> new BannerItem(PILLAGER_WARDING_BANNER.get(), PILLAGER_WARDING_WALL_BANNER.get(), properties.stacksTo(16).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));

    public static final DeferredHolder<PoiType, PoiType> PILLAGER_WARDING_BANNER_POI = POI_TYPES.register("pillager_warding_banner",
            () -> new PoiType(Set.of(PILLAGER_WARDING_BANNER.get().defaultBlockState()), 1, 1));

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("pillager_deterrent_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pillager_deterrent"))
                    .icon(() -> new ItemStack(PILLAGER_RING.get()))
                    .displayItems((flags, output) -> {
                        output.accept(PILLAGER_RING.get());
                        output.accept(getBannerStack(flags.holders().lookupOrThrow(Registries.BANNER_PATTERN)));
                    }).build());

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        POI_TYPES.register(modEventBus);
        TABS.register(modEventBus);
    }

    public static ItemStack getBannerStack(HolderGetter<BannerPattern> patternRegistry) {
        ItemStack stack = new ItemStack(PILLAGER_WARDING_BANNER_BLOCK_ITEM);
        BannerPatternLayers bannerpatternlayers = new BannerPatternLayers.Builder()
                .addIfRegistered(patternRegistry, BannerPatterns.RHOMBUS_MIDDLE, DyeColor.CYAN)
                .addIfRegistered(patternRegistry, BannerPatterns.STRIPE_BOTTOM, DyeColor.LIGHT_GRAY)
                .addIfRegistered(patternRegistry, BannerPatterns.STRIPE_CENTER, DyeColor.GRAY)
                .addIfRegistered(patternRegistry, BannerPatterns.BORDER, DyeColor.LIGHT_GRAY)
                .addIfRegistered(patternRegistry, BannerPatterns.STRIPE_MIDDLE, DyeColor.BLACK)
                .addIfRegistered(patternRegistry, BannerPatterns.HALF_HORIZONTAL, DyeColor.LIGHT_GRAY)
                .addIfRegistered(patternRegistry, BannerPatterns.CIRCLE_MIDDLE, DyeColor.LIGHT_GRAY)
                .addIfRegistered(patternRegistry, BannerPatterns.BORDER, DyeColor.BLACK)
                .addIfRegistered(patternRegistry, BannerPatterns.CROSS, DyeColor.RED)
                .build();

        stack.set(DataComponents.BANNER_PATTERNS, bannerpatternlayers);
        stack.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);

        return stack;
    }
}
