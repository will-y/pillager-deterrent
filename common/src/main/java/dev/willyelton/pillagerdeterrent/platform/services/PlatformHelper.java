package dev.willyelton.pillagerdeterrent.platform.services;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

// Credit: https://github.com/jaredlll08/MultiLoader-Template/blob/26.1.1/common/src/main/java/com/example/examplemod/platform/services/IPlatformHelper.java
public interface PlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    Item getBannerBlockItem();

    int bannerRange();
}
