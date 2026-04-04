package dev.willyelton.pillagerdeterrent;

import dev.willyelton.pillagerdeterrent.block.WardingBannerBlock;
import dev.willyelton.pillagerdeterrent.block.WardingBannerWallBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Optional;
import java.util.function.Function;

import static dev.willyelton.pillagerdeterrent.Constants.rl;

public class ModBlocks {
    public static final Block PILLAGER_WARDING_BANNER = register("pillager_warding_banner", WardingBannerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BANNER));
    public static final Block PILLAGER_WARDING_WALL_BANNER = register("pillager_warding_wall_banner", WardingBannerWallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER).overrideLootTable(Optional.of(ResourceKey.create(Registries.LOOT_TABLE, rl("blocks/pillager_warding_banner")))));

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MODID, name));
    }

    public static void init() {

    }
}
