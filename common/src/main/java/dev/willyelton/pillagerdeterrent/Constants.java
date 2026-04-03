package dev.willyelton.pillagerdeterrent;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.slf4j.Logger;

public class Constants {
    public static final String MODID = "pillager_deterrent";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceKey<PoiType> PILLAGER_DETERRENT_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, rl("pillager_warding_banner"));

    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}
