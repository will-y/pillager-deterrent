package dev.willyelton.pillagerdeterrent.platform;

import dev.willyelton.pillagerdeterrent.platform.services.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

// Credit: https://github.com/jaredlll08/MultiLoader-Template/blob/26.1.1/fabric/src/main/java/com/example/examplemod/platform/FabricPlatformHelper.java
public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}