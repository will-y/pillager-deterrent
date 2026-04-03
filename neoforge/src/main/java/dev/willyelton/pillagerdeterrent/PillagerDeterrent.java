package dev.willyelton.pillagerdeterrent;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MODID)
public class PillagerDeterrent {
    public PillagerDeterrent(IEventBus modEventBus, ModContainer container) {
        Registration.init(modEventBus);

        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC, "pillager_deterrent.toml");
    }
}
