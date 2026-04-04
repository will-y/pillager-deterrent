package dev.willyelton.pillagerdeterrent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import dev.willyelton.pillagerdeterrent.mixin.BlockEntityTypeAccessor;
import dev.willyelton.pillagerdeterrent.mixin.PoiTypesInvoker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

import static dev.willyelton.pillagerdeterrent.Constants.PILLAGER_DETERRENT_POI_KEY;
import static dev.willyelton.pillagerdeterrent.Constants.getBannerStack;

public class PillagerDeterrent implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.init();
        ModBlocks.init();

        PoiTypesInvoker.invokeRegister(BuiltInRegistries.POINT_OF_INTEREST_TYPE, PILLAGER_DETERRENT_POI_KEY, getPOIBlockStates(), 0, 1);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeTab) -> {
                    creativeTab.accept(ModItems.PILLAGER_RING);
                    creativeTab.accept(getBannerStack(Minecraft.getInstance().level.holderLookup(Registries.BANNER_PATTERN)).create());
                });

        ((BlockEntityTypeAccessor) BlockEntityType.BANNER).pillagerDeterrent$ValidBlocks().add(ModBlocks.PILLAGER_WARDING_BANNER);
        ((BlockEntityTypeAccessor) BlockEntityType.BANNER).pillagerDeterrent$ValidBlocks().add(ModBlocks.PILLAGER_WARDING_WALL_BANNER);
    }

    private static Set<BlockState> getPOIBlockStates() {
        ImmutableSet<BlockState> bannerStates = ImmutableSet.copyOf(ModBlocks.PILLAGER_WARDING_BANNER.getStateDefinition().getPossibleStates());
        ImmutableSet<BlockState> wallStates = ImmutableSet.copyOf(ModBlocks.PILLAGER_WARDING_WALL_BANNER.getStateDefinition().getPossibleStates());

        return ImmutableSet.copyOf(Sets.union(bannerStates, wallStates));
    }
}
