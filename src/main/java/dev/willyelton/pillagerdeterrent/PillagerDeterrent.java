package dev.willyelton.pillagerdeterrent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import dev.willyelton.pillagerdeterrent.mixin.BlockEntityTypeAccessor;
import dev.willyelton.pillagerdeterrent.mixin.PoiTypesInvoker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static dev.willyelton.pillagerdeterrent.ModItems.PILLAGER_WARDING_BANNER_ITEM;
import static net.minecraft.world.level.storage.loot.BuiltInLootTables.PILLAGER_OUTPOST;

public class PillagerDeterrent implements ModInitializer {
	public static final String MOD_ID = "pillager_deterrent";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceKey<PoiType> PILLAGER_DETERRENT_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pillager_warding_banner"));


	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();

		PoiTypesInvoker.invokeRegister(BuiltInRegistries.POINT_OF_INTEREST_TYPE, PILLAGER_DETERRENT_POI_KEY, getPOIBlockStates(), 0, 1);

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register(tab -> {
					tab.accept(ModItems.PILLAGER_RING);
					tab.accept(getBannerStack(tab.getContext().holders().lookup(Registries.BANNER_PATTERN).orElseThrow()));
				});

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (source.isBuiltin() && PILLAGER_OUTPOST.equals(key)) {
				LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PILLAGER_RING)).setRolls(ConstantValue.exactly(1)).add(EmptyLootItem.emptyItem());
				tableBuilder.withPool(poolBuilder);
			}
		});

		((BlockEntityTypeAccessor) BlockEntityType.BANNER).pillagerDeterrent$ValidBlocks().add(ModBlocks.PILLAGER_WARDING_BANNER);
		((BlockEntityTypeAccessor) BlockEntityType.BANNER).pillagerDeterrent$ValidBlocks().add(ModBlocks.PILLAGER_WARDING_WALL_BANNER);

	}

	public static ItemStack getBannerStack(HolderGetter<BannerPattern> patternRegistry) {
		var patch = getPatch(patternRegistry);

		return new ItemStack(PILLAGER_WARDING_BANNER_ITEM.builtInRegistryHolder(), 1, patch);
	}

	public static DataComponentPatch getPatch(HolderGetter<BannerPattern> patternRegistry) {
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

		return DataComponentPatch.builder()
				.set(DataComponents.BANNER_PATTERNS, bannerpatternlayers)
				.build();
	}

	private static Set<BlockState> getPOIBlockStates() {
		ImmutableSet<BlockState> bannerStates = ImmutableSet.copyOf(ModBlocks.PILLAGER_WARDING_BANNER.getStateDefinition().getPossibleStates());
		ImmutableSet<BlockState> wallStates = ImmutableSet.copyOf(ModBlocks.PILLAGER_WARDING_WALL_BANNER.getStateDefinition().getPossibleStates());

		return ImmutableSet.copyOf(Sets.union(bannerStates, wallStates));
	}
}