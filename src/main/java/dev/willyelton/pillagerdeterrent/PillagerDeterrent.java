package dev.willyelton.pillagerdeterrent;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PillagerDeterrent.MODID)
public class PillagerDeterrent {
    public static final String MODID = "pillager_deterrent";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    public PillagerDeterrent() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.init(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC, "pillager_deterrent.toml");
    }
}
