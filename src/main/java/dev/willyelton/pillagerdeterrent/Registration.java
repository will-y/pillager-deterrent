package dev.willyelton.pillagerdeterrent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.willyelton.pillagerdeterrent.block.WardingBannerBlock;
import dev.willyelton.pillagerdeterrent.block.WardingBannerWallBlock;
import dev.willyelton.pillagerdeterrent.datagen.AddTableLootModifier;
import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PillagerDeterrent.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PillagerDeterrent.MODID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, PillagerDeterrent.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PillagerDeterrent.MODID);
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, PillagerDeterrent.MODID);

    public static final RegistryObject<Item> PILLAGER_RING = ITEMS.register("pillager_ring", () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Block> PILLAGER_WARDING_BANNER = BLOCKS.register("pillager_warding_banner", () -> new WardingBannerBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_BANNER)));
    public static final RegistryObject<Block> PILLAGER_WARDING_WALL_BANNER = BLOCKS.register("pillager_warding_wall_banner", () -> new WardingBannerWallBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WALL_BANNER)));

    public static final RegistryObject<Item> PILLAGER_WARDING_BANNER_BLOCK_ITEM = ITEMS.register("pillager_warding_banner", () -> new PillagerWardingBannerItem(PILLAGER_WARDING_BANNER.get(), PILLAGER_WARDING_WALL_BANNER.get(), new Item.Properties()));

    public static final RegistryObject<PoiType> PILLAGER_WARDING_BANNER_POI = POI_TYPES.register("pillager_warding_banner",
            () -> new PoiType(getPOIBlockStates(), 1, 1));

    static {
        GLOBAL_LOOT_MODIFIERS.register("add_table", () -> AddTableLootModifier.CODEC);
    }

    public static RegistryObject<CreativeModeTab> TAB = TABS.register("pillager_deterrent_tab", () ->
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
        GLOBAL_LOOT_MODIFIERS.register(modEventBus);
    }

    public static ItemStack getBannerStack(HolderGetter<BannerPattern> patternRegistry) {
        ItemStack stack = new ItemStack(PILLAGER_WARDING_BANNER_BLOCK_ITEM.get());
        CompoundTag tag = new CompoundTag();
        CompoundTag blockEntityData = new CompoundTag();
        ListTag listTag = new ListTag();

        tag.put("BlockEntityTag", blockEntityData);
        blockEntityData.put("Patterns", listTag);

        addPattern(listTag, patternRegistry, BannerPatterns.RHOMBUS_MIDDLE, DyeColor.CYAN);
        addPattern(listTag, patternRegistry, BannerPatterns.STRIPE_BOTTOM, DyeColor.LIGHT_GRAY);
        addPattern(listTag, patternRegistry, BannerPatterns.STRIPE_CENTER, DyeColor.GRAY);
        addPattern(listTag, patternRegistry, BannerPatterns.BORDER, DyeColor.LIGHT_GRAY);
        addPattern(listTag, patternRegistry, BannerPatterns.STRIPE_MIDDLE, DyeColor.BLACK);
        addPattern(listTag, patternRegistry, BannerPatterns.HALF_HORIZONTAL, DyeColor.LIGHT_GRAY);
        addPattern(listTag, patternRegistry, BannerPatterns.CIRCLE_MIDDLE, DyeColor.LIGHT_GRAY);
        addPattern(listTag, patternRegistry, BannerPatterns.BORDER, DyeColor.BLACK);
        addPattern(listTag, patternRegistry, BannerPatterns.CROSS, DyeColor.RED);

        stack.setTag(tag);

        return stack;
    }

    private static void addPattern(ListTag listTag, HolderGetter<BannerPattern> patternRegistry, ResourceKey<BannerPattern> bannerpattern, DyeColor color) {
        patternRegistry.get(bannerpattern).ifPresent(bannerPatternReference -> {
            CompoundTag tag = new CompoundTag();
            tag.putInt("Color", color.getId());
            tag.putString("Pattern", bannerPatternReference.get().getHashname());
            listTag.add(tag);
        });
    }

    private static Set<BlockState> getPOIBlockStates() {
        ImmutableSet<BlockState> bannerStates = ImmutableSet.copyOf(PILLAGER_WARDING_BANNER.get().getStateDefinition().getPossibleStates());
        ImmutableSet<BlockState> wallStates = ImmutableSet.copyOf(PILLAGER_WARDING_WALL_BANNER.get().getStateDefinition().getPossibleStates());

        return ImmutableSet.copyOf(Sets.union(bannerStates, wallStates));
    }
}
