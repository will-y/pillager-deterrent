package dev.willyelton.pillagerdeterrent;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(PillagerDeterrent.MODID)
public class PillagerDeterrent {
    public static final String MODID = "pillager_deterrent";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public PillagerDeterrent(IEventBus modEventBus, ModContainer container) {
        Registration.init(modEventBus);

        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC, "pillager_deterrent.toml");
    }
}
