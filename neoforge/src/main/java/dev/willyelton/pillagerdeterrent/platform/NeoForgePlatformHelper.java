package dev.willyelton.pillagerdeterrent.platform;

import dev.willyelton.pillagerdeterrent.platform.services.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

// Credit: https://github.com/jaredlll08/MultiLoader-Template/blob/26.1.1/neoforge/src/main/java/com/example/examplemod/platform/NeoForgePlatformHelper.java
public class NeoForgePlatformHelper implements PlatformHelper {
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }
}
