package dev.willyelton.pillagerdeterrent;

import com.mojang.logging.LogUtils;
import dev.willyelton.pillagerdeterrent.platform.Services;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import org.slf4j.Logger;

public class Constants {
    public static final String MODID = "pillager_deterrent";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceKey<PoiType> PILLAGER_DETERRENT_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, rl("pillager_warding_banner"));

    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public static ItemStackTemplate getBannerStack(HolderGetter<BannerPattern> patternRegistry) {
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

        var patch = DataComponentPatch.builder()
                .set(DataComponents.BANNER_PATTERNS, bannerpatternlayers)
                .set(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.BANNER_PATTERNS, true))
                .build();

        return new ItemStackTemplate(Services.REGISTRATION.getBannerBlockItem().builtInRegistryHolder(), 1, patch);
    }
}
