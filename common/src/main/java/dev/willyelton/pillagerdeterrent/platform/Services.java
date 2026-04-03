package dev.willyelton.pillagerdeterrent.platform;

import dev.willyelton.pillagerdeterrent.platform.services.CuriosCompatability;
import dev.willyelton.pillagerdeterrent.platform.services.PlatformHelper;
import dev.willyelton.pillagerdeterrent.platform.services.RegistrationHelper;

import java.util.ServiceLoader;

// Credit: https://github.com/jaredlll08/MultiLoader-Template/blob/26.1.1/common/src/main/java/com/example/examplemod/platform/services/IPlatformHelper.java
public class Services {

    // In this example we provide a platform helper which provides information about what platform the mod is running on.
    // For example this can be used to check if the code is running on NeoForge vs Fabric, or to ask the modloader if another
    // mod is loaded.
    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);
    public static final RegistrationHelper REGISTRATION = load(RegistrationHelper.class);
    public static final CuriosCompatability CURIOS_COMPATIBILITY = load(CuriosCompatability.class);

    // This code is used to load a service for the current environment. Your implementation of the service must be defined
    // manually by including a text file in META-INF/services named with the fully qualified class name of the service.
    // Inside the file you should write the fully qualified class name of the implementation to load for the platform. For
    // example our file on Forge points to ForgePlatformHelper while Fabric points to FabricPlatformHelper.
    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz, Services.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}