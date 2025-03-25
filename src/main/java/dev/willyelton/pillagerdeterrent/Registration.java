package dev.willyelton.pillagerdeterrent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import dev.willyelton.pillagerdeterrent.block.WardingBannerBlock;
import dev.willyelton.pillagerdeterrent.block.WardingBannerWallBlock;
import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Registration {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PillagerDeterrent.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PillagerDeterrent.MODID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, PillagerDeterrent.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PillagerDeterrent.MODID);

    public static final DeferredHolder<Item, Item> PILLAGER_RING = ITEMS.registerSimpleItem("pillager_ring", new Item.Properties().stacksTo(1).component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("lore.pillager_deterrent.ring", "test")))));

    public static final DeferredHolder<Block, Block> PILLAGER_WARDING_BANNER = BLOCKS.registerBlock("pillager_warding_banner", WardingBannerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BANNER));
    public static final DeferredHolder<Block, Block> PILLAGER_WARDING_WALL_BANNER = BLOCKS.registerBlock("pillager_warding_wall_banner", WardingBannerWallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER).overrideLootTable(Optional.of(ResourceKey.create(Registries.LOOT_TABLE, PillagerDeterrent.rl("blocks/" + PILLAGER_WARDING_BANNER.getId().getPath())))));

    public static final DeferredHolder<Item, PillagerWardingBannerItem> PILLAGER_WARDING_BANNER_BLOCK_ITEM = ITEMS.registerItem("pillager_warding_banner", properties -> new PillagerWardingBannerItem(PILLAGER_WARDING_BANNER.get(), PILLAGER_WARDING_WALL_BANNER.get(), properties), new Item.Properties().useBlockDescriptionPrefix());

    public static final DeferredHolder<PoiType, PoiType> PILLAGER_WARDING_BANNER_POI = POI_TYPES.register("pillager_warding_banner",
            () -> new PoiType(getPOIBlockStates(), 0, 1));

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
        stack.set(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.BANNER_PATTERNS, true));

        return stack;
    }

    private static Set<BlockState> getPOIBlockStates() {
        ImmutableSet<BlockState> bannerStates = ImmutableSet.copyOf(PILLAGER_WARDING_BANNER.get().getStateDefinition().getPossibleStates());
        ImmutableSet<BlockState> wallStates = ImmutableSet.copyOf(PILLAGER_WARDING_WALL_BANNER.get().getStateDefinition().getPossibleStates());

        return ImmutableSet.copyOf(Sets.union(bannerStates, wallStates));
    }
}
