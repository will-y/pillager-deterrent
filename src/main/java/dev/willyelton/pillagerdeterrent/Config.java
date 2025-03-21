package dev.willyelton.pillagerdeterrent;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static ModConfigSpec.IntValue BANNER_RANGE;

    static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();
        setupConfig(configBuilder);
        SPEC = configBuilder.build();
    }

    private static void setupConfig(ModConfigSpec.Builder builder) {
        BANNER_RANGE = builder
                .comment("The number of blocks away that the pillager warding banner will prevent patrol spawns")
                .defineInRange("banner_range", 128, 0, 512);
    }
}
